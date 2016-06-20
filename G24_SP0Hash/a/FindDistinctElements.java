

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author: G24
 *         Kunal Krishna
 *          
 */
 



public class FindDistinctElements<T> {

  
    
    public static void main(String[] args) throws FileNotFoundException{
     
        
        
        test1[] testArr = new test1[8];
        FindDistinctElements fde = new FindDistinctElements();
        
            testArr[0] = new test1("kjlmsd");
            testArr[1] = new test1(7);
            testArr[2] = new test1(7);
            testArr[3] = new test1("ghg",123);
            testArr[6] = new test1("kjlmsd");
            testArr[4] = new test1("ghg",123);
            testArr[5] = new test1("ghg",12);
            testArr[7] = new test1("kjlmsd");
        
            
        
        
        //# of Distinct Elements in given array: 
        System.out.println("# ofdistinct elements "+fde.findDistinct(testArr));
        
    }
    
    public   int findDistinct(T[] arr){
     
        //add elements of generic array to hashset
        HashSet hs = new HashSet();
        for(int i=0;i<arr.length;i++){
            hs.add(arr[i]);
            
        }
            
        int distinct = hs.size();
        int i=0;
        
        
        //update array , distinct elements are k so first k positions are occupied by those k elements, 
        //this is done by copying hashset elements in array first keeping array elements k+1...n same.
        for (Iterator it = hs.iterator(); it.hasNext();) {
            T in = (T) it.next();
            
            arr[i]=in;
            i++;
        }
   
        return distinct;
    }
    
}
   


