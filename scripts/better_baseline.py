"""
This script uses cosine angles to label verbs in transcripts
and compares the extractions to annotations from BRAT. 

Invoking the script:
better_baseline.py transcript_dir/ path_to_token_embeddings
 - Calling with a directory full of transcripts causes the script to calculate
   scores across all files ending with '.raw'. The script assumes that each
   '.raw' file is accompanied by a '.txt' file in the same directory
   containing the corresponding BRAT annotations. 

better_baseline.py path_to_transcript_file path_to_token_embeddings
 - Calling with a single file causes the script to print all detected events
   to stdout. 
"""

import spacy
import numpy as np

import sys
import os

LABELS = {'SAVE', 'MOVE', 'SIGHT'}


# Taken from:
# https://stackoverflow.com/questions/2827393/angles-between-two-n-dimensional-vectors-in-python/13849249#13849249
def unit_vector(vector):
    """ Returns the unit vector of the vector.  """
    return vector / np.linalg.norm(vector)


def angle_between(v1, v2):
    return np.dot(v1, v2) / (np.linalg.norm(v1)*np.linalg.norm(v2))


def get_label(text, numbers, index, threshold):
    text = text.lower()
    if text not in index:
        return 'SAVE', 0
    text_rep = numbers[index[text]]
    best = (-1, 'null')
    for label in LABELS:
        label_rep = numbers[index[label.lower()]]
        ang = angle_between(text_rep, label_rep)
        if ang > best[0]:
            best = (ang, label)
    return best[1], best[0]


def baseline_classify(input_file_name, numbers, index, threshold=0.0):

    nlp = spacy.load('en_core_web_sm')
    events = []
    char_counter = 0
    with open(input_file_name, 'r') as in_file:
        for line in in_file:
            cur = nlp(line)
            for token in cur:
                if token.pos_ == 'VERB':
                    potential_label, angle = get_label(
                        token.text,
                        numbers,
                        index,
                        threshold)
                    if potential_label:
                        event = {}
                        event['text'] = token.text
                        event['label'] = potential_label
                        event['char_start'] = token.idx + char_counter
                        event['char_end'] = token.idx + len(event['text']) \
                            + char_counter
                        event['angle'] = angle
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


def calc_alignment_stats(events, annotations, threshold):
    true_pos = 0
    false_pos = [1 if x['angle'] > threshold else 0 for x in events]
    for annotation in annotations:
        for j, extract in enumerate(events):
            if extract['angle'] < threshold:
                continue
            if annotation['char_start'] == extract['char_start'] and \
                    annotation['label'] == extract['label']:
                true_pos += 1
                false_pos[j] = 0
                break
    return true_pos, len(annotations), sum(false_pos)


if __name__ == '__main__':
    numbers = []
    index = dict()
    with open(sys.argv[2], 'r') as in_file:
        for line in in_file:
            cur = line.strip()
            cur = cur.split(' ')
            index[cur[0]] = len(numbers)
            numbers.append([float(x) for x in cur[1:]])
    numbers = np.array(numbers)
    assert len(index) == len(numbers)

    if os.path.isdir(sys.argv[1]):
        pr_scores = []
        threshold_values = [0.1*x for x in range(1, 10)]
        threshold_stats = [[0, 0, 0] for x in threshold_values]
        for f in os.listdir(sys.argv[1]):
            if f[-3:] == 'raw':
                file_name = sys.argv[1] + f
                events = baseline_classify(file_name, numbers, index)
                annotations = get_annotations(file_name)
                for i, thresh in enumerate(threshold_values):
                    stats = calc_alignment_stats(events, annotations, thresh)
                    threshold_stats[i][0] += stats[0]
                    threshold_stats[i][1] += stats[1]
                    threshold_stats[i][2] += stats[2]

        for i, threshold in enumerate(threshold_values):
            true_pos, num_true, false_pos = threshold_stats[i]
            recall = true_pos / num_true
            precision = true_pos / (true_pos + false_pos)
            f1 = (2 * recall * precision) / (recall + precision)
            print(f'Stats for threshold: {threshold:.2}')
            print(f'\tRecall: {recall:.2}')
            print(f'\tPrecision: {precision:.2}')
            print(f'\tF1: {f1:.2}')
    else:
        events = baseline_classify(sys.argv[1], numbers, index)
        for event in events:
            print(event)
