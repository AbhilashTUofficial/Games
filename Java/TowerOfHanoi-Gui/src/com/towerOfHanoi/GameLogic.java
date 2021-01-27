package com.towerOfHanoi;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class GameLogic extends JPanel {
    Scanner input = new Scanner(System.in);
    int[] peg1 = new int[]{3,2,1};
    int[] peg2 = new int[]{0,0,2};
    int[] peg3 = new int[]{0,0,0};
    int[] diskCoors;
    boolean onGame = true;
    int[][] towers = {peg1, peg2, peg3};
// Drawing section
   void drawDisk(Graphics g){
       tower tow=new tower();
       diskBottom bot =new diskBottom();
       diskMiddle mid=new diskMiddle();
       diskTop top=new diskTop();
       // Disk
       for(int i=0;i<=2;i++){
           for(int j=0;j<=2;j++){
               int diskWidth=0;
               int diskHeight=0;
               if(towers[i][j]!=0){
                   diskCoors=getDiskCoors(towers,i,j);
                   if(diskCoors[0]==3){
                       diskWidth=bot.diskWidth;
                       diskHeight=bot.diskHeight;
                       g.setColor(Color.magenta);
                   }
                   if(diskCoors[0]==2){
                       diskWidth=mid.diskWidth;
                       diskHeight=mid.diskHeight;
                       g.setColor(Color.pink);
                   }
                   if(diskCoors[0]==1){
                       diskWidth=top.diskWidth;
                       diskHeight=top.diskHeight;
                       g.setColor(Color.RED);
                   }
                   g.fillRect(diskCoors[1],diskCoors[2],diskWidth,diskHeight);

               }
           }
       }
   }
   int[] getDiskCoors(int[][] towers,int i,int j){
       tower tow=new tower();
       diskBottom bot=new diskBottom();
       diskMiddle mid=new diskMiddle();
       diskTop top=new diskTop();
       /*
       * Disk positions
       * first bottom:
       *    x: pegY + half of peg width -half of disk width */
       int diskWidth=0;
       int diskType=0;
       int diskY=0;

       // Find disk width
       if(towers[i][j]==3){
           diskWidth=bot.diskWidth;
           diskType=3;
       }else if(towers[i][j]==2){
           diskWidth=mid.diskWidth;
           diskType=2;
       }else if(towers[i][j]==1){
           diskWidth=top.diskWidth;
           diskType=1;
       }
       // Find disk y position
       if(j==0){
           diskY=460;
       }else if(j==1){
           diskY=430;
       }else if(j==2){
           diskY=400;
       }
       // Find disk x position
       int diskX=0;
       if(i==0){
           diskX=tow.pegAX-diskWidth/2+10;;
       }else if(i==1){
           diskX=tow.pegBX-diskWidth/2+10;;
       }else if(i==2){
           diskX=tow.pegBX-diskWidth/2+10;;
       }
       int x= tow.pegAX-diskWidth/2+10;
       diskCoors= new int[]{diskType,diskX,diskY};
       return diskCoors;
   }

    boolean winCheck(int[][] towers) {

        if (towers[2][0] > towers[2][1] && towers[2][1] > towers[2][2] && towers[2][2] != 0) {
            return false;
        }
        return true;
    }
    void choiceTake(Scanner input, int[][] towers) {
        int onHand;
        System.out.print("Take from: ");
        int ch = input.nextInt();
        switch (ch) {
            case 1:
                onHand = take(towers[ch - 1]);
                break;
            case 2:
                onHand = take(towers[ch - 1]);
                break;
            case 3:
                onHand = take(towers[ch - 1]);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + ch);
        }
        choicePlace(input, towers, onHand);
    }
    void choicePlace(Scanner input, int[][] towers, int onHand) {
        System.out.print("Place to: ");
        int ch = input.nextInt();
        switch (ch) {
            case 1:
                if (check(towers[ch - 1], onHand)) place(towers[ch - 1], onHand);
                else {
                    System.out.println("Can't do that!");
                    choicePlace(input, towers, onHand);
                }
                break;
            case 2:
                if (check(towers[ch - 1], onHand)) place(towers[ch - 1], onHand);
                else {
                    System.out.println("Can't do that!");
                    choicePlace(input, towers, onHand);
                }
                break;
            case 3:
                if (check(towers[ch - 1], onHand)) place(towers[ch - 1], onHand);
                else {

                    System.out.println("Can't do that!");
                    choicePlace(input, towers, onHand);
                }
                break;
        }
    }

    boolean check(int[] tower, int onHand) {
        if (tower[0] == 0) return true;
        else if (tower[0] < onHand) return false;
        else if (tower[0] > onHand) {
            if (tower[1] == 0) return true;
            else if (tower[1] < onHand) return false;
            else if (tower[1] > onHand) {
                if (tower[2] == 0) return true;
            }
        }
        return true;
    }

    int take(int[] peg) {
        int onHand = 0;
        for (int i = peg.length - 1; i > -1; i--) {
            if (peg[i] != 0) {
                onHand = peg[i];
                peg[i] = 0;
                i = -1;
            }
        }
        return onHand;
    }
    int place(int[] peg, int disk) {
        for (int i = 0; i <= 2; i++) {
            if (peg[i] == 0) {
                peg[i] = disk;
                return 0;
            }
        }
        return 0;
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