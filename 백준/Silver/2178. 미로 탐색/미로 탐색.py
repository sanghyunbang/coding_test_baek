import sys
from collections import deque

input_data = sys.stdin.read().strip().split("\n")

data = input_data

movable = {}
dist ={}
visited = {}

N, M = map(int,data[0].split())

#1. 초기화 : input 데이터를 이용 가능하게 변수와 배열로 담아두기
for i in range(N):
  row = list(map(int, data[i+1]))
  for j in range(M):
    dist[(i+1,j+1)]=0
    visited[(i+1,j+1)]=0

    if row[j]==1:
      movable[(i+1,j+1)]=1
    else:
      movable[(i+1,j+1)]=0

queue=deque()
queue.append((1,1))
movable[(1,1)]=1
dist[(1,1)]=1


#2. 본격적으로 dist찾기 -> 상하좌우 네 가지 경우를 고려
startNode = (1,1)
queue.append(startNode)
visited[(1,1)] = 1

directions = [(0,1), (1,0), (0,-1), (-1,0)]



while queue:
  parentNode = queue.popleft() #선입선출로 체크 -> 기준 노드
  for direction in directions:
    checkedNode = tuple(x+y for x, y in zip(direction, parentNode))
    if checkedNode in movable and movable[checkedNode] == 1 and visited[checkedNode] == 0:
        queue.append(checkedNode)
        visited[checkedNode] = 1
        dist[checkedNode] = dist[parentNode] + 1

print(dist[(N, M)])
