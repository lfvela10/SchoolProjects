import java.math.BigDecimal;
import java.math.BigInteger;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Calculator extends Application
{
   
    GridPane gridPane = new GridPane();
    String num1 = "";
    String num2 = "";
    String operator = "";
    boolean equalPressed = false;
    boolean operatorPressed = false;
    String numDisplay = "";
    BigDecimal big1;
    BigDecimal big2;
    BigDecimal result;
 
    int count  = 0;
    
    String finalOperator = "";
    
    String sqrSymbol = "x" + "\u00B2";
    String cubeSymbol = "x" + "\u00B3";
    String sqrtSymbol = "\u221B";
    String cubeRtSymbol = "\u00B3" + "\u221A"; // "\u00B3" + "\u221A" == "\u221B"
    
     //groups easy styling of buttons - LV
       Group numbers = new Group();
       Group symbols = new Group();
     
       // Buttons needed - T-Vandy
       Button btnZero = new Button("0");
       Button btnOne = new Button("1");
       Button btnTwo = new Button("2");
       Button btnThree = new Button("3");
       Button btnFour = new Button("4");
       Button btnFive = new Button("5");
       Button btnSix = new Button("6");
       Button btnSeven = new Button("7");
       Button btnEight = new Button("8");
       Button btnNine = new Button("9");
      
       int oppCount = 0;
   
       //operators
       Button btnTimes = new Button("x");
       Button btnMinus = new Button("-");
       Button btnAdd = new Button("+");
       Button btnDivide = new Button("÷");
       Button btnMod = new Button("%");
       Button btnFactorial = new Button("x!");
       Button btnSquaredRoot = new Button("\u221A");
       Button btnCubedRoot = new Button("\u221B"); 
       Button btnXsquared = new Button("x" + "\u00B2");
       Button btnXcubed= new Button("x" + "\u00B3");
       Button btnXtoTheN = new Button("x" + "\u207F");
       
       //submit/clear - LV
       Button btnEqual = new Button("=");
       Button btnClear = new Button("Clear");
       Button btnDecimal = new Button(".");
     
       Button btnPI = new Button("\u03C0");
       
       BigDecimal bigPI = new BigDecimal("3.141592653589793");
       
       //calc set up needs - LV
       BorderPane pane = new BorderPane();
       TextField tfInputWindow = new TextField();
       GridPane calculatorPane = new GridPane();
       String answerToDisplay = " ";
    
    
      boolean numFirst = false;
      
       BigInteger sum = new BigInteger("0");
       String stringToBeParsed;
       String value = "";
       
       //function booleans
       boolean numeratorPressedFirst = false;
       boolean numPressed = false;
       TextField   txtField = new TextField();
       EqualHandler equalHandler = new EqualHandler();
       
    @Override
    public void start(Stage primaryStage){
        
         
       primaryStage.setResizable(false);
       NumberHandler numberHandler = new NumberHandler();
       OperatorsHandler operatorHandler = new OperatorsHandler();
     
       SpecialOperationHandler specialHandler = new SpecialOperationHandler();
       btnOne.setOnAction(numberHandler);
       btnTwo.setOnAction(numberHandler);
       btnThree.setOnAction(numberHandler);
       btnFour.setOnAction(numberHandler);
       btnFive.setOnAction(numberHandler);
       btnSix.setOnAction(numberHandler);
       btnSeven.setOnAction(numberHandler);
       btnEight.setOnAction(numberHandler);
       btnNine.setOnAction(numberHandler);
       btnZero.setOnAction(numberHandler);
       btnTimes.setOnAction(operatorHandler);
       btnMinus.setOnAction(operatorHandler);
       btnAdd.setOnAction(operatorHandler);
       btnDivide.setOnAction(operatorHandler);
       btnMod.setOnAction(operatorHandler);
       
       
       btnFactorial.setOnAction(specialHandler);
       btnXsquared.setOnAction(specialHandler);
       btnXcubed.setOnAction(specialHandler);
       btnCubedRoot.setOnAction(specialHandler);
       btnSquaredRoot.setOnAction(specialHandler);
       
       btnXtoTheN.setOnAction(operatorHandler);
       
       btnPI.setOnAction(specialHandler);
  
        setUpCalculator();
       
       
        
        
        
       btnClear.setOnAction(e ->{
           txtField.setText("");
           tfInputWindow.setText("");
           num1 = num2 = "";
           equalPressed = false;
           operatorPressed = false;
           numDisplay = " ";
           
       });
       
        btnPI.setOnAction(e -> {
            
            if(numeratorPressedFirst == true)
            {
                big1 = new BigDecimal(num1);
                result = big1.multiply(bigPI);
                tfInputWindow.setText(result + "");
            }
            else
                tfInputWindow.setText("3.141592653589793");
        
        });
       
      
       btnDecimal.setOnAction(numberHandler);
      
       
      
       
      
        Scene scene = new Scene(pane, 522, 385);
        
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
   class NumberHandler implements EventHandler<ActionEvent>{
    
      
        @Override
        public void handle(ActionEvent e)
        {
            count++;
            numFirst = true;
            numeratorPressedFirst = true;
            value =((Button)e.getSource()).getText();
            if(operatorPressed == true && oppCount == 1)
                numDisplay = "";
            
            numDisplay += value;
            
            tfInputWindow.setText(numDisplay);
            if(operatorPressed == false){
                num1 += value;
                txtField.setText(num1);
            }
            else{
                oppCount++;
                num2 += value;
                txtField.setText(num2);
            }
            
            if(count >= 2)
                btnEqual.setOnAction(equalHandler);
            
            
        }
    }

    class OperatorsHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent e){
            numDisplay = " ";
            oppCount++;
            operatorPressed = true;
            finalOperator = ((Button)e.getSource()).getText();
        }
    }

    class EqualHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent e){
          
            equalPressed = true;
            tfInputWindow.setText(num1);
            numDisplay = "";
            
            big1 = new BigDecimal(num1);
            
            big2 = new BigDecimal(num2);

            if(finalOperator.equals("+")){ 
               tfInputWindow.setText(num1 + " + " + num2 + " = " + big1.add(big2) + "");
            }
            else if(finalOperator.equals("-")){
               tfInputWindow.setText(num1 + " - " + num2 + " = " + big1.subtract(big2) + "");
            }
            else if(finalOperator.equals("x")){
                tfInputWindow.setText(num1 + " * " + num2 + " = " + big1.multiply(big2) + "");
            }
            else if(finalOperator.equals("÷")){
                if(num2.equals("0"))
                    tfInputWindow.setText("Can't divide by zero");
                else
                   tfInputWindow.setText(num1 + " ÷ " + num2 + " = " + big1.divide(big2) + "");
            }
            else if (finalOperator.equals("%"))
            {
                tfInputWindow.setText(num1 + " % " + num2 + " = " + (big1.remainder(big2)) + "");
           
            }

            txtField.setText("");
            num1 = "";
            num2 = "";
            operatorPressed = false; 

        
      }
    }
    
    class SpecialOperationHandler implements EventHandler<ActionEvent>{
     
        
        @Override
        public void handle(ActionEvent e){
           numDisplay = " ";
           if(numeratorPressedFirst == true)
            {

            String value = ((Button)e.getSource()).getText();
            String getId = ((Button)e.getSource()).getId();
            big1 = new BigDecimal(num1);
            if(value.equals(sqrSymbol)){
               tfInputWindow.setText(big1.multiply(big1) + "");
                num1 = "";
            }
            else if(value.equals(cubeSymbol)){
                tfInputWindow.setText(big1.multiply(big1.multiply(big1)) + "");
                num1 = "";
            }
            else if(value.equals(sqrtSymbol)){
                //double sqrtNum = Double.parseDouble(num1);
                double sqrtNum = Math.sqrt(Double.parseDouble(num1));
               tfInputWindow.setText(new BigDecimal(sqrtNum + "") + "");
                num1 = "";
            }
            else if(value.equals(cubeRtSymbol)){
                //double sqrtNum = Double.parseDouble(num1);
                double cubeRtNum = Math.cbrt(Double.parseDouble(num1));
               tfInputWindow.setText(new BigDecimal(cubeRtNum + "") + "");
                num1 = "";
            }
            else if(value.equals("x!"))
            {

                //Joe Code--------------FACTORIAL            
                if(num1.contains("."))
                {
                    tfInputWindow.setText("Error");
                }
                else
                {
                    BigInteger sum = new BigInteger("1");
                    int intNum = Integer.parseInt(num1);

                    for (int i = 1; i <= intNum; i++) 
                    {
                        sum = sum.multiply(new BigInteger(i + ""));
                    
                    }
                
                tfInputWindow.setText(num1 + "!" + " = " + sum);
                txtField.setText("");
                
                }
            }
           
            }
           else
               tfInputWindow.setText("Please enter number to operate on");

        }
    }
    
    public void setUpCalculator()
    {
       //style buttons prior to set up to inherit style prior to set up - LV
       styleButtons(); 
       
       //FontSizes to displayed on tf - Lv
      // answerToDisplay.setFont(Font.font(40));
       tfInputWindow.setFont(Font.font(20));
       
       
       //makes calc pane a gradient blue - LV
       calculatorPane.setStyle("-fx-background-color:  linear-gradient(to bottom, #003366 0%, #009999 100%);");
    
       // sets up the textfield then sets it to top of border pane - LV
       tfInputWindow.setPrefHeight(80);
       tfInputWindow.setPrefColumnCount(3);
       tfInputWindow.setText("Hi");
       tfInputWindow.setStyle("-fx-focus-color: transparent;");
       pane.setTop(tfInputWindow);
       
       //sets calculator pane to center which spans under textfield. - LV
       pane.setCenter(calculatorPane);
       
       
       //button components are added to the calc grid pane
       calculatorPane.setPadding(new Insets(10));
       calculatorPane.setHgap(14);
       calculatorPane.setVgap(9);
       //1st column from the left - LV
       calculatorPane.add(btnSeven, 0, 0);
       calculatorPane.add(btnFour, 0, 1);
       calculatorPane.add(btnOne, 0, 2);
       calculatorPane.add(btnClear, 0, 3);
       //2nd column from left - LV
       calculatorPane.add(btnEight, 1, 0);
       calculatorPane.add(btnFive, 1, 1);
       calculatorPane.add(btnTwo, 1, 2);
       calculatorPane.add(btnZero, 1, 3);
       //3rd column from left - LV
       calculatorPane.add(btnNine, 2, 0);
       calculatorPane.add(btnSix, 2, 1);
       calculatorPane.add(btnThree, 2, 2);
       calculatorPane.add(btnEqual, 2, 3);
       //4th row from left, operators - LV
       calculatorPane.add(btnAdd, 3, 0);
       calculatorPane.add(btnMinus, 3, 1);
       calculatorPane.add(btnDivide, 3, 2);
       calculatorPane.add(btnDecimal, 3, 3);
       //5th row from left - LV
       calculatorPane.add(btnTimes, 4, 0);
       calculatorPane.add(btnSquaredRoot, 4, 2);
       calculatorPane.add(btnXsquared, 4, 1);
       calculatorPane.add(btnMod, 4, 3);
       //6th row from left - LV
       calculatorPane.add(btnXtoTheN, 5, 0);
       calculatorPane.add(btnXcubed, 5, 1);
       calculatorPane.add(btnCubedRoot, 5, 2);
       calculatorPane.add(btnPI, 5, 3);
       
       calculatorPane.add(btnFactorial, 6, 0);
      
    }
    
    public void styleButtons()
    {
        /* all buttons are placed in a group and then an observable list is 
        created and called inside the for loop. Inside the for a button object
        is created. The lst object is casted to a button. Once casted, a round 
        shape is given to the buttons and a soft silver color is applied - LV
        */
        numbers.getChildren().addAll(btnZero,btnOne,btnTwo,btnThree,
                btnFour,btnFive,btnSix,btnSeven,btnEight,btnNine
        );
        
        symbols.getChildren().addAll(btnTimes,btnMinus,
                btnAdd,btnDivide,btnMod,btnFactorial,btnCubedRoot,btnSquaredRoot,
                btnXsquared, btnXcubed, btnXtoTheN, btnClear, btnEqual, btnDecimal, btnPI);
        
        ObservableList lstOfNumbers = numbers.getChildren();
        ObservableList lstOfSymbols = symbols.getChildren();
        
        // this takes care of numeric values only - LV
        for(int i  = 0; i < lstOfNumbers.size(); i++)
        {
            Button bt = (Button)lstOfNumbers.get(i);
            bt.setStyle(
                "-fx-background-radius: 60em; " +
                "-fx-min-width: 60px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 60px; " +
                "-fx-max-height: 60px;" +
                "-fx-background-color: #f2f2f2"
                        ); //end style 
            
            bt.setOnMousePressed(e -> {
            
                bt.setStyle(
                "-fx-background-radius: 60em; " +
                "-fx-min-width: 60px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 60px; " +
                "-fx-max-height: 60px;" +
                "-fx-background-color: #3d5c5c"
                        ); //end style 
            bt.setTextFill(Color.WHITE);
            //bt.setFont(Font.font(24));
            
            });
            
            bt.setOnMouseReleased(e -> {
            
             bt.setStyle(
                "-fx-background-radius: 60em; " +
                "-fx-min-width: 60px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 60px; " +
                "-fx-max-height: 60px;" +
                "-fx-background-color: #f2f2f2"
                        ); //end style 
             
              bt.setTextFill(Color.BLACK);
            
            });
            
        }
     
       //this handles all symbols - LV
      for(int j  = 0; j < lstOfSymbols.size(); j++)
        {
            Button bt = (Button)lstOfSymbols.get(j);
            bt.setStyle(
                "-fx-background-radius: 60em; " +
                "-fx-min-width: 60px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 60px; " +
                "-fx-max-height: 60px;" +
                "-fx-background-color: #3d5c5c"
                        ); //end style 
            bt.setTextFill(Color.WHITE);
            bt.setFont(Font.font(24));
            
             bt.setOnMousePressed(e -> {
             bt.setStyle(
                "-fx-background-radius: 60em; " +
                "-fx-min-width: 60px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 60px; " +
                "-fx-max-height: 60px;" +
                "-fx-background-color: #f2f2f2"
                        ); //end style 
             
              bt.setTextFill(Color.BLACK);
            
            });
            
            bt.setOnMouseReleased(e -> {
            
           bt.setStyle(
                "-fx-background-radius: 60em; " +
                "-fx-min-width: 60px; " +
                "-fx-min-height: 60px; " +
                "-fx-max-width: 60px; " +
                "-fx-max-height: 60px;" +
                "-fx-background-color: #3d5c5c"
                        ); //end style 
            bt.setTextFill(Color.WHITE);
            });
            
        }
      
      //resized these because general style didnt display font correctly - Lv
      btnCubedRoot.setFont(Font.font(22));
      btnSquaredRoot.setFont(Font.font(22));
      btnClear.setFont(Font.font(14));

        
    } // end style button
    
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
