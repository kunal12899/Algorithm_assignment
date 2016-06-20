/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author kunal krishna
 */
public class sp1b_largeNumber_add_sub {
    
    public static void main(String args[]){
    
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        List<Integer> z1 = new ArrayList<>();
        List<Integer> z2 = new ArrayList<>();
        
        System.out.println("Please enter the base of the numbers given in the list!");
        Scanner sc = new Scanner(System.in);
        int b=sc.nextInt();
        
        
        //input used for addition of numbers to base 10
//        x.add(4);
//        x.add(2);
//        x.add(8);
//        x.add(1);
//        x.add(4);
//        x.add(7);
//        x.add(3);
//        x.add(7);
//        x.add(0);
//        x.add(1);
//        y.add(9);
//        y.add(9);
//        y.add(1);

        
        //input used for addition of numbers to base 2
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(1);
//        x.add(0);
//        y.add(1);
//        y.add(1);
//        y.add(1);
//        y.add(0);
        
        //input for base 8 numbers
        x.add(6);
        x.add(5);
        x.add(4);
        y.add(3);
        y.add(7);
        y.add(1);
        
        //starting timer before addition
        long startTime = System.currentTimeMillis();
        add(x, y, z1, b);
        long endTime   = System.currentTimeMillis();
        //end time for subtraction
        long totalTime = endTime - startTime;
        System.out.println("Total time in ms for addition: "+totalTime);
        
        System.out.println("Addition of the given numbers:");
        for(int i=z1.size()-1;i>=0;i--)
        System.out.print(z1.get(i)+" ");
        System.out.println();
        
        //starting timer before subtraction
        long startTime1 = System.currentTimeMillis();
        subtract(x, y, z2, b);
        long endTime1   = System.currentTimeMillis();
        //end time for subtraction
        long totalTime1 = endTime1 - startTime1;
        System.out.println("Total time in ms for subtraction: "+totalTime1);
        
        System.out.println("Subtraction of the given numbers:");
        for(int i=z2.size()-1;i>=0;i--)
            System.out.print(z2.get(i)+" ");
    }
    
    public static void add(List<Integer> x, List<Integer>  y,List<Integer> z1, int b) {
   	  // Return z = x + y.  Numbers are stored using base b.
	  // The "digits" are stored in the list with the least
   	  // significant digit first.  For example, if b = 10, then
	  // the number 709 will be stored as 9 -> 0 -> 7.
	  // Assume that b is small enough that you will not get any
	  // overflow of numbers during the operation.
            
            Iterator listX = x.iterator();
            Iterator listY = y.iterator();
            
            Integer sum=0,carry=0;
            //iterating through both the given lists simultaneously and adding each digit of each list  
            while (listX.hasNext() && listY.hasNext()) {
                sum = (Integer)listX.next() + (Integer)listY.next() +carry;
                z1.add(sum%b);
                carry=sum/b;
            }
            
            //if the list Y is smaller in size when compared to list X, then this will be executed
                while(listX.hasNext()){
                sum = (Integer)listX.next() +carry;
                z1.add(sum%b);
                carry=sum/b;
                }
            
            //if the list X is smaller in size when compared to list Y, then this will be executed
                while(listY.hasNext()){
                sum = (Integer)listY.next() +carry;
                z1.add(sum%b);
                carry=sum/b;
                }
                
                //any additional carry after adding all the digits is added to the Z list
                if(carry>0) z1.add(carry);
            
   }

   public static void subtract(List<Integer> x, List<Integer>  y,List<Integer> z2, int b) {
   	  // Return z = x - y.  Numbers are stored using base b.
	  // Assume that x >= y.
            Iterator listX = x.iterator();
            Iterator listY = y.iterator();
            
            Integer minuend=0, subtrahend=0, diff=0;
            String result="";
            
            int i=0,j=0;
            Double xpow, ypow;
            
            //converting the given number to base 10
            try{

            //iterating through both lists simultaneously
            while(listX.hasNext() && listY.hasNext()){
            xpow=Math.pow(b, i);
            ypow=Math.pow(b, j);
            minuend+=((Integer)listX.next()*xpow.intValue());
            subtrahend+=((Integer)listY.next()*ypow.intValue());        
                    i++; j++;
            }
            
            //continue iterating through list X when list Y is smaller in size
            while(listX.hasNext()){
            xpow=Math.pow(b, i);
            minuend+=((Integer)listX.next()*xpow.intValue());
                    i++;
            }
            
            //continue iterating through list Y when list Z is smaller in size
            while(listY.hasNext()){
            ypow=Math.pow(b, j);
            subtrahend+=((Integer)listY.next()*ypow.intValue());               
                    j++;
            }
                
            diff=minuend-subtrahend;
            
            //adding the result to the output list
            while(diff!=0){
                z2.add(diff%b);
                diff=diff/b;
            }
            
            } catch(Exception e){
                e.printStackTrace();
            }
       
   }
}
