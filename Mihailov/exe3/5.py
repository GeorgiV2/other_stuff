n = int(input('n = '))
max_num = -10000000000000

for i in range(n):
    num = int(input())
    if num > max_num:
        max_num = num

print("max num is: " + max_num)