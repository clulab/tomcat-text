
Automated testing of Testbed metadata.

This script will quantitatively analyze Testbed metadata, using uaz_dialog_agent.py   

Usage is "validate_testbed_runs " followed by an arbitrary number of Testbed run names

e.g.: "validate_testbed_runs TM000283 TM000286 TM000296"


Testing will create (or overwrite) in this directory:
  * A directory for each of the run names containing:
    -  the data files downloaded from the Google drive
    -  A test results file 'test_results.txt'
  * A summary of all test results 'all_results.txt' 


REQUIREMENTS:
  * You must have gsutil access to the Testbed metadata google drive.
  * Suffient local file storage space for the downloaded metadata files.

