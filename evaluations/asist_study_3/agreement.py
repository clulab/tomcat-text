#!/usr/bin/env python

# inter-coder agreement calculater for study 3 ie annotations (labels are not tokenized version)
# ============
# This script calculate the inter-coder reliability between two coders, and produce a file of disagreemnt items between two coders.
# argument1: the directory of first coder's annotation scripts
# argument2: the directory of first coder's annotation scripts
# output: general agreement, out files of disagreemt utterances and their labels

# Example usage
# -------------
# python agreement.py /home/tomcat/annotations/ie-annotations/rater1 /home/tomcat/annotations/ie-annotations/rater2

import pandas as pd
import glob
import os
import re
import sys
from collections import Counter

dir1 = sys.argv[1]
dir2 = sys.argv[2]

coder1 = dir1.split('/')[-1]
coder2 = dir2.split('/')[-1]

#read files and save the labels to lists
def readFiles(dir):
    rater_files = glob.glob(os.path.join(dir, "*.csv"))
    rater_dfs = (pd.read_csv(f,index_col=None, header=0).fillna("0") for f in rater_files)
    rater_df = pd.concat(rater_dfs, ignore_index=True)
    return rater_df

rater1 = readFiles(dir1)["labels"].tolist()
rater2= readFiles(dir2)["labels"].tolist()
utt = readFiles(dir1)["utt"].tolist()

def cleanArgue(rater):
    res = []
    for i in rater:
        res.append(re.sub(r'\{[^)]*\}',"",i))
    return res
cleanR1 = cleanArgue(rater1)
cleanR2 = cleanArgue(rater2)

agree = disagree = 0
disagreeIdx = []

for i in range(len(cleanR1)):
    overlap = Counter(cleanR1[i].split("/")) & Counter(cleanR2[i].split("/"))
    maxlabel = max(len(cleanR1[i].split("/")),len(cleanR2[i].split("/")))
    len_overlap = 0
    for j in overlap:
        len_overlap += overlap[j]
        agree += len_overlap
    if len_overlap != maxlabel:
        disagree += maxlabel - len_overlap
        disagreeIdx.append(i)

print('the overall inter-coder agreement:', agree/(agree+disagree))

disagreeRows = [[utt[i],rater1[i],rater2[i]] for i in disagreeIdx]
df = pd.DataFrame(disagreeRows,columns=["utt",coder1,coder2])
df.to_csv('disagreement.csv',index=False)
