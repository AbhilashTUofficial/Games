import math
import sys

import numpy as np
import pygame

colCount = 7
rowCount = 6
turn = 1
play = True


# creating the board
def createBoard():
    board = np.zeros((rowCount, colCount))
    return board


# initialising board to createBoard function
board = createBoard()


# reversing the orientation of the matrix
# to the connect four upside down format
def reverseBoard(board):
    board = (np.flip(board, 0))
    return board


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


# draw screen
pygame.init()
squareSize = 100
width = colCount * squareSize
height = (rowCount + 1) * squareSize

size = (width, height)
radius = int(squareSize / 2 - 5)
screen = pygame.display.set_mode(size);
font=pygame.font.SysFont("monospace",75)

# draw grids
def drawGrid(board):
    for c in range(colCount):
        for r in range(rowCount):
            pygame.draw.rect(screen, (0, 0, 255), (c * squareSize, r * squareSize + squareSize, squareSize, squareSize))
            if board[r][c] == 1:
                pygame.draw.circle(screen, (255, 0, 0), (
                    int(c * squareSize + squareSize / 2), int(r * squareSize + squareSize + squareSize / 2)), radius)
            elif board[r][c] == 2:
                pygame.draw.circle(screen, (0, 255, 0), (
                    int(c * squareSize + squareSize / 2), int(r * squareSize + squareSize + squareSize / 2)), radius)
            else:
                pygame.draw.circle(screen, (0, 0, 0), (
                    int(c * squareSize + squareSize / 2), int(r * squareSize + squareSize + squareSize / 2)), radius)
    pygame.display.update()


# gameloop
while play:
    drawGrid(reverseBoard(board))
    pygame.display.update()

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            sys.exit()
        if event.type == pygame.MOUSEMOTION:
            posX = event.pos[0]
            pygame.draw.rect(screen, (0, 0, 0), (0, 0, width, squareSize))
            if (turn == 1):
                pygame.draw.circle(screen, (255, 0, 0), (posX, int(squareSize / 2)), radius)
            elif (turn == 2):
                pygame.draw.circle(screen, (0, 255, 0), (posX, int(squareSize / 2)), radius)
            pygame.display.update()
        if event.type == pygame.MOUSEBUTTONDOWN:

            # getting input from the player 1
            if turn == 1:
                posX = event.pos[0]
                col = int(math.floor((posX / squareSize)))
                turn = 2

                if isValidLoc(board, col):
                    row = getTheLowestRow(board, col)
                    dropPiece(board, row, col, 1)

                if winningMove(board, 1):
                    pygame.draw.rect(screen, (0, 0, 0), (0, 0, width, squareSize))
                    label=font.render("Player 1 wins!!!",1,(255,0,0))
                    screen.blit(label,(40,10))
                    play = False

                # getting input from the player 2
            else:

                posX = event.pos[0]
                col = int(math.floor((posX / squareSize)))

                turn = 1

                if isValidLoc(board, col):
                    row = getTheLowestRow(board, col)
                    dropPiece(board, row, col, 2)

                if winningMove(board, 2):
                    pygame.draw.rect(screen, (0, 0, 0), (0, 0, width, squareSize))
                    label = font.render("Player 2 wins!!!", 1, (0, 255, 0))
                    screen.blit(label, (40, 10))
                    play = False
    if (not play):
        drawGrid(reverseBoard(board))
        pygame.time.wait(3000)
