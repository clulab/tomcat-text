"""
This script creates a file named 'taxonomy_map.json' which contains a mapping
between the labels produced by ODIN and the concepts in the Planning group's
taxonomy.

It creates this mapping by comparing the cosine angle between vector 
representations of the elements in different taxonomies. The vector
representations are created by averaging the token embeddings of all the words
comprising each taxonomy element.

The final map file is a json containing a list a of all the term mappings. 
Each element in the ODIN taxonomy is mapped to the 5 most similar elements 
in the Planning group taxonomy. The relative angles are also stored so other
systems can perform noisy matching.


Arguments:
 1) ODIN taxonomy which contains all labels to be used in yaml format
 2) Planning group taxonomy which contains variables they want to align
    to labels produced by ODIN system.
 3) Path to token embeddings. These embeddings will be used by the script
    to estimate the similarity between items in the two taxonomies. 
"""

import sys
import json
import re
import numpy as np
from heapq import nsmallest, heappush, heappop


def create_mapping(our_tax, their_tax, embedding_filepath):
    raw_json = ''
    with open(their_tax, 'r') as in_file:
        raw_json = json.load(in_file)
    possible_match = []
    possible_match += raw_json['Actions']
    possible_match += raw_json['Methods']

    raw_yaml = ''
    with open(our_tax, 'r') as in_file:
        raw_yaml = in_file
        needing_match = []
        regex = re.compile('[^a-zA-Z]')
        for line in raw_yaml:
            cur = line.strip()
            needing_match.append(regex.sub('', cur))

    temp = set()
    reverse_match_notation = dict()
    for ent in needing_match:
        if not ent.isupper():
            for i, char in enumerate(ent):
                if i > 0 and char.isupper():
                    break
            if i < len(ent)-1:
                norm_form = ent[:i].lower()+'-'+ent[i:].lower()
            else:
                norm_form = ent.lower()
            temp.add(norm_form)
            reverse_match_notation[norm_form] = ent

    needing_match = list(temp)
    possible_match = list(set(possible_match))
    print(needing_match)
    print(possible_match)

    numbers = []
    index = dict()
    with open(embedding_filepath, 'r') as in_file:
        for line in in_file:
            cur = line.strip()
            cur = cur.split(' ')
            index[cur[0]] = len(numbers)
            numbers.append([float(x) for x in cur[1:]])
    numbers = np.array(numbers)
    assert len(index) == len(numbers)

    scores = dict()
    for term in needing_match:
        scores[term] = []
        vecs = []
        for subterm in term.split('-'):
            if subterm in index:
                vecs.append(numbers[index[subterm], :])
        if not vecs:
            continue  # Skip terms not in our vocab
        term_avg = np.average(vecs, axis=0)
        for other in possible_match:
            vecs = []
            for subterm in other.split('-'):
                if subterm in index:
                    vecs.append(numbers[index[subterm], :])
            if vecs:
                other_avg = np.average(vecs, axis=0)
                score = np.linalg.norm(np.dot(term_avg, other_avg)) / \
                    (np.linalg.norm(term_avg)*np.linalg.norm(other_avg))
                if np.isnan(score):
                    continue
                heappush(scores[term],
                         (-score,
                          other
                          )
                         )
                if len(scores[term]) > 5:
                    scores[term] = nsmallest(
                        5, scores[term], key=lambda x: x[0])

    output_json = {}
    for term in scores:
        if not scores[term]:
            continue  # skip empty terms
        norm_term = reverse_match_notation[term]
        term_group = []
        for scoring in scores[term]:
            temp = {}
            temp['term'] = str(scoring[1])
            temp['score'] = str(-scoring[0])
            term_group.append(temp)
        output_json[term] = term_group

    json_mode = json.dumps(output_json, indent=4)

    with open('taxonomy_map.json', 'w') as out_file:
        out_file.write(str(json_mode))


if __name__ == "__main__":
    assert len(sys.argv) == 4
    create_mapping(sys.argv[1], sys.argv[2], sys.argv[3])
