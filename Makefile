all: data/fall2020_extractions.txt

# Dialogue extractions from Zoom transcripts from the Fall 2020 ASIST
# experiment
data/fall2020_extractions.txt: scripts/run_fall2020_analysis
	$< $@
