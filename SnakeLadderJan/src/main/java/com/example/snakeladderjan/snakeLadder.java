package com.example.snakeladderjan;

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

public class snakeLadder extends Application {

    public static final int tileSize=60,height=10,width=10;
    int lowerLine  = tileSize*height;
    int diceValue;

    Label rolledDiceValueLabel;

    boolean firstPlayerTurn=true , secondPlayerTurn  = false, gameStarted = false;

    Button startGameButton;

    Player firstPlayer = new Player(tileSize, Color.BLACK,"Hari");
    Player secondPlayer = new Player(tileSize-5,Color.WHITE,"Vicky");
    Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize,height*tileSize+80);

        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }
//        root.getChildren().add(new Tile(tileSize)

        Image img = new Image("C:\\Users\\USER\\IdeaProjects\\SnakeLadderJan\\src\\snake.jpg");
        ImageView boardImage = new ImageView();
        boardImage.setImage(img);
        boardImage.setFitWidth(tileSize*width);
        boardImage.setFitHeight(tileSize*height);

        Button playerOneButton  = new Button("Player One");
        playerOneButton.setTranslateX(30);
        playerOneButton.setTranslateY(lowerLine+20);


        Button playerTwoButton  = new Button("Player Two");
        playerTwoButton.setTranslateX(500);
        playerTwoButton.setTranslateY(lowerLine+20);


        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(firstPlayerTurn){
                        setDiceValue();
                        firstPlayer.movePlayer(diceValue);
                        if(firstPlayer.playerWon()!=null){
                            rolledDiceValueLabel.setText(firstPlayer.playerWon());
                            firstPlayerTurn=true;
                            secondPlayerTurn=false;
                            gameStarted = false;
                            startGameButton.setDisable(false);
                            startGameButton.setText("Start Game");
                        }
                        firstPlayerTurn=false;
                        secondPlayerTurn=true;
                    }
                }

            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameStarted) {
                    if (secondPlayerTurn) {
                        setDiceValue();
                        secondPlayer.movePlayer(diceValue);
                        if (secondPlayer.playerWon() != null) {
                            rolledDiceValueLabel.setText(secondPlayer.playerWon());
                            firstPlayerTurn=true;
                            secondPlayerTurn=false;
                            gameStarted = false;
                            startGameButton.setDisable(false);
                            startGameButton.setText("Start Game");
                        }
                        secondPlayerTurn=false;
                        firstPlayerTurn=true;
                    }
                }
            }
        });

        startGameButton = new Button("Start");
        startGameButton.setTranslateY(lowerLine+50);
        startGameButton.setTranslateX(275);
        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted=true;
                startGameButton.setText("Ongoing Game");
                startGameButton.setDisable(true);
            }
        });

        rolledDiceValueLabel = new Label("Start the Game");
        rolledDiceValueLabel.setTranslateY(lowerLine+25);
        rolledDiceValueLabel.setTranslateX(260);

        root.getChildren().addAll(boardImage,playerOneButton,playerTwoButton,firstPlayer.getCoin(),secondPlayer.getCoin(),
                rolledDiceValueLabel,startGameButton);
//                secondPlayer.getCoin());

        return root;
    }

    private void setDiceValue(){
        diceValue = (int)(Math.random()*6+1);   //math.random is a metod which gives random value between 0 and 1
        rolledDiceValueLabel.setText("Dice Value : "+ diceValue);
    }


    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake and Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}