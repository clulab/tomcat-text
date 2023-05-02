pdfdoc: extraction_schemas.pdf
htmldoc: extraction_schemas.html

# Dialogue extractions from Zoom transcripts from the Fall 2020 ASIST
# experiment
data/fall2020_extractions.txt: scripts/run_fall2020_analysis
	$< $@

extraction_schemas.json: scripts/build_docs
	$<

extraction_schemas.html: scripts/process_extraction_schemas_json extraction_schemas.json version.sbt
	$^ $@
