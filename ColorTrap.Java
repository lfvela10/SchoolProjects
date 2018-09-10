import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.BROWN;
import static javafx.scene.paint.Color.ORANGE;
import static javafx.scene.paint.Color.PURPLE;
import static javafx.scene.paint.Color.RED;
import static javafx.scene.paint.Color.YELLOW;
import javafx.scene.text.*;
import javafx.util.Duration;

public class ColorTrap extends Application
{
    private Scene scene;
    private BorderPane borderPane;
    private Text txtCountDown = new Text("");
    private Timeline timeline;
    private final String [] colorNameArray = {"RED", "BLUE", "YELLOW", "BROWN","BLACK", "PURPLE", "ORANGE"};
    private final Color [] fontColorArray = {RED, BLUE, YELLOW, BROWN, BLACK, PURPLE, ORANGE};
    private int nameIndex = 0;
    private int colorIndex = 0;
    private final int TIMER = 15;
    private int count = 0;
    private FlowPane flowPane = new FlowPane();
    private Text trapWord = new Text();
    private Text [] arrayOfText = new Text[7];
    private HBox top = new HBox();
    private HBox bottom = new HBox (425);
    private HBox bottomRight = new HBox();
    private int score = 0;
    private int i = 0;
    private int extraCreditIndex = 0;
    private VBox endOfGamePositionBox = new VBox();
    
    private Button b = new Button("Play Again");
    private Text scoreText = new Text(score + "");
    private Text timerText = new Text("Time Remaining: ");
    private Text endOfGameScoreDisplay = new Text("");
    

    @Override
    public void start(Stage primaryStage)
    {
        borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: lightgrey");
        
        scene = new Scene(borderPane, 600, 300);
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(600);
        initializeGame();
        startPlay();

        primaryStage.setTitle("Color Trap");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void startPlay()
    {
        chooseTrapWordAndColor();
        colorNameOptions();

        count = TIMER;
        txtCountDown.setText(TIMER + "");
        timeline = new Timeline(new KeyFrame(
                Duration.millis(1000), e -> {


                    if(count >= 0)
                    {
                        txtCountDown.setText(count + "");
                        count--;
                    }
                    else
                    {
                        endOfGame();
                    }
                }));
        timeline.setCycleCount(TIMER + 2);
        timeline.play();

    }
    
    public void endOfGame()
    {        
        b.setDisable(false);
        endOfGameScoreDisplay.setVisible(true);
        endOfGameScoreDisplay.setText("Your score: " + score );
        endOfGameScoreDisplay.setFont(Font.font("Marker Felt", 40));     
        endOfGameScoreDisplay.setTextAlignment(TextAlignment.CENTER);
        
        b.setAlignment(Pos.CENTER);
        
        endOfGamePositionBox.setAlignment(Pos.CENTER);
        endOfGamePositionBox.setSpacing(15);
        borderPane.setCenter(endOfGamePositionBox);
        flowPane.setDisable(true);
        
        //hides trap and score boxes while keeping them in place
        top.setVisible(false);
        bottom.setVisible(false);
        
        
        b.setOnMouseClicked(e ->{
        
        score = 0;
        scoreText.setText("Score: " + score + "");
        borderPane.setCenter(flowPane);
        flowPane.setVisible(true);
        top.setVisible(true);
        bottom.setVisible(true);
        b.setDisable(true);
        flowPane.setDisable(false);
        
            startPlay();
        });


    }


    public void checkChoice(Text choice)
    {
       // checks choice updates score only if need be
      if(trapWord.getFill() == (Color.valueOf(choice.getText())))
      {
               score++;
               scoreText.setText("Score: " + score + "");
      }
        //Do NOT add any code after this comment
        //Choose a new trap word and options list
        chooseTrapWordAndColor();
        colorNameOptions();
    }

    public void chooseTrapWordAndColor()
    {
        //TODO complete this method as required.
     
       boolean equalIndex = false;
         
        while(equalIndex == false)
        {
           nameIndex = 0 + (int)(Math.random() * ((6 - 0) + 1)); // random index created to initiliaze random trap
           colorIndex = 0 + (int)(Math.random() * ((6 - 0) + 1)); 
            
           if(nameIndex != colorIndex)
                equalIndex = true;
        }
        //trap created
         trapWord.setText(colorNameArray[nameIndex]);
         trapWord.setFill(fontColorArray[colorIndex]); 
    }
    
    public void colorNameOptions()
    {
        // indexs in these two arrays are to keep track of indexes used
        int [] usedColorIndex = {0, 1, 2, 3, 4, 5, 6};
        int [] usedNameIndex = {0, 1, 2, 3, 4, 5, 6};

        for(i = 0; i < 7; i++)
        {
           boolean equalIndex = false;
            while(equalIndex == false)
            {
               nameIndex = 0 + (int)(Math.random() * ((6 - 0) + 1)); // random index created to initiliaze random trap
               colorIndex = 0 + (int)(Math.random() * ((6 - 0) + 1)); 

                if(usedColorIndex[colorIndex] != -1 && usedNameIndex[nameIndex] != -1)
                    equalIndex = true;
               
               
            }
            arrayOfText[i].setText(colorNameArray[nameIndex]);
            arrayOfText[i].setFill(fontColorArray[colorIndex]);
            
            usedColorIndex[colorIndex] = -1;
            usedNameIndex[nameIndex] = -1;
  
        }

    }

    public void initializeGame()
    {
        scoreText.setText("Score: " + score + "");
        // trapWord setup
        trapWord.setFont(Font.font("Marker Felt", 60));
        top.getChildren().add(trapWord);
        top.setPrefHeight(borderPane.getHeight() * 0.35);
        top.setAlignment(Pos.CENTER);
        borderPane.setTop(top);
        
        //sets up slots for center words
                    
       for(i = 0; i < 7; i++)
        {
           Text centerWord = new Text();
           centerWord.setFont(Font.font("Marker Felt", 40));
           
           centerWord.setOnMouseClicked(e -> {
                
                checkChoice(centerWord);
                
                });
           
           arrayOfText[i] = centerWord;
           flowPane.getChildren().add(centerWord);
        }
        
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setHgap(40);
        flowPane.setPrefHeight(borderPane.getHeight() * 0.55);
        flowPane.setAlignment(Pos.CENTER);
        borderPane.setCenter(flowPane);
        
        
        //initialize bottom of board

        bottomRight.getChildren().addAll(timerText, txtCountDown);
        bottomRight.setAlignment(Pos.BOTTOM_RIGHT);                   
        bottom.getChildren().addAll(scoreText, bottomRight);
        bottom.setPrefHeight(borderPane.getHeight() * 0.10 );
        bottom.setAlignment(Pos.BOTTOM_LEFT);
        borderPane.setBottom(bottom);
        endOfGamePositionBox.getChildren().addAll(endOfGameScoreDisplay, b);

     } 
  
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
