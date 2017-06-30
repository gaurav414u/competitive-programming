def isPalindrome(a, i, j, relx):
    if relx < 0:
        return False

    while(i<j):
        if a[i] == a[j]:
            i = i + 1
            j = j - 1
        else:
            return isPalindrome(a, i, j-1, relx-1) or isPalindrome(a, i+1, j, relx-1)
    if i == j:
        return True
    if i == j+1:
        return True


def __main__():
    t = input("")

    for ts in range(0, t):
        a = raw_input("")
        if isPalindrome(a, 0, len(a) - 1, 1):
            print "YES"
        else:
            print "NO"


__main__()
