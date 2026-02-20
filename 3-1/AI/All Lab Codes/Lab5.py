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
    visited = {}
    
    initial_h = manhatt(start)
    
    heapq.heappush(pq, (initial_h, 0, x, y, start))
    visited[tuple(map(tuple, start))] = initial_h
    
    row, col = [-1, 1, 0, 0], [0, 0, -1, 1]
    
    while pq:
        curr_cost, curr_depth, curr_x, curr_y, curr_board = heapq.heappop(pq)
        
        if is_goal(curr_board):
            print(f"Goal found! Actual steps: {curr_depth}, AO* Cost: {curr_cost}")
            return
            
        best_child_cost = float('inf')
        for i in range(4):
            new_x, new_y = curr_x + row[i], curr_y + col[i]
            
            if is_valid(new_x, new_y):
                new_board = [r[:] for r in curr_board]
                # Swap the empty tile (0) with the neighbor
                new_board[curr_x][curr_y], new_board[new_x][new_y] = \
                    new_board[new_x][new_y], new_board[curr_x][curr_y]
                
                mutableboard = tuple(map(tuple, new_board))
                
                if mutableboard not in visited:
                    dist = manhatt(new_board)
                    visited[mutableboard] = dist
                    # Push with depth + 1
                    heapq.heappush(pq, (dist, curr_depth + 1, new_x, new_y, new_board))
                
                best_child_cost = min(best_child_cost, 1 + visited[mutableboard])
        
        parent_tuple = tuple(map(tuple, curr_board))
        if best_child_cost != float('inf'):
            visited[parent_tuple] = best_child_cost
            
    print("No solution")

solve([[1, 2, 3], [4, 0, 6], [7, 5, 8]], 1, 1)