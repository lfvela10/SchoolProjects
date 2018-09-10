public class GameOfLife
{
   private int [][] grid;
   
   public GameOfLife(int a, int b)
   {
      this.grid = new int[a][b];
   }
   
   public int [][] getGrid()
   {
      return this.grid; 
   }
  
   
   public  void nextIteration()
   {
      int count = 0;
      int row = this.grid.length;
      int col = this.grid[0].length;
      int tempGrid[][] = new int [row][col];
      
      for (int i = 1; i < row - 1; i++) 
      {
         for (int k = 1; k < col - 1; k++)
         {
            //imports number of neighbors
            count = this.countN(i, k); 
            
            // checks for the rules of life
            if(this.grid[i][k] == 0 && count == 3)
               tempGrid[i][k] = 1;
               
            else if(this.grid[i][k] == 1 && count < 2)
               tempGrid[i][k] = 0;
               
            else if(this.grid[i][k] == 1 && count > 3)
               tempGrid[i][k] = 0;
            
            else if(this.grid[i][k] == 1 && (count == 3 || count == 2))
               tempGrid[i][k] = 1;
            else 
               tempGrid[i][k] = 0;
                   
         }
      
      } 
      
      this.grid = tempGrid;
      
   } //next iteration ends
   
   public int countN(int i, int k)
   { 
      /* This method counts the number of neighbors. If the value of
      grid[i][k] or which ever variation you see below has a value of 
      1 (1 = life) then you increment the count. This method is used to
      check the rules of life in the nextIteration() method.*/
      
      int count = 0;
      
      if (this.grid[i][k-1] == 1)
         count++;
         
      if (this.grid[i][k + 1] == 1) 
          count++; 
 
      if (this.grid[i + 1][k] ==1)
         count++;
     
      if (this.grid[i - 1][k] == 1)
         count++;
         
      if (this.grid[i - 1] [k - 1] ==1)
         count++; 
   
      if (this.grid[i + 1] [k +1] == 1)
         count++;
 
      if (this.grid[i - 1][k + 1] == 1) 
         count++;
         
      if (this.grid[i + 1][k -1] == 1)      
         count++; 
      
      return count;
      
   } // count method ends
   
    
   public void randomizeInitialGrid()
   {   
      for(int row = 0; row < this.grid.length; row++)
      {
         for(int col = 0; col < this.grid[row].length; col++)
        
            this.grid[row][col] = (int)(Math.random() * 2); 
       }
   
   } // end of rand
   
}// end of class
