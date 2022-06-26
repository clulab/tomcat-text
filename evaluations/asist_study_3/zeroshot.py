#!/usr/bin/env python

# zeroshot classifier for study 3 transcriptions
# ============
# This script feed the study 3 transcriptions (32 files, 3686 utterances) to the zero-shot classifier, and generate the scores for each labels.
# argument1: the directory of the transcriptions
# argument2(optional): cadidate labels, segmented by comma. A default list of labels is provided)
# output: csv files for each label, with the utterance and scores for each label


# Example usage
# -------------
# python zeroshot.py /home/tomcat/annotations/ie-annotations Movement,Precedence,Inform




import pandas as pd
import glob
import os
import sys
import torch
from transformers import pipeline


directory = sys.argv[1]
candidate_labels = sys.argv[2].split(',')

def loadFiles(directory):
    files = glob.glob(os.path.join(directory, "*.csv"))
    files.sort()
    files = files[:32]
    files
    dfs = (pd.read_csv(f,index_col=None, header=0).fillna("0") for f in files)
    df = pd.concat(dfs, ignore_index=True)
    return df["utt"].tolist()

utt = loadFiles(directory)

def Zeroshot(candidate_labels = ['Movement',
                    'Precedence',
                    'Triage',
                    'Inform',
                    'Report Location',
                    'Search',
                    'Request',
                    'Question',
                    'Instruction',
                    'Plan']):

    classifier = pipeline("zero-shot-classification",
                        model="oigele/Fb_improved_zeroshot")
    res = []

    for sentence in utt:
        res.append(classifier(sentence, candidate_labels,multi_label=True))

    return pd.DataFrame(res)

out = Zeroshot(candidate_labels)

#zip
def ZipLabelScore(out):
    labels = out['labels'].to_list()
    scores = out['scores'].to_list()
    label_score = []
    for i in range(len(labels)):
        l = list(zip(labels[i],scores[i]))
        dct = {j[0]:j[1] for j in l}
        label_score.append(dct)
    return list(zip(out['sequence'],label_score))

utt_label_score = ZipLabelScore(out)

def labelCsv(label):
    outList = [[i[0],i[1][label]] for i in utt_label_score]
    df = pd.DataFrame(outList,columns=["sequence","score"])
    filename = label + '.csv'
    df.to_csv(filename,index=False)

if candidate_labels:
    for label in candidate_labels:
        labelCsv(label)
else:
    labelCsv('Movement')
    labelCsv('Precedence')
    labelCsv('Triage')
    labelCsv('Inform')
    labelCsv('Report Location')
    labelCsv('Search')
    labelCsv('Request')
    labelCsv('Question')
    labelCsv('Instruction')
    labelCsv('Plan')
