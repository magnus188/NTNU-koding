import numpy as np

cal = []
temp = []

with open("data1.txt") as d:
    data = d.readlines()

for l in data:
    if l != "\n":
        temp.append(int(l))
        continue
    cal.append(sum(temp))
    temp = []

print("MAX: " + str(max(cal)))

sort = sorted(cal, reverse=True)
print(sort)
print("TOP 3: " + str(sum(sort[0:3])))
    

    
    