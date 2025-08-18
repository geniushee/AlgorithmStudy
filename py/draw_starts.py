# def conqure(board, row, col):
#     if row % 3 == 1 and col % 3 == 1:
#         board[row][col]= " "
#         return
    
#     return 0

def divide(board, start_row, end_row, start_col, end_col):
    # print("cur:", start_row, end_row, start_col, end_col)
    if end_row - start_row < 1 and end_col - start_col < 1:
        board[start_row][start_col] = "*"
        return
    
    # print("row arrays")
    mod_row = (end_row - start_row + 1) // 3
    start_rows = [start_row + a * mod_row for a in range(3)]
    # print(start_rows)
    end_rows = [start_row + a * mod_row - 1 for a in range(1,4)]
    # print(end_rows)

    # print("col arrays")
    mod_col = (end_col - start_col + 1) // 3
    start_cols = [start_col + a * mod_col for a in range(3)]
    # print(start_cols)
    end_cols = [start_col + a * mod_col - 1 for a in range(1,4)]
    # print(end_cols)

    for r in range(3):
        for c in range(3):
            if r == 1 and c == 1:
                continue
            divide(board, start_rows[r],end_rows[r],start_cols[c],end_cols[c])

n = int(input())

board = [[" " for _ in range(n)] for _ in range(n)] # board초기화
# print(board) 

divide(board, 0, n, 0, n)
for row in board:
    print("".join(row))