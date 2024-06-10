def count_rectangles(points, A, B):
    point_set = set(points)
    count = 0

    for (x, y) in points:
        # Check if the other three points needed to form the rectangle exist
        if (x + A, y) in point_set and (x, y + B) in point_set and (x + A, y + B) in point_set:
            count += 1

    return count

# 입력 받기
import sys
input = sys.stdin.read
data = input().split()

N = int(data[0])
A = int(data[1])
B = int(data[2])

points = []
for i in range(N):
    x = int(data[3 + 2 * i])
    y = int(data[4 + 2 * i])
    points.append((x, y))

# 가능한 모든 경우의 수 계산
result = count_rectangles(points, A, B)

# 결과 출력
print(result)