import numpy as np

play = True
rowCount = 6
colCount = 7
turn = 1


# creating the board
def createBoard():
    board = np.zeros((rowCount, colCount))
    return board


# initialising board to createBoard function
board = createBoard()


# reversing the orientation of the matrix
# to the connect four upside down format
def reverseBoard(board):
    print(np.flip(board, 0))


# check the location which the player
# selected is a valid location
# means, to check weather the location
# is empty or not.
def isValidLoc(board, col):
    if (board[rowCount - 1, col] == 0):
        return True
    else:
        return False


# When the player drops a piece to the board
# it have to be in the loswest possible row
# This function returns the lowest possible
# row
def getTheLowestRow(board, col):
    for r in range(rowCount):
        if board[r][col] == 0:
            return r


# This function drop the piece to the board
# based on the column given by the player
# and row returened by the getTheLowestRow
# function
def dropPiece(board, row, col, piece):
    board[row][col] = piece


# Winning move
# to check all the possible starting possitions
# first and find the next position to  it and
# check it also the same piece , if it all
# sutes up then it return true
def winningMove(board, piece):
    # horizontal check
    for c in range(colCount - 3):
        for r in range(rowCount):
            if board[r][c] == piece and board[r][c + 1] == piece and board[r][c + 2] == piece and board[r][
                c + 3] == piece:
                return True
    # vertical check
    for c in range(colCount):
        for r in range(rowCount - 3):
            if board[r][c] == piece and board[r + 1][c] == piece and board[r + 2][c] == piece and board[r + 3][
                c] == piece:
                return True
    # positive diagonal check
    for c in range(colCount - 3):
        for r in range(rowCount - 3):
            if board[r][c] == piece and board[r + 1][c + 1] == piece and board[r + 2][c + 2] == piece and board[r + 3][
                c + 3] == piece:
                return True
    # negative diagonal check
    for c in range(colCount - 3):
        for r in range(3, rowCount):
            if board[r][c] == piece and board[r - 1][c + 1] == piece and board[r - 2][c + 2] == piece and board[r - 3][
                c + 3] == piece:
                return True


print("__________CONNECT FOUR___________")
# The main loop
reverseBoard(board)
while play:
    # getting input from the player 1
    if turn == 1:
        # modified while loop to perform like a do while loop
        while True:
            while True:
                col = int(input("Player 1 make your selection(0-6): "))
                if (0 > col or col > colCount):
                    print("ERROR 404 COLUMN NOT FOUND!")
                else:
                    break
            turn = 2

            if isValidLoc(board, col):
                row = getTheLowestRow(board, col)
                dropPiece(board, row, col, 1)
                break
            else:
                print("ERROR 503 THE COLUMN IS FULL")
        reverseBoard(board)
        if winningMove(board, 1):
            print("Player 1 win!!!")
            play = False


    else:
        # getting input from the player 2
        # modified while loop to perform like a do while loop
        while True:
            while True:
                col = int(input("Player 2 make your selection(0-6): "))
                if (0 > col or col > colCount):
                    print("ERROR 404 COLUMN NOT FOUND!")

                else:
                    break
            turn = 1

            if isValidLoc(board, col):
                row = getTheLowestRow(board, col)
                dropPiece(board, row, col, 2)
                break
            else:
                print("ERROR 503 THE COLUMN IS FULL")
        reverseBoard(board)
        if winningMove(board, 2):
            print("Player 2 win!!!")
            play = False
