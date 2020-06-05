#    Tic Tac Toe

#######################
#      #       #      #
#      #       #      #
#######################
#      #       #      #
#      #       #      #
#######################
#      #       #      #
#      #       #      #
#######################


import math
import sys
import numpy as np
import pygame




play = True


# create board
colCount = 3
rowCount = 3
board = np.zeros((rowCount, colCount))
turn = 1


# checking for win
def winningMove(board, turn):
    return False


# draw screen variables
pygame.init()
width = 600
height = 600
screen = pygame.display.set_mode((width + 30, height + 30), )

# draw box variables
boxWidth = (int)(width / 3)
boxHeight = (int)(height / 3)
startDrawX = 210
startDrawY = 210

# draw cursor variables
cursorSize = 15
dotSize = 70




while play:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            sys.exit()
            play = False
        # draw screen
        # the blue screen behind the boxes
        pygame.draw.rect(screen, (4, 23, 120), (0, 0, width + 30, height + 30))

        # draw box
        # box is the solts for entering the dots
        for c in range(3):
            for r in range(3):
                pygame.draw.rect(screen, (200, 200, 200), (c * startDrawX + 5, r * startDrawY + 5, boxWidth, boxHeight))
                pygame.draw.rect(screen, (250, 250, 250),
                                 (c * startDrawX + 20, r * startDrawY + 15, boxWidth - 30, boxHeight - 30))

            # draw cursor
            # cursor have two colors which indicates the
            # turns
        if event.type == pygame.MOUSEMOTION:
            cursorX = event.pos[0]
            cursorY = event.pos[1]
            if turn == 1:
                pygame.draw.circle(screen, (255, 0, 0), (cursorX, cursorY), cursorSize)

            if turn == 2:
                pygame.draw.circle(screen, (0, 255, 0), (cursorX, cursorY), cursorSize)


        # on the mouseclick of the player
        if event.type == pygame.MOUSEBUTTONDOWN:
            # checking the turns
            if turn == 1:
                col = int(math.floor((cursorX / 210)))
                row = int(math.floor((cursorY / 210)))
                print(str(col) + "col")
                print(row)
                print(str(turn) + "turns")
                # checking that the selected slot
                # is empty
                if (board[row][col] == 0):
                    board[row][col] = turn

                    #checking that the move was an winning move
                    if winningMove(board, turn):
                        print("win")
                    else:
                        # changing the turns
                        turn = 2
                        print("not win")
                else:
                    pass

            # checking the turns
            elif turn == 2:
                col = int(math.floor((cursorX / 210)))
                row = int(math.floor((cursorY / 210)))
                print(col)
                print(row)
                # checking that the selected slot
                #is empty
                if (board[row][col] == 0):
                    board[row][col] = turn
                # checking that the move was an winning move
                    if winningMove(board, turn):
                        print("win")
                    else:
                        # changing the turns
                        turn = 1
                        print("not win")

        # loops through all the slots
        for c in range(colCount):
            for r in range(rowCount):
                # checking for the dot
                if board[r][c] == 1:
                    # drawing the dot
                    pygame.draw.circle(screen, (255, 0, 0),
                                       (int(c * startDrawX + startDrawX / 2), int(r * startDrawY + startDrawY / 2)),
                                       dotSize)
                # checking for the dot
                if (board[r][c] == 2):
                    # drawing the dot
                    pygame.draw.circle(screen, (0, 255, 0),
                                       (int(c * startDrawX + startDrawX / 2), int(r * startDrawY + startDrawY / 2)),
                                       dotSize)

    pygame.display.update()
