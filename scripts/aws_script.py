import boto3
import os
from time import sleep
import requests


"""
To run this script you need a config file at:
~/.aws/config
The file should look like:

[default]
aws_access_key_id = YOUR_ACCESS_KEY
aws_secret_access_key = YOUR_SECRET_KEY
"""



transcribe_client = boto3.client('transcribe')
s3_bucket = f"BUCKETNAME"

cur_dir = os.fsencode('./data/')
for cur in os.listdir(cur_dir):
    dir_name = os.fsdecode(cur)
    for mission_f in os.listdir(cur_dir+cur):
        mission_name = os.fsdecode(mission_f)
        player_audio = f'./data/{dir_name}/{mission_name}/player_audio.wav'
        if mission_name == 'metadata.json' or not os.path.exists(player_audio):
            continue
        job_name = f'{dir_name}_{mission_name}.wav'
        print(f'Starting transcription of: {s3_bucket}Search/{job_name}')
        transcribe_client.start_transcription_job(
            TranscriptionJobName=job_name,
            Media={'MediaFileUri': s3_bucket+'Search/'+job_name},
            MediaFormat='wav',
            LanguageCode='en-US',
            Settings={'VocabularyName':'TomcatVocab'}
        )

        transcription_filepath = f'./data/{dir_name}/{mission_name}_transcript.txt'

        while True:
            status = transcribe_client.get_transcription_job(TranscriptionJobName=job_name)
            job_status = status['TranscriptionJob']['TranscriptionJobStatus']
            if job_status in ['COMPLETED', 'FAILED']:
                break
            sleep(1)
        result_url = status['TranscriptionJob']['Transcript']['TranscriptFileUri']
        req = requests.get(url=result_url)
        result_json = req.json()
        text = result_json['results']['transcripts'][0]['transcript']
        with open(transcription_filepath, 'w') as out_file:
            out_file.write(text)
        print(f'Collected transcript for file {dir_name}_{mission_name}')
        
