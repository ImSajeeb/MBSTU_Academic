import heapq

N = 3
GOAL_BOARD = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 0]
]

GOAL_POSITIONS = {}
for r in range(N):
    for c in range(N):
        value = GOAL_BOARD[r][c]
        if value != 0:
            GOAL_POSITIONS[value] = (r, c)

def is_valid(x, y):
    return 0 <= x < N and 0 <= y < N

def is_goal_state(board):
    return board == GOAL_BOARD

def manhat(board):
    dist = 0
    for r in range(N):
        for c in range(N):
            val = board[r][c]
            if val != 0:
                tr, tc = GOAL_POSITIONS[val]
                dist += abs(r - tr) + abs(c - tc)
    return dist

def solve(start, x, y):
   
    pq = []
    visited = set()
    
    initial_cost = manhat(start)
    heapq.heappush(pq, (initial_cost, 0, x, y, start))
    visited.add(tuple(map(tuple, start)))
    
    row = [-1, 1, 0, 0]
    col = [0, 0, -1, 1]
    
    while pq:
        curr_cost, curr_depth, curr_x, curr_y, curr_board = heapq.heappop(pq)
        
        if is_goal_state(curr_board):
            print("Goal State Reached!")
            print(f"Total Steps (Depth): {curr_depth}")
            print("Final Board Configuration:")
            for r in curr_board:
                print(r)
            return
            
        for i in range(4):
            new_x = curr_x + row[i]
            new_y = curr_y + col[i]
            
            if is_valid(new_x, new_y):
                new_board = [r[:] for r in curr_board]
               
                new_board[curr_x][curr_y], new_board[new_x][new_y] = \
                    new_board[new_x][new_y], new_board[curr_x][curr_y]
                
                board_tuple = tuple(map(tuple, new_board))
                
                if board_tuple not in visited:
                    visited.add(board_tuple)
                    dist = manhat(new_board)
                    
                    heapq.heappush(pq, (dist, curr_depth + 1, new_x, new_y, new_board))
                    
    print("No solution found.")

solve([[1, 2, 3], [4, 0, 5], [6, 7, 8]], 1, 1)