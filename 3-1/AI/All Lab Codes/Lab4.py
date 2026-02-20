import heapq

N = 3
goal_board = [[1, 2, 3], [4, 5, 6], [7, 8, 0]]
goal_pos = {}

for r in range(N):
    for c in range(N):
        val = goal_board[r][c]
        if val != 0:
            goal_pos[val] = (r, c)

def is_valid(x, y):
    return 0 <= x < N and 0 <= y < N

def is_goal(board):
    return board == goal_board

def manhatt(board):
    dist = 0
    for r in range(N):
        for c in range(N):
            val = board[r][c]
            if val != 0:
                tr, tc = goal_pos[val]
                dist += abs(r - tr) + abs(c - tc)
    return dist

def solve(start, x, y):
    pq = []
    visited = set()
    
    initial_h = manhatt(start)
    depth = 0
    # Priority queue stores (f_cost, g_depth, x, y, board)
    heapq.heappush(pq, (initial_h + depth, depth, x, y, start))
    visited.add(tuple(map(tuple, start)))
    
    row = [-1, 1, 0, 0]
    col = [0, 0, -1, 1]
    
    while pq:
        curr_cost, curr_depth, curr_x, curr_y, curr_board = heapq.heappop(pq)
        
        if is_goal(curr_board):
            print(f"goal is found : {curr_depth}")
            return
            
        for i in range(4):
            new_x = curr_x + row[i]
            new_y = curr_y + col[i]
            
            if is_valid(new_x, new_y):
                new_board = [r[:] for r in curr_board]
                # Swap the empty tile (0) with the neighbor
                new_board[curr_x][curr_y], new_board[new_x][new_y] = \
                    new_board[new_x][new_y], new_board[curr_x][curr_y]
                
                immutable_board = tuple(map(tuple, new_board))
                
                if immutable_board not in visited:
                    visited.add(immutable_board)
                    dist = manhatt(new_board)
                    new_depth = curr_depth + 1
                    f_total_cost = new_depth + dist
                    heapq.heappush(pq, (f_total_cost, new_depth, new_x, new_y, new_board))
                    
    print("No solution")

solve([[1, 2, 3], [4, 0, 5], [6, 7, 8]], 1, 1)