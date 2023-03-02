data = [
    
]




def no_common_letters(L):
    result = []

    for e in L:
        string1 = list(e[0])
        string2 = list(e[1])

        if not(string1 in string2 or string2 in string1):
            result.append([string1, string2])
    return result
