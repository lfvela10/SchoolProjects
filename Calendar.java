
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class Calendar extends Application
{
    private BorderPane containerPane = new BorderPane();
    private GridPane monthPane = new GridPane();
    private Scene scene;
    private LocalDateTime date = LocalDateTime.now();
    private String appointmentFile = "src/project4/appointmentFile.csv";
    
    Circle highlightToday = new Circle();
    String [] totalMonths = {" ","JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
    String [] nameOfDays = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
    int [] numberOfdaysPerMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // 2 = 29 with leap
    
    int [] currentMonthSetupArray  = {0, 8, 9, 10, 11, 12, 13};
    
    int monthValue = date.getMonthValue();
    int yearValue = date.getYear();
    int dayCounter = 1;
    int currentMonthsetUp = 0;
    int leftOrRightCount = 0;
 
    HBox monthAndYear = new HBox();
    HBox controlBox = new HBox();
    HBox Month_Year_and_controlBox = new HBox();
  
    Button btnLeftArrow = new Button("<");
    Button btnToday = new Button("Today");
    Button btnRightArrow = new Button(">");
    
    Text month = new Text();
    Text year = new Text();
    boolean currentMonthOver= false;
    LocalDateTime currentMonth = LocalDateTime.of(yearValue, Month.of(monthValue), dayCounter, 12, 0);

    int todayLstIndex = 0;  
    boolean todayVisited = false;
    boolean initialSetup = true;
    int count = 0;
   
    
    @Override
    public void start(Stage primaryStage)
    {
        
        scene = new Scene(containerPane, 900, 650);
        primaryStage.setResizable(false);
        setupCalendarPane();
        primaryStage.setTitle("Calendar");
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(620);
        primaryStage.setScene(scene);
        primaryStage.show();
  
        
        
    }

    
    public void setupCalendarPane()
    { 
        //controls HBox for the control pannel
        controlBox.getChildren().addAll(btnLeftArrow, btnToday, btnRightArrow);
        controlBox.setPadding(new Insets(15));
        controlBox.setAlignment(Pos.BASELINE_RIGHT);
        controlBox.prefWidthProperty().bind(Month_Year_and_controlBox.widthProperty().divide(2));
        controlBox.setSpacing(15);
        
        // controls initial set up to month and year.
        month.setText(date.getMonth().toString());  
        month.setFont(Font.font(30));
        
        
        year.setText(yearValue + "");
        year.setFont(Font.font(30));
        
        monthAndYear.getChildren().addAll(month, year);    
        monthAndYear.setAlignment(Pos.BASELINE_LEFT);
        monthAndYear.setPadding(new Insets(15));
        monthAndYear.prefWidthProperty().bind(Month_Year_and_controlBox.widthProperty().divide(2));
       
        monthAndYear.setSpacing(15);
        
        
        //combines both hboxs to current days months and time
        Month_Year_and_controlBox.getChildren().addAll(monthAndYear, controlBox);
        
         
         
        
        
      
        //sets top
        containerPane.setTop(Month_Year_and_controlBox);

        // adds needed rectangles to create the month
        for(int row = 0; row < 7; row++)
           {
            for(int col = 0; col < 7; col++)
               {
                  
                  Rectangle rec;                
                  StackPane pane;
                  
                  if(row == 0)
                  {
                    pane = new StackPane();
                    rec = new Rectangle(120, 40);                  
                    rec.setStroke(Color.LIGHTBLUE);
                    rec.setFill(scene.getFill()); 
                    rec.setId(nameOfDays[col]);
                    pane.getChildren().add(rec);
                    monthPane.add(pane, col, row);
                    
                    
                  }
                  else
                  {
                    pane = new StackPane();
                    rec = new Rectangle(120, 80);
                    rec.setStroke(Color.LIGHTBLUE);
                    rec.setFill(scene.getFill());
                    pane.getChildren().add(rec);
                    
                    monthPane.add(pane, col, row);
                  }
               }  
           }    
        
        monthPane.setPadding(new Insets(20));
        containerPane.setCenter(monthPane);
        
        
        fillUpMonth(); 
    }
     
    public void fillUpMonth()
    {
      
       ObservableList lst = monthPane.getChildren();
       
       
       /*lines 180 through 185 are used to find the int value of the first day of 
       the week so according to java, the day-of-week, from 1 (Monday) to 7 
       (Sunday), I set up currentMonthSetupArray to change this value into a 
       value i could work with in my observable list. 0 acts as a placeholder for
       7,
       */
       currentMonth = LocalDateTime.of(yearValue, Month.of(monthValue), dayCounter, 12, 0);
       currentMonthsetUp = currentMonth.getDayOfWeek().getValue();
       
       if( currentMonthsetUp != 7)
           currentMonthsetUp = currentMonthSetupArray[ currentMonthsetUp];
       
        count++;
       
       
       if(initialSetup == true && count == 1)
       {
           
       //adds name of days,  will remain unchanged through run
            for(int i = 0; i < 7; i++ )
                 {
                     Text txt = new Text();
                     txt.setText(nameOfDays[i]);
                     txt.setTextAlignment(TextAlignment.CENTER);
                     StackPane pane = (StackPane)lst.get(i);
                     pane.getChildren().add(txt);
                 }

             /* initial setup based on the day of the week of the first day of current
            month. This loop runs from then until the next month once the current month
            fills up.
            */
             for(int i = currentMonthsetUp; i < lst.size(); i++)
                 {
                    Text txt = new Text();
                    txt.setText(dayCounter + "");
                    StackPane pane = (StackPane)lst.get(i);

                    if(currentMonthOver == false && dayCounter == date.getDayOfMonth())
                    {  
                        highlightToday.setRadius(20);
                        highlightToday.setFill(Color.RED);
                        txt.setFill(Color.WHITE);
                        pane.getChildren().addAll(highlightToday,txt);
                        todayLstIndex = i;
                    }
                    else
                    pane.getChildren().add(txt);

                    dayCounter++;

                    if(currentMonthOver == true)
                        txt.setFill(Color.GRAY);

                    if(dayCounter > numberOfdaysPerMonth[monthValue])
                     {
                        
                        dayCounter = 1;
                        currentMonthOver = true;

                     }
                     
                 } //ends initial forloop



             /* this second forloop will only be entered if inital month doesnt 
             begin on sunday like april does. this loop will fill in grey the days of
             previous month only up until the first of the current month. 
             If the current month does begin on a sunday it is irrelevant */
             
             if(monthValue-1 == 0)
              dayCounter = numberOfdaysPerMonth[12];
             else
              dayCounter = numberOfdaysPerMonth[monthValue-1];

             for(int i = currentMonthsetUp - 1 ; i >= 7; i--)
                {
                     Text txt = new Text();
                     txt.setText(dayCounter + "");
                     txt.setTextAlignment(TextAlignment.CENTER);

                     StackPane pane = (StackPane)lst.get(i);
                   //  pane.getChildren().remove(1);
                     txt.setFill(Color.GRAY);


                     pane.getChildren().add(txt);
                     dayCounter--;
                }

             currentMonthOver = false;//ends initialfill
             initialSetup = false;
            
       } //ends first if
       
       
       /*this block of code is used once the calendar has originally been filled
        since it removes from the stackpane */
       
       if (initialSetup == false && count >= 2)
       {
            for(int i = currentMonthsetUp; i < lst.size(); i++)
               {

                 Text txt = new Text();
                 txt.setText(dayCounter + "");
                 txt.setTextAlignment(TextAlignment.CENTER);
                 StackPane pane = (StackPane)lst.get(i);

                //this segment is added to remove the red circle and number
                if(i == todayLstIndex && leftOrRightCount == 1)
                    {
                      pane.getChildren().remove(2);
                      pane.getChildren().remove(1);
                      pane.getChildren().add(txt);
                      
                    }
                else if(currentMonthOver == false && dayCounter == date.getDayOfMonth() && monthValue == date.getMonth().getValue() && yearValue == date.getYear())
                  {
                    pane.getChildren().remove(1);
                    highlightToday.setRadius(20);
                    highlightToday.setFill(Color.RED);
                    txt.setFill(Color.WHITE);
                    pane.getChildren().addAll(highlightToday,txt);
                    leftOrRightCount = 0;
     
                  }
                else 
                {
                    pane.getChildren().remove(1);
                    pane.getChildren().add(txt);
                    
                }
                
                dayCounter++;

                if(currentMonthOver == true)
                   txt.setFill(Color.GRAY);

                if(dayCounter > numberOfdaysPerMonth[monthValue])
                {
                 
                 dayCounter = 1;
                 currentMonthOver = true; 

                 }

             }
           
          //fills the empty front spaces in the calendar dating back to previous month
          if(monthValue-1 == 0)
              dayCounter = numberOfdaysPerMonth[12];
          else
              dayCounter = numberOfdaysPerMonth[monthValue-1];
          
          for(int k = currentMonthsetUp - 1 ; k >= 7; k--)
           {
                Text txt = new Text();
                txt.setText(dayCounter + "");
                txt.setTextAlignment(TextAlignment.CENTER);
                
                StackPane pane = (StackPane)lst.get(k);
                pane.getChildren().remove(1);
                txt.setFill(Color.GRAY);
                
            
                pane.getChildren().add(txt);
                dayCounter--;
           }
          
        currentMonthOver = false;
        
      }
        
       btnRightArrow.setOnAction(e -> {
       
       leftOrRightCount++;
       todayVisited = false;
       monthValue++;
       dayCounter = 1;
           
        if(monthValue > 12)
           {
             monthValue = 1;  
             month.setText(totalMonths[monthValue]);
             yearValue++;
             year.setText(yearValue + "");
             
             LocalDate leapCheck = LocalDate.of(yearValue, Month.JANUARY, 1);
              
             if (leapCheck.isLeapYear())
                 numberOfdaysPerMonth[2] = 29;
             else 
                 numberOfdaysPerMonth[2] = 28;
           
            }
           else
                month.setText(totalMonths[monthValue]);
      
        currentMonthOver = false;
           
           fillUpMonth();
           
        });
       
       btnToday.setOnAction(e ->{
            
           if(leftOrRightCount != 0)
           {
            monthValue = date.getMonth().getValue();
            month.setText(totalMonths[monthValue]);
            year.setText(date.getYear() + "");
            yearValue = date.getYear();
            leftOrRightCount = 0;
            dayCounter = 1;
           
          
            fillUpMonth();
           }
      
       currentMonthOver = false;
       
       }); // ends today
       
       btnLeftArrow.setOnAction(e -> {
           
           leftOrRightCount++;
           todayVisited = false;
           monthValue--;
           dayCounter = 1;
           
           
           if(monthValue == 0)
           {
               monthValue = 12;
               yearValue = yearValue - 1;
               month.setText(totalMonths[monthValue]);
               year.setText(yearValue + "");
             
             LocalDate leapCheck = LocalDate.of(yearValue, Month.JANUARY, 1);
              
             if (leapCheck.isLeapYear())
                 numberOfdaysPerMonth[2] = 29;
             else 
                 numberOfdaysPerMonth[2] = 28;
               
           }
            else
                month.setText(totalMonths[monthValue]);
           
          currentMonthOver = false;
               
         fillUpMonth();
           
       });

    } 
    
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
