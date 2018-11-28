import java.sql.*;
import java.util.Scanner;

public class ConnectionToDb {

public static void main(String[]args){
	
	Connection conn = null;
        Scanner kbd = new Scanner(System.in);
	
	try {
    	conn =
        DriverManager.getConnection("jdbc:mysql://cs.neiu.edu:3306/db_Fall18_lfvela10?serverTimezone=UTC&" +
                                   "user=lfvela10&password=InsertPassword");
        
        /*System.out.println("Enter the exercise: ");
        String exercise = kbd.nextLine();
        
        System.out.println("Enter weight amount: ");
        String weight = kbd.nextLine();
        
        System.out.println("Enter an integer for reps: ");
        int reps = kbd.nextInt();
        
        
        String sql = "INSERT INTO workout VALUES('" + exercise + "'," + "'" + weight +"'," + reps +")"; 
        stmt.executeUpdate(sql);
	*/
        
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM workout";
       ResultSet results = stmt.executeQuery(sql);
       results.first();
       int i = 1;
 
       while(results.isAfterLast() == false)    
       {
           System.out.print(i + ". ");
           
           if(i < 10)
           System.out.print("Exercise Name: " + results.getString(1) + "\n   Weight: " + results.getString(2) + "\n   Reps: " +
                   results.getInt(3));
           else
           System.out.print("Exercise Name: " + results.getString(1) + "\n    Weight: " + results.getString(2) + "\n    Reps: " +
                   results.getInt(3));
               
               
           
           results.next();
           System.out.println();
           i++;
       } // ends while
       
   
	} catch (SQLException ex) {
    // handle any errors
    System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());
	}
}
}
