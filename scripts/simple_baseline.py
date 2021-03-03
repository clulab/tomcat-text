"""
This script randomly assigns labels to verbs in transcripts
and compares the extractions to annotations from BRAT. 

Invoking the script:
simple_baseline.py transcript_dir/ path_to_token_embeddings
 - Calling with a directory full of transcripts causes the script to calculate
   scores across all files ending with '.raw'. The script assumes that each
   '.raw' file is accompanied by a '.txt' file in the same directory
   containing the corresponding BRAT annotations. 

simple_baseline.py path_to_transcript_file path_to_token_embeddings
 - Calling with a single file causes the script to print all detected events
   to stdout. 
"""

import spacy
import numpy as np

import sys
import os
import random

LABELS = {'SAVE', 'MOVE', 'SIGHT'}


def get_label(text):
    return random.choice(list(LABELS))


def baseline_classify(input_file_name):

    nlp = spacy.load('en_core_web_sm')
    events = []
    char_counter = 0
    with open(input_file_name, 'r') as in_file:
        for line in in_file:
            cur = nlp(line)
            for token in cur:
                if token.pos_ == 'VERB':
                    event = {}
                    event['text'] = token.text
                    event['label'] = get_label(token.text)
                    event['char_start'] = token.idx + char_counter
                    event['char_end'] = token.idx + len(event['text']) \
                        + char_counter
                    events.append(event)
            char_counter += len(line)

    return events


def get_annotations(file_name):
    annotations = []
    annotation_file_name = file_name[:-3] + 'txt'
    with open(annotation_file_name, 'r') as in_file:
        for line in in_file:
            if line[0] != 'T':
                continue
            event = {}
            cur = line.strip().split('\t')[1:]
            first, text = cur
            first = first.split()
            event['text'] = text
            event['label'] = first[0]
            if event['label'] not in LABELS:
                continue
            event['char_start'] = int(first[1])
            event['char_end'] = int(first[2])
            annotations.append(event)
    return annotations


def calc_alignment_stats(events, annotations):
    true_pos = 0
    false_pos = [1 for x in events]
    for annotation in annotations:
        for j, extract in enumerate(events):
            if annotation['char_start'] == extract['char_start'] and \
                    annotation['label'] == extract['label']:
                true_pos += 1
                false_pos[j] = 0
                break
    return true_pos, len(annotations), sum(false_pos)


if __name__ == '__main__':
    random.seed(123)

    if os.path.isdir(sys.argv[1]):
        true_pos, num_true, false_pos = 0, 0, 0
        for f in os.listdir(sys.argv[1]):
            if f[-3:] == 'raw':
                file_name = sys.argv[1] + f
                events = baseline_classify(file_name)
                annotations = get_annotations(file_name)
                stats = calc_alignment_stats(events, annotations)
                true_pos += stats[0]
                num_true += stats[1]
                false_pos += stats[1]

        recall = true_pos / num_true
        precision = true_pos / (true_pos + false_pos)
        f1 = (2 * recall * precision) / (recall + precision)
        print(f'Recall: {recall:.2} | {true_pos} / {num_true}')
        print(
            f'Precision: {precision:.2} | {true_pos} / {true_pos + false_pos}')
        print(f'F1: {f1:.2}')
    else:
        events = baseline_classify(sys.argv[1])
        for event in events:
            print(event)
