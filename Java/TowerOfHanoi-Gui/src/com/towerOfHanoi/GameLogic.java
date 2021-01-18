package com.towerOfHanoi;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

public class GameLogic extends JPanel {
    Scanner input = new Scanner(System.in);
    int[] peg1 = new int[]{3,2,1};
    int[] peg2 = new int[3];
    int[] peg3 = new int[3];
    int[] diskCoors;
    boolean onGame = true;
    int[][] towers = {peg1, peg2, peg3};
   void drawDisk(Graphics g){
       tower tow=new tower();
       diskBottom bot =new diskBottom();
       diskMiddle mid=new diskMiddle();
       diskTop top=new diskTop();
       // Disk
       g.setColor(Color.ORANGE);
       g.fillRect(tow.pegAX+tow.pegWidth/2-140/2,tow.pegAY+tow.pegHeight-40,bot.diskWidth,bot.diskHeight);
       for(int i=0;i<=2;i++){
           for(int j=0;j<=2;j++){
               int diskWidth=0;
               int diskHeight=0;
               if(towers[i][j]!=0){
                   diskCoors=getDiskCoors(towers,i,j);
                   if(diskCoors[0]==3){
                       diskWidth=bot.diskWidth;
                       diskHeight=bot.diskHeight;
                   }
                   if(diskCoors[0]==2){
                       diskWidth=mid.diskWidth;
                       diskHeight=mid.diskHeight;
                   }
                   if(diskCoors[0]==1){
                       diskWidth=top.diskWidth;
                       diskHeight=top.diskHeight;
                   }
                   g.setColor(Color.GREEN);
                   g.fillRect(diskCoors[1],diskCoors[2],diskWidth,diskHeight);

               }
           }
       }
   }
   int[] getDiskCoors(int[][] towers,int i,int j){
       if(i==0)diskCoors[1]=200;
       if(i==1)diskCoors[1]=400;
       if(i==2)diskCoors[1]=300;
       if(j==0)diskCoors[2]=100;
       if(j==1)diskCoors[2]=200;
       if(j==2)diskCoors[2]=300;
       if(towers[i][j]==3)diskCoors[0]=3;
       if(towers[i][j]==2)diskCoors[0]=2;
       if(towers[i][j]==1)diskCoors[0]=1;

       return diskCoors;
   }

}
class diskTop extends tower{
    int diskHeight=26;
    int diskWidth=80;
}
class diskMiddle extends tower{
    int diskHeight=26;
    int diskWidth=120;
}
class diskBottom extends tower{
    int diskHeight=26;
    int diskWidth=140;
}