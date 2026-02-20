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
    cboard = start
    cx = x
    cy = y
    cd = manhatt(start)
    steps = 0
    row = [-1, 1, 0, 0]
    col = [0, 0, -1, 1]

    while True:
        if is_goal(cboard):
            print(f"Goal Reached! Total steps: {steps}")
            return
        
        best_neighbour = None
        bestcd = cd
        
        for i in range(4):
            nx, ny = row[i] + cx, col[i] + cy
            if is_valid(nx, ny):
                nboard = [r[:] for r in cboard]
                nboard[cx][cy], nboard[nx][ny] = nboard[nx][ny], nboard[cx][cy]
                neighbour_dist = manhatt(nboard)
                
                if neighbour_dist < bestcd:
                    bestcd = neighbour_dist
                    best_neighbour = (nboard, nx, ny)
        
        if best_neighbour is None:
            print("Local optimum reached. No better neighbor found.")
            return
            
        cboard, cx, cy = best_neighbour
        cd = bestcd
        steps += 1
        print(f"Step {steps}: Moving to state with h = {cd}")

solve([[1, 2, 3], [4, 0, 5], [6, 7, 8]], 1, 1)