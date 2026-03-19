import sys
from collections import deque

input_data = sys.stdin.read().strip().split("\n")

data = deque(input_data)
caseNum = int(data.popleft())


def dfs(x, y, graph, M, N):
  dx = [0, 0, -1, 1]
  dy = [-1, 1, 0, 0]
  stack = [(x,y)] # 1-1. 스택 초기화
  visited[y][x] = True # 1-2. 시작의 경우 일단 방문가능
  while stack: 
    x, y = stack.pop() # 2. 체크할 위치 -> LIFO
    for d in range(4):
      nx, ny = x+dx[d], y+dy[d]
      if 0<=nx<M and 0<=ny<N:
        if graph[ny][nx] == 1 and not visited[ny][nx]:
          visited[ny][nx]=True
          stack.append((nx, ny))
          

# visited = [[False]*M for _ in range(N)]


def bfs(x ,y, graph, M, N):
  dx = [0, 0, -1, 1]
  dy = [-1, 1, 0, 0]
  queue = deque()
  queue.append((x,y)) # 1-1. 큐 초기화
  visited[y][x] = True # 1-2. 시작의 경우 일단 방문가능
  
  while queue:
      x, y = queue.popleft() # 2. 체크할 위치 -> FIFO
      for d in range(4):
        nx = x+dx[d]
        ny = y+dy[d]
        if 0<=nx<M and 0<=ny<N:
          if graph[ny][nx] == 1 and not visited[ny][nx]:
            visited[ny][nx]=True
            queue.append((nx, ny))



for _ in range(caseNum):
  M, N, K = map(int, data.popleft().split())
  graph = [[0]*M for _ in range(N)] # M이 row , N이 칼럼인데 배열상 칼럼 인덱스가 먼저 필요
  for _ in range(K):
    x, y = map(int,data.popleft().split())
    graph[y][x]=1
    
  visited = [[False]*M for _ in range(N)]



  count = 0
  for y in range(N):
    for x in range(M):
      if graph[y][x] == 1 and not visited[y][x]:
        bfs(x,y,graph,M,N)
        count += 1
  print(count);
    