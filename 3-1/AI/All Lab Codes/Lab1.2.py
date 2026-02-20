from collections import deque

N = 3
goal_board = [[1, 2, 3], [4, 5, 6], [7, 8, 0]]

def is_valid(x, y):
    return 0 <= x < N and 0 <= y < N

def is_goal(board):
    return board == goal_board

def solve(start, x, y):
    queue = deque([(0, x, y, start)])
    visited = set()
    visited.add(tuple(map(tuple, start)))
    
    row = [-1, 1, 0, 0]
    col = [0, 0, -1, 1]
    
    while queue:
        cd, cx, cy, cboard = queue.popleft()
        
        if cd > 500:
            print("Limit Crossed")
            return
            
        if is_goal(cboard):
            print(f"Goal Reached! depth = {cd}")
            return
            
        for i in range(4):
            nx = cx + row[i]
            ny = cy + col[i]
            
            if is_valid(nx, ny):
                nboard = [r[:] for r in cboard]
                nboard[cx][cy], nboard[nx][ny] = nboard[nx][ny], nboard[cx][cy]
                immutableboard = tuple(map(tuple, nboard))
                
                if immutableboard not in visited:
                    visited.add(immutableboard)
                    queue.append((cd + 1, nx, ny, nboard))
                    
    print("No Solution Found")

solve([[1, 2, 3], [4, 0, 5], [6, 7, 8]], 1, 1)