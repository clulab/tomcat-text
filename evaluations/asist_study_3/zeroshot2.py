#!/usr/bin/env python

# zeroshot classifier for study 3 transcriptions
# ============
# This script feed the study 3 transcriptions (32 files, 3686 utterances) to the zero-shot classifier(multi_label = False), and generate the scores for each labels(add up to 1).
# argument: the directory of the transcriptions
# output: csv files for each label, with the utterance and scores for each label


# Example usage
# -------------
# python zeroshot2.py /home/tomcat/annotations/transcriptions


import pandas as pd
import glob
import os
import sys
import torch
from transformers import pipeline
import logging
from logging import info
from tqdm import tqdm


logging.basicConfig(level=logging.INFO)
directory = sys.argv[1]

def loadFiles(directory):
    files = glob.glob(os.path.join(directory, "*.csv"))
    files.sort()
    files = files[:32]
    files
    dfs = (pd.read_csv(f, index_col=None, header=0).fillna("0") for f in files)
    df = pd.concat(dfs, ignore_index=True)
    return df["utt"].tolist()
utt = loadFiles(directory)

token_labels = [
    "Critical Victim",
    "Victim",
    "Room",
    "Engineer",
    "Transporter",
    "Medic",
    "Rubble",
    "Marker Block",
    "Meeting",
]

event_labels=[
    "Movement",
    "Precedence",
    "Rescue",
    "Inform",
    "Report Location",
    "Search",
    "Request",
    "Question",
    "Instruction",
    "Plan",
]

def Zeroshot(candidate_labels):
    info("Setting up", candidate_labels[:5], "classifier")
    classifier = pipeline(
        "zero-shot-classification", model="oigele/Fb_improved_zeroshot"
    )
    res = []

    for sentence in tqdm(utt, unit="sentence"):
        res.append(classifier(sentence, candidate_labels))

    return pd.DataFrame(res)

Zeroshot(token_labels).to_csv("token.csv", index=False)
Zeroshot(event_labels).to_csv("event.csv", index=False)
