#!/usr/bin/env python3

import argparse
import json
import os
import sys
import traceback

# Authors:  Joseph Astier, Adarsh Pyarelal
#
# Validate the Testbed output of the UAZ Dialog Agent by counting the 
# occurance of each message type.

# Keep track of the number of relevant messages
class MessageCounter:

    # relevant message counts
    counts = {}

    # reset for each instance of this class
    def __init__(self):
        self.counts = {'asr':0, 
            'chat': 0,
            'dialog': 0,
            'heartbeats': 0,
            'num_lines': 0,
            'num_messages': 0,
            'rollcall_request': 0,
            'rollcall_response': 0,
            'trial_start': 0,
            'version_info': 0}

    # Bump the count for the key
    def increment_field(self, key):
        value = 1
        if(key in self.counts):
            value += self.counts[key]
        self.counts.update({key:value})

    # Record valid single-line JSON messages
    def count_message(self, message):
        if (('topic' in message)
        and('header' in message)
        and('message_type' in message['header'])
        and('msg' in message)
        and('source' in message['msg'])
        and('sub_type' in message['msg'])):
            self.increment_field("num_messages")

            topic = message['topic']
            message_type = message['header']['message_type']
            sub_type = message['msg']['sub_type']
            source = message['msg']['source']

            # subscribed Trial message
            if((topic == 'trial') 
            and (message_type == 'trial')):
                if(sub_type == 'start'):
                    self.increment_field('trial_start')
                elif(sub_type == 'stop'):
                    self.increment_field('trial_stop')

            # subscribed Rollcall Request messsage
            elif((topic == 'agent/control/rollcall/request')
            and(message_type == 'agent')
            and(sub_type == 'rollcall:request')):
                self.increment_field('rollcall_request')

            # subscribed Chat messsage
            elif((topic == 'minecraft/chat')
            and(message_type == 'chat')
            and(sub_type == 'Event:Chat')):
                self.increment_field('chat')

            # subscribed ASR messsage
            elif((topic == 'agent/asr/final')
            and(message_type == 'observation')
            and(sub_type == 'asr:transcription')):
                self.increment_field('asr')

            # published Dialog Agent message
            elif((topic == 'agent/dialog')
            and(message_type == 'event')
            and(sub_type == 'Event:dialogue_event')
            and(source == 'uaz_dialog_agent')):
                self.increment_field('dialog')

            # published Heartbeat message
            elif((topic == 'agent/uaz_dialog_agent/heartbeats')
            and(message_type == 'status')
            and(sub_type == 'heartbeat')
            and(source == 'uaz_dialog_agent')):
                self.increment_field('heartbeats')

            # published Version Info message
            elif((topic == 'agent/tomcat_textAnalyzer/versioninfo')
            and(message_type == 'agent')
            and(sub_type == 'versioninfo')
            and(source == 'uaz_dialog_agent')):
                self.increment_field('version_info')

            # published Rollcall Response message
            elif((topic == 'agent/control/rollcall/response')
            and(message_type == 'agent')
            and(sub_type == 'rollcall:response')
            and(source == 'uaz_dialog_agent')):
                self.increment_field('rollcall_response')

    # count a line of input which may be anything
    def count_line(self, line):
        self.increment_field('num_lines')
        try:
            self.count_message(json.loads(line))
        except Exception as e:
            line_number = self.counts['num_lines']
            bad_line = line.replace('\n','')
            print(f'Could not parse JSON on line {line_number}: {bad_line}')

# name is the agent name
# lines are single-line JSON messages from the message bus
# table is a global where:
#     key is the id of the test.
#     value is a tuple of [name, success, data, predicate] where:
#         name is the agent name. 
#         success is a boolean. 
#         data is extra data you've given to accompany the result.
#         predicate is the test itself
#
def uaz_dialog_agent_test(name,lines,table:dict):

    message_counter = MessageCounter()

    # count the messages
    for line in lines:
        message_counter.count_line(line)

    counts = message_counter.counts

    # string representations of message counts
    asr = str(counts['asr'])
    chat = str(counts['chat'])
    dialog = str(counts['dialog'])
    heartbeats = str(counts['heartbeats'])
    version_info = str(counts['version_info'])
    rc_res = str(counts['rollcall_response'])
    rc_req = str(counts['rollcall_request'])
    trial_start = str(counts['trial_start'])
    num_messages = str(counts['num_messages'])

    # TEST 0:  The number of dialog messages must equal the number
    #          of chat and final ASR messages
    test_id = f'{name}_0'
    success = counts['dialog'] == (counts['asr'] + counts['chat'])
    data = f'# dialog : {dialog}'
    predicate = f'# dialog({dialog}) == chat({chat}) + final({asr})'
    table[test_id] = name, str(success), data, predicate

    # TEST 1:  The number of version_info messages must equal the number
    #          of trial_start messages
    test_id = f'{name}_1'
    success = counts['version_info'] == counts['trial_start']
    data = f'# version_info : {version_info}'
    predicate = f'# version_info({version_info}) == trial_start({trial_start})'
    table[test_id] = name, str(success), data, predicate

    # TEST 2:  The number of rollcall_response messages must equal the
    #          rollcall_request messages
    test_id = f'{name}_2'
    success = counts['rollcall_response'] == counts['rollcall_request']
    data = f'# rollcall_response : {rc_res}'
    predicate = f'# rollcall_response({rc_res}) == rollcall_request({rc_req})'
    table[test_id] = name, str(success), data, predicate

    # TEST 3:  The number of heartbeat messages must be greater than zero
    test_id = f'{name}_3'
    success = counts['heartbeats'] > 0
    data = f'# heartbeats : {heartbeats}'
    predicate = f'# heartbeats({heartbeats}) > 0'
    table[test_id] = name, str(success), data, predicate

    # TEST 4:  The number of messages must be greater than zero
    test_id = f'{name}_4'
    success = counts['num_messages'] > 0
    data = f'# num_messages : {num_messages}'
    predicate = f'# num_messages({num_messages}) > 0'
    if not success:  # a degenerate case, only include if it fails.
        table[test_id] = name, str(success), data, predicate

# test one filename from the command line
def test_metadata_file(filename):

    # simulate the global table used by the Testbed
    table = {}

    input_file = open(filename, 'r', encoding='UTF-8') 
    lines = input_file.readlines()
    uaz_dialog_agent_test('uaz_dialog_agent', lines, table)

    # Show the table using the same formatting as the Testbed
    print ("{:<28} {:<28} {:<10} {:<32} {:<30}".format('AC/ASI',
        'Test ID', 'Success', 'Relevent Data', 'Predicate'))
    for k, v in table.items():
        test_id, success, data, predicate = v
        print ("{:<28} {:<28} {:<10} {:<32} {:<30}".format(test_id, 
            k, success, data, predicate))

# If run as a script, test metadata files from the command line
if __name__ == '__main__':

    parser = argparse.ArgumentParser()
    parser.add_argument('filenames', 
        action='store', 
        nargs = '+',
        help = 'One or more *.metadata filenames') 
    args = parser.parse_args(sys.argv[1:])

    # process valid input files, warn on others
    for filename in args.filenames:

        print(f'{filename}:')

        # Check that the file exists
        if (os.path.isfile(filename)):
            # Check that it's a metadata file
            if filename.endswith('.metadata'):
                test_metadata_file(filename)
            else:
                print('Input file must have .metadata extension')
        else:
            print('File not found.')

        print('')
