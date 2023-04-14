package com.example.snake_and_ladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer,Integer>> posiCordinates;
    ArrayList<Integer>SnakeLadderPosition;
    public Board()
    {
        posiCordinates = new ArrayList<>();
        arrangeCordinates();
        SnakeLadderCordinates();
    }

    private void arrangeCordinates() {
        posiCordinates.add(new Pair<>(0, 0));
        for (int i = 0; i < SnakeandLadder.height; i++) {
            for (int j = 0; j < SnakeandLadder.width; j++) {
                // x cordinates
                int x = 0;
                if (i % 2 == 0) {
                    x = j * SnakeandLadder.tilesize + SnakeandLadder.tilesize / 2;
                } else {
                    x = SnakeandLadder.height * SnakeandLadder.tilesize - (j * SnakeandLadder.tilesize) - SnakeandLadder.tilesize / 2;
                }
                // y cordinates
                int y = SnakeandLadder.height * SnakeandLadder.tilesize - (i * SnakeandLadder.tilesize) - SnakeandLadder.tilesize / 2;
                posiCordinates.add(new Pair<>(x, y));
            }
        }
    }


    //Arranging positions for snake and ladder
     private void SnakeLadderCordinates()
     {
         SnakeLadderPosition = new ArrayList<>();
         for (int i = 0; i < 101; i++) {
                SnakeLadderPosition.add(i);
         }
         SnakeLadderPosition.set(4,25);
         SnakeLadderPosition.set(13,46);
         SnakeLadderPosition.set(33,49);
         SnakeLadderPosition.set(42,63);
         SnakeLadderPosition.set(50,69);
         SnakeLadderPosition.set(62,81);
         SnakeLadderPosition.set(74,92);
         SnakeLadderPosition.set(27,5);
         SnakeLadderPosition.set(40,3);
         SnakeLadderPosition.set(43,18);
         SnakeLadderPosition.set(54,31);
         SnakeLadderPosition.set(66,45);
         SnakeLadderPosition.set(76,58);
         SnakeLadderPosition.set(89,53);
         SnakeLadderPosition.set(99,41);

     }

     //Returning position of snake and ladder if present at current position
     int getSnakeLadderPosi(int posi)
     {
         if(posi >= 1 && posi <= 100)
         {
             return SnakeLadderPosition.get(posi);
         }
         else {
             return -1;
         }
     }
    // providing x and y cordinates
       int getXcordinate(int posi)
       {
           if(posi >= 1 && posi <= 100)
           {
               return posiCordinates.get(posi).getKey();
           }
           else {
               return -1;
           }
       }
    int getYcordinate(int posi)
    {
        if(posi >= 1 && posi <= 100)
        {
            return posiCordinates.get(posi).getValue();
        }
        else {
            return -1;
        }
    }
        // check whether getting exact cordinates or not
//        public static void main(String[] args) {
//            Board obj = new Board();
//            for (int i = 0; i < obj.posiCordinates.size(); i++) {
//
//
//                    System.out.println(i+" # "+ " x : "+ obj. posiCordinates.get(i).getKey() + " y : "+obj.posiCordinates.get(i).getValue() );
//
//
//
//            }
//        }

}
