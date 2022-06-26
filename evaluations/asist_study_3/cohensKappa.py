#!/usr/bin/env python

# cohen's Kappa calculator for study 3 ie annotations
# ============
# This script calculate the inter-coder reliability between two coders
# argument1: the directory of first coder's annotation scripts
# argument2: the directory of first coder's annotation scripts
# output: Cohen's Kappa, general agreement, token labels' agreement, event labels' agreement, agreement for each label


# Example usage
# -------------
# python cohensKappa.py /home/tomcat/annotations/ie-annotations/rater1 /home/tomcat/annotations/ie-annotations/rater2


import pandas as pd
import glob
import os
import re
import sys
from collections import Counter
from sklearn.metrics import cohen_kappa_score


dir1 = sys.argv[1]
dir2 = sys.argv[2]

#read files and save the labels to lists
def readFiles(dir):
    rater_files = glob.glob(os.path.join(dir, "*.csv"))
    rater_dfs = (pd.read_csv(f,index_col=None, header=0).fillna("0") for f in rater_files)
    rater_df = pd.concat(rater_dfs, ignore_index=True)
    return rater_df

rater1 = readFiles(dir1)["labels"].tolist()
rater2= readFiles(dir2)["labels"].tolist()

labels = {'0':0,
"CriticalVictim":1,
'Victim':2,
'Room':3,
'Engineer':4,
'Transporter':5,
'Medic':6,
'Rubble':7,
'MarkerBlock':8,
'Meeting':9,
'Movement':10,
'Precedence':11,
'Triage':12,
'KnowledgeSharing':13,
'ReportLocation':14,
'Search':15,
'HelpRequest':16,
'Question':17,
'YesNoQuestion':18,
'Instruction':19,
'Plan':20
}

#clean argument and tokenize labels
def clean(rater):
    cleanR = []
    for i in rater:
        label = re.sub(r'\{[^)]*\}',"",i).split('/')
        cleanR.append([labels[j] for j in label])
    return cleanR
cleanR1 = clean(rater1)
cleanR2 = clean(rater2)

R1 = []
R2 = []
for i in range(len(cleanR1)):
    len1,len2 = len(cleanR1[i]),len(cleanR2[i])
    #padding 0 to shorter lists
    if len1 > len2:
        r2 = cleanR2[i] + (len1-len2) * [0]
        r1 = cleanR1[i]
    elif len2 > len1:
        r1 = cleanR1[i] + (len2-len1) * [0]
        r2 = cleanR2[i]
    else:
        r1,r2 = cleanR1[i], cleanR2[i]
    #deal with overlap
    overlap = Counter(r1) & Counter(r2)
    for j in overlap:
        R1 += [j] * overlap[j]
        R2 += [j] * overlap[j]
        r1.remove(j)
        r2.remove(j)
    for k in r1:
        R1.append(k)
    for l in r2:
        R2.append(l)

print('--------------------')
print('Cohen\'s Kappa score: ', cohen_kappa_score(R1, R2))
print('--------------------')


agree = 0
for i in range(len(R1)):
    if R1[i] == R2[i]:
        agree += 1
print('Overall agreement(agree/(agree+disagree)):', agree/len(R1))
print('--------------------')

def groupedLabel(labelRange):
    S1,S2 = [],[]
    for i in range(len(R1)):
        if R1[i] in list(labelRange) or R2[i] in list(labelRange):
            S1.append(R1[i])
            S2.append(R2[i])
    agree = 0
    for j in range(len(S1)):
        if S1[j] == S2[j]:
            agree += 1
    print("the number of labels in this: ", len(S1))
    print('the agreement of this label group: ',agree/len(S1))

print('Token labels')
groupedLabel(range(1,10))
print('----------')
print('Event labels')
groupedLabel(range(10,21))
print('--------------------')


def singleLabel(labelIdx):
    S1,S2 = [],[]
    for i in range(len(R1)):
        if R1[i] == labelIdx or R2[i] == labelIdx:
            S1.append(R1[i])
            S2.append(R2[i])
    agree = 0
    if len(S1) == 0: return "this label is not exist"
    for j in range(len(S1)):
        if S1[j] == S2[j]:
            agree += 1
    print("the number of labels of this category: ",len(S1))
    return agree/len(S1)

for key,value in labels.items():
    print(key)
    print('Agreement of this label: ', singleLabel(value))
    print('-----')
