package com.games;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


public class gameLogic extends JPanel implements KeyListener, ActionListener {
    // variables
    private boolean play = false;
    private int col = 7;
    private int row = 7;
    private int numberOfBricks =col*row;
    private int score;
    private Timer time;
    private int delay = 0;
    private int playerX = 310;
    private int playerY = 550;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballXDir = -1;
    private int ballYDir = -2;
    private int windowWidth = 700;
    private int windowHeight = 600;
    private int borderSize = 8;
    private int screenMargin = 2;
    private int screenTopMargin = 30;
    private int screenWidth = windowWidth - borderSize;
    private int screenHeight = windowHeight - borderSize;
    private int borderWidth = 3;
    private int paddleHeight = 8;
    private int paddleWidth = 100;
    private int ballSize=20;
    private MapGenerator map;


    public gameLogic() {
        map = new MapGenerator(col, row);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time = new Timer(delay, this);
        time.start();
    }

    public void paint(Graphics g) {
        //background
        g.setColor(Color.black);
        g.fillRect(1, 1, screenWidth, screenHeight);

        //draw bricks
        map.draw((Graphics2D) g);

        //score
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, screenHeight - screenMargin, screenTopMargin);

        //border
        g.setColor(Color.yellow);
        g.fillRect(0, 0, borderWidth, screenHeight);
        g.fillRect(0, 0, screenWidth, borderWidth);
        g.fillRect(screenWidth - 10, 0, borderWidth, screenHeight);

        //paddle
        g.setColor(Color.green);
        g.fillRect(playerX, playerY, paddleWidth, paddleHeight);

        //ball
        g.setColor(Color.yellow);
        g.fillOval(ballPosX, ballPosY, ballSize, ballSize);

        //End game->lost
        if (ballPosY > 560) {
            play = false;
            ballXDir = 0;
            ballYDir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 50));
            g.drawString("Game over", 225, 300);
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press enter to Restart", 215, 335);
            g.dispose();
        }
        //End game->win
        if (numberOfBricks <= 0) {
            play = false;
            ballXDir = 0;
            ballYDir = 0;
            g.setColor(Color.GREEN);
            g.setFont(new Font("serif", Font.BOLD, 50));
            g.drawString("You win!!!", 225, 300);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press enter to Restart", 215, 335);
            g.dispose();
        }
    }
    //collision
    @Override
    //collision with the paddle
    public void actionPerformed(ActionEvent e) {
        if (new Rectangle(ballPosX, ballPosY, ballSize, ballSize).intersects(new Rectangle(playerX, playerY, paddleWidth, paddleHeight))) {
            ballYDir = ballYDir * -1;
        }
        A:
        for (int i = 0; i < map.map.length; i++) {
            for (int j = 0; j < map.map[0].length; j++) {
                if (map.map[i][j] > 0) {
                    int brickX = j * map.brickWidth + 80;
                    int brickY = i * map.brickHeight + 50;
                    int brickWidth = map.brickWidth;
                    int brickHeight = map.brickHeight;
                    Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                    Rectangle ballRect = new Rectangle(ballPosX, ballPosY, ballSize, ballSize);
                    Rectangle brickRect = rect;
                    if (ballRect.intersects(brickRect)) {
                        map.setBrickValue(0, i, j);
                        numberOfBricks--;
                        score++;
                        if (ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width) {
                            ballXDir = -ballXDir;
                        } else {
                            ballYDir = -ballYDir;
                        }
                        break A;

                    }
                }
            }
        }
        time.start();
        //collision with the border
        if (play) {
            ballPosX += ballXDir;
            ballPosY += ballYDir;
            if (ballPosX < 0) {
                ballXDir = ballXDir * -1;
            }
            if (ballPosY < 0) {
                ballYDir = ballYDir * -1;
            }
            if (ballPosX > 670) {
                ballXDir = ballXDir * -1;
            }
        }
        repaint();


    }

    @Override
    public void keyTyped(KeyEvent e) { /* Empty function*/ }

    @Override
    public void keyReleased(KeyEvent e) { /* Empty function*/ }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX > 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                playerX = 310;
                ballPosX = 120;
                ballPosY = 350;
                ballXDir = -1;
                ballYDir = -2;
                score = 0;
                numberOfBricks = col*row;
                map = new MapGenerator(row, col);
                repaint();
            }

        }


    }
    //motion function
    public void moveLeft() {
        play = true;
        playerX -= 20;
    }

    public void moveRight() {
        play = true;
        playerX += 20;

    }


}
