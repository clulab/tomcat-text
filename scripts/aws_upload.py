import json
import os
import boto3
from botocore.exceptions import ClientError

"""
To run this script you need a config file at:
~/.aws/config
The file should look like:

[default]
aws_access_key_id = YOUR_ACCESS_KEY
aws_secret_access_key = YOUR_SECRET_KEY
"""

s3 = boto3.client('s3')
bucket = 'BUCKETNAME'

cur_dir = os.fsencode('./data/')
for cur in os.listdir(cur_dir):
    dir_name = os.fsdecode(cur)
    print(f'Processing files from: {dir_name}')
    for mission_f in os.listdir(cur_dir+cur):
        mission_f_name = os.fsdecode(mission_f)
        if mission_f_name[:-1] != 'mission_':
            continue
        file_path = './data/'+dir_name+'/'+mission_f_name+'/player_audio.wav'
        if not os.path.exists(file_path):
            continue
        print(f' Uploading file: {file_path}')
        save_name = f'Search/{dir_name}_{mission_f_name}.wav'
        try:
            response = s3.upload_file(file_path, bucket, save_name)
        except ClientError as e:
            print(e)
            raise
        print('    Done')
