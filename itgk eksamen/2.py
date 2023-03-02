win = 6

scores = {
    'X': 1,
    'Y': 2,
    'Z': 3
}

# Win, draw, loose
rules = {
    'A': ['Y', 'X', 'Z'],
    'B': ['Z', 'Y', 'X'],
    'C': ['X', 'Z', 'Y']
}

totScore = 0
newTot = 0


with open('data2.txt') as d:
    for l in d:
        elf, resp = l.strip().split(" ")[0:2]
        if rules[elf][0] == resp:
            # We won
            totScore += win + scores[resp]
        elif rules[elf][1] == resp:
            # Draw
            totScore += 3 + scores[resp]
        else:
            # Loss
            totScore += scores[resp]

with open('data2.txt') as d:
    for l in d:
        elf, resp = l.strip().split(" ")
        if resp == "X":
            # Loose!
            newTot += scores[rules[elf][2]]
        elif resp == "Y":
            # Draw
            newTot += 3 + scores[rules[elf][1]]
        else:
            # Win
            newTot += 6 + scores[rules[elf][0]]


print(totScore)
print(newTot)
