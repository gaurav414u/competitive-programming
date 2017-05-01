import sys
t = input("")

for ts in range (0, t):
    ans = []
    line = raw_input("").split(" ")
    n = int(line[0]) #Number of employees
    used = [False] * n
    m = int(line[1]) #Number of compatible pairs
    u = [0] * m
    v = [0] * m
    
    for mi in range (0,m):
        line = raw_input("").split(" ")
        u[mi] = int(line[0])
        v[mi] = int(line[1])

    for mri in range (m-1, -1, -1):
        if (used[u[mri]] == False and used[v[mri]] == False):
            ans.append(mri)

            used[u[mri]] = True
            used[v[mri]] = True

    for ai in range(len(ans) - 1, -1, -1):
        k = str(ans[ai])
        sys.stdout.write(k + " ")
        sys.stdout.flush()



"""
t..
    n, m
    m..
        ui, vi 

"""
