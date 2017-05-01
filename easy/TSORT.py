def sort():
    print "sort()"

def main():
    ink = raw_input("")
    arr = ink.split(" ")
    amt = float(arr[0])
    bal = float(arr[1])
    if amt % 5 != 0:
        print "%.2f" % bal
        return

    if bal < amt + 0.5:
        print "%.2f" % bal
        return
    print "%.2f" % (bal - (amt + 0.5))


if __name__ == "__main__": main()
