
def __main__():
    t = input("")

    for ts in range(0, t):
        aset = set()
        
        a = raw_input("").split(" ")
        b = raw_input("").split(" ")

        for k in a:
            aset.add(k)

        match = 0
        max_match = len(a)/2
        for item in b:
            if item in aset:
                match = match + 1
                if match >= max_match:
                    print "similar"
                    break

        if match < max_match:
            print "dissimilar"
                
                


__main__()
