package com.towerOfHanoi;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

class screen{
    int x=200;
    int y=100;
    int height=800;
    int width=800;
    Color backGroundColor=Color.LIGHT_GRAY;
    int innerContHeight=400;
    int innerContWidth=700;
    Color innerContBackGroundColor=Color.white;
    int innerContX=50;
    int innerContY=200;
}
class tower extends screen{

    int pegDist=200;
    int pegAX=pegDist-10;
    int pegAY=300;
    int pegBX=pegDist*2-10;
    int pegBY=300;
    int pegCX=pegDist*3-10;
    int pegCY=300;
    Color pegColor=Color.DARK_GRAY;
    int pegWidth=20;
    int pegHeight=200;
    int pegFeetHeight=10;
    int pegFeetWidth=pegDist-10;
    int pegAFeetX=pegAX+5-(pegFeetWidth)/2;
    int pegAFeetY=pegAY+pegHeight-pegFeetHeight;
    int pegBFeetX=pegBX+5-(pegFeetWidth)/2;
    int pegBFeetY=pegBY+pegHeight-pegFeetHeight;
    int pegCFeetX=pegCX+5-(pegFeetWidth)/2;
    int pegCFeetY=pegCY+pegHeight-pegFeetHeight;
    int pegTipRadius=pegWidth+1;
    int pegATipX=pegAX-1;
    int pegATipY=pegAY-pegTipRadius/2;
    int pegBTipX=pegBX-1;
    int pegBTipY=pegBY-pegTipRadius/2;
    int pegCTipX=pegCX-1;
    int pegCTipY=pegCY-pegTipRadius/2;
}



public class Window extends JFrame {
    public static void main(String args[]){
        Window win=new Window();
        screen scr=new screen();
        win.setBounds(scr.x,scr.y,scr.width,scr.height);
        win.setVisible(true);
        win.setResizable(false);
        win.setTitle("Tower Of Hanoi");
    }
    public void paint(Graphics g){
        GameLogic game=new GameLogic();
        screen scr=new screen();
        g.setColor(scr.backGroundColor);
        g.fillRect(0,0,scr.width,scr.height);
        g.setColor(scr.innerContBackGroundColor);
        g.fillRect(scr.innerContX,scr.innerContY,scr.innerContWidth,scr.innerContHeight);
        drawPegs(g);
        game.drawDisk(g);
        g.dispose();
        repaint();
    }
    public void drawPegs(Graphics g){
        tower tow=new tower();
        diskTop top=new diskTop();
        diskMiddle mid=new diskMiddle();
        diskBottom bot=new diskBottom();
        g.setColor(tow.pegColor);
        // Peg A
        g.fillRect(tow.pegAX,tow.pegAY,tow.pegWidth,tow.pegHeight);
        g.fillRect(tow.pegAFeetX,tow.pegAFeetY,tow.pegFeetWidth,tow.pegFeetHeight);
        g.fillOval(tow.pegATipX,tow.pegATipY,tow.pegTipRadius,tow.pegTipRadius);
        // Peg B
        g.fillRect(tow.pegBX,tow.pegBY,tow.pegWidth,tow.pegHeight);
        g.fillRect(tow.pegBFeetX,tow.pegBFeetY,tow.pegFeetWidth,tow.pegFeetHeight);
        g.fillOval(tow.pegBTipX,tow.pegBTipY,tow.pegTipRadius,tow.pegTipRadius);
        // Peg C
        g.fillRect(tow.pegCX,tow.pegCY,tow.pegWidth,tow.pegHeight);
        g.fillRect(tow.pegCFeetX,tow.pegCFeetY,tow.pegFeetWidth,tow.pegFeetHeight);
        g.fillOval(tow.pegCTipX,tow.pegCTipY,tow.pegTipRadius,tow.pegTipRadius);
    }
}

