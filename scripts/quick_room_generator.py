letters = ["a","b","c","d","e","f","g","h","i","j","k","l","m"]

writefile = open("study3roomsprocedural.yml", "w+", encoding="utf-8")

for letter in letters:
	for i in range(1,10):
		writefile.write("- name: room"+letter+str(i))
		writefile.write("\n")
		writefile.write('  priority: ${ second_priority }')
		writefile.write("\n")
		writefile.write("  label: Room"+letter.upper()+str(i))
		writefile.write("\n")
		writefile.write("  type: token")
		writefile.write("\n")
		writefile.write("  keep: true")
		writefile.write("\n")
		writefile.write("  pattern: |")
		writefile.write("\n")
		writefile.write(f"          [lemma=/(?i)^room/]? [lemma=/(?i)^{letter}-?{i}$/]")
		writefile.write("\n")
		writefile.write("\n")

writefile.close()