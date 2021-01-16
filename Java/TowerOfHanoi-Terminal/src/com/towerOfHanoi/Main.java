package com.towerOfHanoi;

import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        tower obj = new tower();
    }
}

class tower {

    tower() {
        Scanner input = new Scanner(System.in);
        int[] peg1 = new int[3];
        int[] peg2 = new int[3];
        int[] peg3 = new int[3];
        boolean onGame = true;
        int[][] towers = {peg1, peg2, peg3};
        for (int i = 0; i <= 2; i++) {
            init(towers[i], i);
        }
        while (onGame) {

            System.out.println(Arrays.deepToString(towers));
            choiceTake(input, towers);
            onGame = winCheck(towers);
        }
        System.out.println("You win!!!");
        System.out.println(Arrays.deepToString(towers));
    }

    boolean winCheck(int[][] towers) {

        if (towers[2][0] > towers[2][1] && towers[2][1] > towers[2][2] && towers[2][2] != 0) {
            return false;
        }
        return true;
    }

    void init(int[] peg, int i) {
        if (i == 0) {
            for (int j = 2; j >= 0; j--) {
                try {
                    peg[j] = 2 - j + 1;
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        } else {
            for (int j = 2; j >= 0; j--) {
                try {
                    peg[j] = 0;
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        }
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