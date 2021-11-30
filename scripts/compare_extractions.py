"""
This script compares ODIN extractions to BRAT annotations.

The script will calculate Recall, Precision, and F1 of the file(s) passed in.

Invoking the script:
compare_extractions.py dir_containing_extractions_and_annotations/
 - If the first argument is a directory, the script assumes that this directory
   contains pairs of files where each file ending with '.txt' is an annotation
   file and the file with same prefix but ending with '.out' is the
   file containing the corresponding ODIN extractions.

compare_extractions.py path_to_annotation_file path_to_extraction_file
 - If the first argument is not a directory, the system will expect a second
   argument telling it the path to the file containing the extractions.

"""


import sys
import json
import os
import spacy

LABELS = {'MOVE', 'SAVE', 'SIGHT'}


def trim_event(event_dict):
    nlp = spacy.load("en_core_web_sm")
    doc = nlp(event_dict['text'])
    tokens, pos = [], []
    for token in doc:
        tokens.append(token.text)
        pos.append(token.pos_)
    return event_dict


def main(annotation_file_name, extract_file_name):
    annotations = []
    with open(annotation_file_name, 'r') as in_file:
        for line in in_file:
            if line[0] != 'T':
                continue
            event = {}
            cur = line.strip().split('\t')[1:]
            first, text = cur
            first = first.split(' ')
            event['text'] = text
            event['label'] = first[0]
            if event['label'] not in LABELS:
                continue
            event['char_start'] = int(first[1])
            event['char_end'] = int(first[2])
            #event = trim_event(event)
            annotations.append(event)

    extractions = []
    with open(extract_file_name, 'r') as in_file:
        for line in in_file:
            event = {}
            cur = json.loads(line.strip())['data']
            event['text'] = cur['Span']
            event['label'] = cur['Label'].upper()
            if event['label'] not in LABELS:
                continue
            event['char_start'] = cur['start_char']
            event['char_end'] = cur['end_char']
            extractions.append(event)

    true_pos = 0
    false_pos = [1 for x in extractions]
    for i, annotation in enumerate(annotations):
        for j, extract in enumerate(extractions):
            if annotation['char_start'] == extract['char_start'] and \
                    annotation['label'] == extract['label']:
                true_pos += 1
                false_pos[j] = 0
                break

    recall = true_pos / len(annotations)
    precision = true_pos / (true_pos + sum(false_pos))
    if true_pos != 0:
        f1 = (2 * recall * precision) / (recall + precision)
    else:
        f1 = 0.0

    print(
        f'Precision: {precision:.2} | {true_pos} / {true_pos+sum(false_pos)}')
    print(f'Recall: {recall:.2} | {true_pos} / {len(annotations)}')
    print(f'F1: {f1:.2}')
    return true_pos, len(annotations), sum(false_pos)


if __name__ == '__main__':
    if os.path.isdir(sys.argv[1]):
        total_true, total_pos, total_false = 0, 0, 0
        for f in os.listdir(sys.argv[1]):
            if not f.endswith('.txt'):
                continue
            else:
                try:
                    print(sys.argv[1]+f)
                    print(sys.argv[1]+f[:-4]+'.out')
                    temp = main(
                        sys.argv[1]+f, sys.argv[1]+f[:-4]+'.out')
                except:
                    print('Skipping file...')
                    raise  # continue
                total_true += temp[0]
                total_pos += temp[1]
                total_false += temp[2]
        recall = total_true / total_pos
        precision = total_true / (total_true + total_false)
        f1 = (2 * recall * precision) / (recall + precision)

        print(
            f'\n\nPrecision: {precision:.2} | {total_true}/{total_true+total_false}')
        print(f'Recall: {recall:.2} | {total_true}/{total_pos}')
        print(f'F1: {f1:.2}')

    else:
        scores = main(sys.argv[1], sys.argv[2])
