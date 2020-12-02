package com.games;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
	JFrame obj=new JFrame();
	gameLogic gamePlay=new gameLogic();
	obj.setBounds(100,100,700,600);
	obj.setTitle("blockBracker!");
	obj.setVisible(true);
	obj.setResizable(false);
	obj.add(gamePlay);

    }
}
