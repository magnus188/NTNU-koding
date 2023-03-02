import re
from string import ascii_letters

tot = 0
tot2 = 0
data = []
with open("data3.txt") as d:
    data = d.readlines()

for l in data:
    similar = ', '.join(set(l[0:len(l)//2]).intersection(set(l[len(l)//2:])))
    tot += int(ord(similar)) - (96 if re.match("[a-z]", similar) else 38)

print(tot)


j = 3
for i in range(0, len(data), 3):
    entry = data[i:j]
    j += 3

    for value, character in enumerate(ascii_letters):
        if character in entry[0] and character in entry[1] and character in entry[2]:
            tot2 += value + 1
print(tot2)

