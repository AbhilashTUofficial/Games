import pygame
from .consts import black,white,red,cols,rows,squareSize

class Board:
    def __init__(self):
        self.board=[]
        self.selectedPiece=None
        self.redLeft=whiteLeft=12
        self.redKing=whiteKing=0

    def drawSqaure(self,win):
        win.fill(black)
        for row in range(rows):
            for col in range(row%2,rows,2):
                pygame.draw.rect(win,red,(row*squareSize,col*squareSize,squareSize,squareSize))
    def createBoard(self):
        pass

