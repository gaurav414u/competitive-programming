
def __main__():
    t = input("")

    for ts in range(0, t):
        
        a = list(raw_input(""))
        n = len(a)
        done = False
        time = 0
        itr = 0
        while not done:
            selected = False
            done = True
            for i in range (0, n-1-itr):
                #print a[i], a[i+1]
                #print a
                if a[i] == '1' and a[i+1] == '0':
                    done = False
                    if not selected:
                        #if already not selected, then select and increment extra time for selection
                        time = time + 1
                        selected = True
                    time = time + 1
                    a[i] = '0'
                    a[i+1] = '1'
                else:
                    selected = False
            itr = itr + 1
        print time

                

__main__()
