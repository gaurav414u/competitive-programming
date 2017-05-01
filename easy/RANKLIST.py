t = input("")

a = [0] * 100000

for ts in range(0, t):

    lists = []
    line = raw_input("").split(" ")

    n = int(line[0]) #Length of the ranklist
    s = int(line[1]) #Sum of the rank list

    leftOutSum = 1
    ans = 0

    for x in range (1, n+1):
        if (n - x + leftOutSum <= s):
            a[x] = leftOutSum
            s = s - leftOutSum
            leftOutSum = leftOutSum + 1
        else:
            ans = n- (x-1)#it would be older x
            break

    print ans



