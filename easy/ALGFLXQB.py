t = input("")
for i in range(0,t):
    chars = raw_input()

    max_count = 0
    count = 0
    for k in range(0,len(chars)):
        if chars[k]=="1":
            count = count + 1
            if (count > max_count):
                max_count = count
        else:
            count = 0
    print max_count

