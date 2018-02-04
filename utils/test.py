def permutate(start, arr):
    if start == len(arr):
        print arr

    for i in range(start, len(arr)):
        arr[start] , arr[i] = arr[i], arr[start]
        permutate(start+1, arr)
        arr[start] , arr[i] = arr[i], arr[start]

def C(arr, r):
    comb(0, 0, len(arr), r, [], arr)


def comb(start, c ,n, r, rarr, arr):
    if c == r:
        print rarr

    for i in range(start,n):
        newarr = list(rarr)
        newarr.append(arr[i])
        comb(i+1, c+1, n, r, newarr, arr)



#permutate(0, ['a', 'b', 'c', 'd'])
#permutate(0, [1, 2, 3])


C(['a', 'b', 'c', 'd'], 4)
