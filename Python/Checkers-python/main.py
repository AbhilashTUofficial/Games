import pygame
from checkers.consts import width,height
from checkers.board import Board

win=pygame.display.set_mode((width,height))
pygame.display.set_caption("Checkers game")
fps=60

# Game loop
def main():
    clock=pygame.time.Clock()
    run=True
    board=Board()
    while(run):
        clock.tick(fps)
        for event in pygame.event.get():
            if event.type==pygame.QUIT:
                run=False
            if event.type==pygame.MOUSEBUTTONDOWN:
                pass

        board.draw(win)
        pygame.display.update()
    pygame.quit()


main()