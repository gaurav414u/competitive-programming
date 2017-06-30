
line = raw_input("")
arr = line.split(" ")
n = int(arr[0])
k = int(arr[1])
count = 0
for i in range(0,n):
    t = int(input(""))
    if t % k == 0:
        count = count + 1

print count

