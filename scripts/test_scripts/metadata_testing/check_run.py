import argparse
import subprocess
import sys

# Authors:  Joseph Astier, Adarsh Pyarelal

def check_file(filename):

    print(f'Checking file: {filename}')

    # download the file
    completed_process = subprocess.run(['gsutil', '-m', 'cp', filename, '.'])

    if completed_process.returncode == 0:
        # test the file

        # add results to global test report

        # delete the file

    else:
        print(completed_process.stderr.decode('utf-8'))



# return the filenames from one study
def get_study_filenames(study):
    filenames = []
    src=f'gs://studies.aptima.com/study-3_2022/*{study}*.metadata'
    completed_process = subprocess.run(['gsutil', 'ls', src], capture_output=True)
    if completed_process.returncode == 0:
        output=completed_process.stdout.decode('utf-8')
        lines = output.split('\n')
        for line in lines:
            if len(line) > 0:
                filenames.append(line)

    else:
        print(completed_process.stderr.decode('utf-8'))

    return filenames


# return the filenames from all studies
def get_filenames(studies):
    
    all_filenames = []
    for study in studies:
        print(f'Files found in {study}:')
        study_filenames = get_study_filenames(study)

        for filename in study_filenames:
            print(f'  {filename}')
            all_filenames.append(filename)

    print(f'Total .metadata files found: {len(all_filenames)}')
    return all_filenames


# download metadata studies and validate using testbed agent test scripts
if __name__ == '__main__':

    parser = argparse.ArgumentParser()
    parser.add_argument('studies', 
        action='store', 
        nargs = '+',
        help = 'One or more *.metadata studies') 
    args = parser.parse_args(sys.argv[1:])

    filenames = get_filenames(args.studies)

    for filename in filenames:
        check_file(filename)

