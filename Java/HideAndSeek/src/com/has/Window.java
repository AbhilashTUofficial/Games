package com.has;
import javax.swing.*;
import java.awt.*;


public class Window {
    public static void main(String[] args){
        int winHeight=806;
        int winWidth=806;
        JFrame win=new JFrame();
        win.setBounds(200,10,winWidth,winHeight);
        win.setBackground(Color.black);
        win.setVisible(true);
        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        win.setResizable(false);
        GameLogic gamePlay=new GameLogic();
        win.add(gamePlay);


    }
}
