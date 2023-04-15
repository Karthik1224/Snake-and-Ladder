package com.example.snake_and_ladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


public class SnakeandLadder extends Application {

     static final int tilesize = 40, width=10,height=10;
    private static final int btn_line= height*tilesize+40;
    private static final int info_line = btn_line-30;

    private static Dice dice = new Dice();

    private  Player player1,player2;

    private boolean gameStart = false, playerOneTurn=false, playerTwoTurn=false;
    private Pane createContent()
    {
        Pane root = new Pane();
        root.setPrefSize(tilesize*width, tilesize*height+100);

        for (int i = 0; i < width; i++) {
            {
                for (int j = 0; j < height; j++) {
                    Tile tile = new Tile(tilesize);
                    tile.setTranslateX(j*tilesize);
                    tile.setTranslateY(i*tilesize);
                    root.getChildren().add(tile);
                }
            }

        }
        Image img = new Image("https://media.istockphoto.com/id/531466314/vector/snakes-and-ladders.jpg?s=612x612&w=0&k=20&c=YYRwkxtVxAXrYV7kFCHKW4h0SHFS4sSSoaj-s9OeHF4=");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tilesize);
        board.setFitWidth(width*tilesize);


        //buttons
        Button player_1 = new Button("Player-1");
        Button player_2 = new Button("Player-2");
        Button start = new Button("start ");
        player_1.setTranslateY(btn_line);
        player_1.setTranslateX(20);
        player_1.setDisable(true);
        player_2.setTranslateY(btn_line);
        player_2.setTranslateX(300);
        player_2.setDisable(true);
        start.setTranslateY(btn_line);
        start.setTranslateX(160);



        //information
        Label p1 = new Label("");
        Label p2 = new Label("");
        Label st = new Label("start");
        p1.setTranslateY(info_line);
        p1.setTranslateX(20);
        p2.setTranslateY(info_line);
        p2.setTranslateX(300);
        st.setTranslateY(info_line);
        st.setTranslateX(160);



        //players initialization
        player1 = new Player(tilesize, Color.BLACK,"A");
        player2 = new Player(tilesize-5,Color.WHITE,"B");


        //players actions
        player_1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(gameStart)
                {
                    if(playerOneTurn)
                    {
                        //playing the game
                        int diceValue = dice.getDiceRolledValue();
                        st.setText("Dice Value : "+ diceValue);
                        player1.movePlayer(diceValue);

                        //Winning Condition
                        if(player1.isWinner())
                        {
                            st.setText("Winner is : "+ player1.getName());
                            playerOneTurn = false;
                            player_1.setDisable(true);
                            p1.setText("");
                            playerTwoTurn = false;
                            player_2.setDisable(true);
                            p2.setText("");

                            start.setDisable(false);
                            start.setText("Restart the game");
                        }
                        else
                        {
                            //Now disable the player who has played
                            playerOneTurn = false;
                            player_1.setDisable(true);
                            p1.setText("");

                            //Now enable the other player to play
                            playerTwoTurn = true;
                            player_2.setDisable(false);
                            p2.setText("Your turn : " + player2.getName());
                        }
                    }
                }

            }
        });
        player_2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(gameStart)
                {
                    if(playerTwoTurn)
                    {
                        //playing the game
                        int diceValue = dice.getDiceRolledValue();
                        st.setText("Dice Value : "+ diceValue);
                        player2.movePlayer(diceValue);

                      if(player2.isWinner())
                      {
                          st.setText("Winner is : "+ player2.getName());
                          playerOneTurn = false;
                          player_1.setDisable(true);
                          p1.setText("");
                          playerTwoTurn = false;
                          player_2.setDisable(true);
                          p2.setText("");

                          start.setDisable(false);
                          start.setText("Restart");
                      }
                      else
                      {
                          //Now disable the player who has played
                          playerTwoTurn = false;
                          player_2.setDisable(true);
                          p2.setText("");

                          //Now enable the other player to play
                          playerOneTurn = true;
                          player_1.setDisable(false);
                          p1.setText("Your turn : " + player1.getName());
                      }
                    }
                }

            }
        });
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStart=true;
                st.setText("Game Started");
                start.setDisable(true);
                playerOneTurn=true;
                player_1.setDisable(false);
                p1.setText("Your turn : "+ player1.getName());
                player1.startingPosition();


                playerTwoTurn=false;
                p2.setText("");
                player_2.setDisable(true);
                player2.startingPosition();

            }
        });
        root.getChildren().addAll(
                board,
                player_1,player_2,start,
                p1,p2,st,
                player1.getCoin(),player2.getCoin()
        );
        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}