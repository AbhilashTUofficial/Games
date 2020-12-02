package com.has;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class GameLogic extends JPanel implements ActionListener, KeyListener, MouseListener {

    private int TileSize = 260;
    private Timer time;
    private int delay = 8;
    int[][] location = new int[3][3];
    int xi=3;
    int yj=3;
    static int[] temp=new int[2];
    int chance=0;
    Font font=new Font("Courier",0,80);


    public static int[] randamize(){
        Random rand = new Random();
        int tempI=rand.nextInt(3);
        int tempJ=rand.nextInt(3);
        temp[0]=tempI;
        temp[1]=tempJ;
        return temp;
    }

    public GameLogic() {
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time = new Timer(delay, this);
        time.start();
        randamize();
    }

    public void paint(Graphics g) {
        int x = 2;
        int y = 2;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                g.setColor(Color.white);
                g.fillRect(x, y, TileSize, TileSize);
                x += 263;
                System.out.println("i:" + i + "j:" + j);
            }
            y += 263;
            x = 0;
        }
        location[temp[0]][temp[1]]=1;
        System.out.println(Arrays.deepToString(location));
        if(yj!=3||xi!=3){
            if(location[xi][yj]==1){
                winMove(g);
            }
            if(chance>=3){
                outOfMove(g);
                time.stop();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        repaint();
        chance+=1;
        int x=e.getX();
        int y=e.getY();
        xi=(x/263);
        yj=(y/263);
        System.out.println(yj);
    }
    public void winMove(Graphics g){
        g.setColor(Color.green);
        g.setFont(font);
        g.drawString("Oh you found it!",100,380);
    }
    public void outOfMove(Graphics g){
        g.setColor(Color.red);
        g.setFont(font);
        g.drawString("Out of chances",120,380);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


    }


    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }



}
