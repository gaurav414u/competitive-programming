
def __main__():
    t = input("")

    for ts in range(0, t):
        ingsAdded = set()
        numIngsAdded = 0
        
        line = raw_input("").split(" ")
        n = int(line[0])
        k = int(line[1])

        some = False
        for i in range (0, n):
            line = raw_input("").split(" ")
            pi = int(line[0])

            if (numIngsAdded >= k):
                some = True
                continue

            for l in range (1, pi + 1):
                ing = line[l]
                if ing in ingsAdded:
                    pass
                else:
                    ingsAdded.add(ing)
                    numIngsAdded = numIngsAdded + 1
        if some:
            print "some"
            continue

        if numIngsAdded >= k:
            print "all"
        else:
            print "sad"



__main__()
