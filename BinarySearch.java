/**
 *
 * @author luisvelazquez
 */


import java.util.*;

public class BinarySearch 
{
    public static void main(String[] args)
    {
        int[] myArray = {10, 25, 30, 40, 50, 65, 70, 90};
        System.out.println("The original array is " + Arrays.toString(myArray));  
		
        System.out.println("Is 35 in the array? " + binarySearchIter(myArray, 35));
        System.out.println("Is 70 in the array? " + binarySearchIter(myArray, 70));
        
        System.out.println("Is 35 in the array? " + binarySearchRec(myArray, 35));
        System.out.println("Is 70 in the array? " + binarySearchRec(myArray, 70));
    }
    
    // iterative binary search
    public static boolean binarySearchIter(int[] numArray, int value)
    {
        int first = 0;
        int last = numArray.length - 1;
        
        while (first <= last)
        {
            int mid = (first + last) / 2;
            
            if (value < numArray[mid])
                last = mid - 1;
            else if (value > numArray[mid])
                first = mid + 1;
            else
                return true;
        }
        
        return false;
    }
    
    // recursive helper method for binarySearchRec
    public static boolean binarySearchRecHelper(int[] numArray, int value, int first, int last)
    {
        if (first > last)
            return false;
        
        int mid = (first + last) / 2;
        
        if (value < numArray[mid])
            return binarySearchRecHelper(numArray, value, first, mid - 1);
        else if (value > numArray[mid])
            return binarySearchRecHelper(numArray, value, mid + 1, last);
        else
            return true;
    }
    
    // recursive binary search
    public static boolean binarySearchRec(int[] numArray, int value)
    {
        return binarySearchRecHelper(numArray, value, 0, numArray.length - 1);
    }
}
