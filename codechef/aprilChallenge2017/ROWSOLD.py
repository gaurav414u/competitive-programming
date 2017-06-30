
def __main__():
    t = input("")

    for ts in range(0, t):
        a = raw_input("")
        n = len(a)
        time = 0
        count1 = 0
        i = 0
        while i < n-1:
            if a[i] == '1':
                count1 = count1 + 1

                if a[i + 1] == '0':
                    j = i+1
                    for j in range (i + 1, n):
                        if j + 1 < n and a[j+1] != '0':
                            break
                        else:
                            continue
                    time = time + (j - i + 1) * count1
                    i = j
            i=i+1

	print ts

__main__()
