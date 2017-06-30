import math

def main():
    t = input("")
    for ts in range(0, t):
        lists = []
        line = raw_input("").split(" ")

        n = int(line[0]) #Length of the ranklist
        s = int(line[1]) #Sum of the rank list
        if (n == s):
            print n-1
            continue

        j = 1
        answer = 0

        x = float((math.sqrt( 8*(s-n)+1 ) + 1))/2
        print "%d"%(n-math.floor(x))

main()
