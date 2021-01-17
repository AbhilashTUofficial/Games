import pygame
from .consts import black,white,red,cols,rows,squareSize,cols
from .piece import Piece

class Board:
    def __init__(self):
        self.board=[]
        self.selectedPiece=None
        self.redLeft=whiteLeft=12
        self.redKing=whiteKing=0
        self.createBoard()

    def drawSqaure(self,win):
        win.fill(black)
        for row in range(rows):
            for col in range(row%2,cols,2):
                pygame.draw.rect(win,red,(row*squareSize,col*squareSize,squareSize,squareSize))
    def createBoard(self):
        for row in range(rows):
            self.board.append([])
            for col in range(cols):
                if col % 2==((row+1)%2):
                    if row<3:
                        self.board[row].append(Piece(row,col,white))
                    elif row>4:
                        self.board[row].append(Piece(row,col,red))
                    else:
                        self.board[row].append(0)
                else:
                    self.board[row].append(0)

    def draw(self,win):
        self.drawSqaure(win)
        for row in range(rows):
            for col in range(cols):
                Piece=self.board[row][col]
                if Piece !=0:
                    Piece.draw(win)



