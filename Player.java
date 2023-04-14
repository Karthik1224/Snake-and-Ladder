package com.example.snake_and_ladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private Circle coin;
    private int cur_position;
    private String name;
    Board gameBoard = new Board();
    public Player(int tilesize, Color color, String playerName )
    {
         coin = new Circle(tilesize/2);
         coin.setFill(color);
         cur_position=0;
         movePlayer(1);
         name = playerName;
    }

    public void movePlayer(int diceValue)
    {
        if(cur_position+diceValue <= 100) {
            cur_position += diceValue;
            TranslateTransition secondMove=null,firstMove=translation(diceValue);

            int newPosition = gameBoard.getSnakeLadderPosi(cur_position);
            {
                if(newPosition != cur_position && newPosition != -1)
                {
                    cur_position = newPosition;
                    secondMove=translation(6);
                }
            }
            if(secondMove==null)
            {
                firstMove.play();
            }
            else {
                SequentialTransition st = new SequentialTransition(firstMove,new PauseTransition(Duration.millis(1000)),secondMove);
                st.play();
            }
        }
//            int x = gameBoard.getXcordinate(cur_position);
//            int y = gameBoard.getYcordinate(cur_position);
//
//            coin.setTranslateX(x);
//            coin.setTranslateY(y);


    }

    public TranslateTransition translation(int diceValue)
    {
        TranslateTransition animate = new TranslateTransition(Duration.millis(200*diceValue),coin);
        animate.setToX(gameBoard.getXcordinate(cur_position));
        animate.setToY(gameBoard.getYcordinate(cur_position));
        animate.setAutoReverse(false);
       return animate;
    }

    // after winning bringing both players to initial state
    public void startingPosition()
    {
        cur_position=0;
        movePlayer(1);
    }
    boolean isWinner()
    {
        if(cur_position==100)
        {
            return true;
        }
        return false;
    }


    public Circle getCoin() {
        return coin;
    }

    public int getCur_position() {
        return cur_position;
    }

    public String getName() {
        return name;
    }
}
