'''
    Generate input json for label-studio tasks 
'''

import json
import pandas as pd
xls = pd.ExcelFile("Game1_xls/Team2052_Transcription_Game1.xls")
sheet = xls.parse(0)

transcriptions = sheet['Utterance']
start_times = sheet['Timestamp_Start']
end_times = sheet['Timestamp_End']
unique_audio_urls = set([element.replace("./","/label-studio/files/croppedAudioFiles/") for element in sheet['Audio_Filename']) 
audio_urls = [element.replace("./","/label-studio/files/croppedAudioFiles/") for element in sheet['Audio_Filename'] 

output = []
for i in range(100):
    task = {"data":{"transcription_data": {"transcript": transcriptions[i], "start_time": start_times[i], "end_time": end_times[i]}, "audio_url": audio_urls[i]}}
    output.append(task)

print(json.dumps(output))
