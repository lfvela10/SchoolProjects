import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class InDictionary {

    static Random rand = new Random();
    static String combinations = "";
    
    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
        // letters to use
        String letters = "cfhat";
        // number of words in dictionary
        int n = in.nextInt();
        // create a String array of size n and use for-loop to load words
        
        // remember to change this 
      String [] words = {"a", "at", "bat", "cat", "hat", "kite", "lion", "that", "yellow", "zebra"};
      
      findCombinations(letters, n);
      testCombinations(combinations, words);
 
    } // ends main
    
    
    public static void testCombinations(String letters, String [] words)
    {
      String [] combinationsSplit = combinations.split(" ");
      
      for(int i = 0; i < words.length; i++)
         {
           for(int k = 0; k < combinationsSplit.length; k++)
            {
               if(words[i].matches(combinationsSplit[k]))
               System.out.print(words[i] + " ");
            
            }
         
         }
    
    
    }
    
    public static void findCombinations(String letters, int n)
    {
       int count = 0;
       int length = 2;
       String temp = "";
       int numberOfPermutations = 0;
       int r = 0;
       boolean uniqueFound = false;
       int index = 0;
       String previous = "";
       
       for(int i = 0; i < letters.length(); i++)
          combinations =  combinations + letters.charAt(i) + " ";
          
       while(length <= letters.length())
         {
            temp = "";
            count = 0;
            numberOfPermutations = permutationFormula(letters.length(), length);
            
            while(count < numberOfPermutations)
               {
                  temp = "";
                  uniqueFound = false;
                  index = 0;
                  previous = "";
                  while(uniqueFound == false)
                     {
                        previous = "";
                        index = 0;
                        temp = "";
                        while(index < length)
                          {
                              r = rand.nextInt(letters.length()) + 0;
                              
                              while(previous.contains(r + "") == true)
                                 {
                                    r = rand.nextInt(letters.length()) + 0;
                                 }
                             
                                 temp = temp + letters.charAt(r);
                                 index++;
                                 previous = previous + r + " ";
                          }
                          
                          if(combinations.contains(temp) == false)
                            {
                              uniqueFound = true;
                              count++;
                              combinations = combinations + temp + " ";
                              
                            }
                          
                          
                     }   
                  
            
               }
         
               length++;
         }
    
    
    } // ends find combinations
    
    
   public static int permutationFormula(int n, int r)
       {
         return ((factorial(n)) / (factorial(n - r)));

       }

    public static int factorial(int n)
         {
            int factorial = 1;
            for(int i = 2; i <= n; i++)
               factorial = factorial * i;
               

         
            return factorial;
         }
         
    
    
}
