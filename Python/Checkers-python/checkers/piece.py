from .consts import red,white,grey,squareSize
import pygame
class Piece:
    padding=10
    outline=2
    def __init__(self,row,col,color):
        self.row=row
        self.col=col
        self.color=color
        self.king=False
        if self.color==red:
            self.direction=1
        else:
            self.direction=-1
        self.x=0
        self.y=0
        self.calcPos()


    def calcPos(self):
        self.x=squareSize*self.col*squareSize//2
        self.y=squareSize*self.row*squareSize//2
    def makeKing(self):
        self.king=True
    
    def draw(self,win):
        radius=squareSize//2-self.padding
        pygame.draw.circle(win,self.grey,(self.x,self.y),radius+self.outline)
        pygame.draw.circle(win,self.color,(self.x,self.y),radius)
    def __repr__(self):
        return str(self.color)

