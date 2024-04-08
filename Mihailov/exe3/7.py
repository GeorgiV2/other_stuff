import math

n = int(input())
left_sum = 0
right_sum = 0

for i in range(0,n):
        nums = float(input())
        left_sum+= nums

for i in range(0,n):
        nums1 = float(input())
        right_sum+= nums1

if left_sum == right_sum:
    print("yes, sum  = %d" % left_sum)
else:
    print("no, diff = %d " % math.fabs(right_sum - left_sum))