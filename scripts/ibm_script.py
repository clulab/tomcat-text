import json
import os

from ibm_watson import SpeechToTextV1
from ibm_cloud_sdk_core.authenticators import IAMAuthenticator


"""
To use this script you need to replace two strings, the NEEDSKEY and 
NEEDSINSTANCEURL strings.
This is done by creating a speech-to-text resouce in the IBM Cloud console.
When the resource is created, the console will provide these two strings. 
"""

authenticator = IAMAuthenticator('NEEDSKEY')
speech_to_text = SpeechToTextV1(
    authenticator=authenticator
)
speech_to_text.set_service_url('NEEDSINSTANCEURL')

cur_dir = os.fsencode('./data/')
for cur in os.listdir(cur_dir):
    dir_name = os.fsdecode(cur)
    print(dir_name)
    for mission_f in os.listdir(cur_dir+cur):
        mission_f_name = os.fsdecode(mission_f)
        if mission_f_name[:-1] != 'mission_':
            continue
        if not os.path.exists('./data/'+dir_name+'/'+mission_f_name+'/player_audio.wav'):
            continue
        with open('./data/'+dir_name+'/'+mission_f_name+'/player_audio.wav', 'rb') as audio_file:
            recognition_results = speech_to_text.recognize(
              audio=audio_file,
              content_type='audio/wav',
              word_alternative_threshold=0.9
            ).get_result()
        if 'results' not in recognition_results:
            print(f'Failed to process: {dir_name}/{mission_f_name}/player_audio.wav')
            print(recognition_results)
            continue
        result_json = recognition_results['results']
        transcript = ''
        for j in range(len(result_json)):
            transcript += result_json[j]['alternatives'][0]['transcript']
        with open('./data/'+dir_name+'/'+mission_f_name+'/ibm_transcript_full.txt', 'w') as out_file:
            out_file.write(transcript)
        name = './data/'+dir_name+'/'+mission_f_name+'/player_audio.wav'
        print(f'Collected transcript for file: {name}')
