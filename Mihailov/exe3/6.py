n = int(input('n = '))
min_num = 10000000000000

for i in range(n):
    num = int(input())
    if num < min_num:
        min_num = num

print("min num is: " + min_num)