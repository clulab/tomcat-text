'''
    Generate tags from tags.txt file
    
    Tags should be in the format
    Group 1:
    Tag 1
    Tag 2
    Tag 3
    Group 2:
    ...

'''

# Read tags from tags.txt
f = open("tags.txt")
lines = f.readlines()
lines = [element.rstrip() for element in lines]
f.close()

# Generate tags
current_group = lines[0].replace(":", "")
print("<View classname='label_group'>")
print(f"<Header name='{current_group}' value='{current_group}' size='3'/>")
for i, line in enumerate(lines):
    if ":" in line and i != 0:
        current_group = lines[i].replace(":", "")
        print("</View>")
        print("<View className='label_group'>")
        print(f"<Header name='{current_group}' value='{current_group}' size='3'/>")
    if not ":" in line: 
        print(f"<Label alias='{i}' value='{line}'/>")
print("</View>")
