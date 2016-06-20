/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 *
 * @author  kunal 
 */
public class LargeNumber {
    static boolean borrow=false, minus=false, num1Minus=false, num2Minus=false;;
    LinkedList num;                      
    
    public LargeNumber(String strInput){
        num= new LinkedList<Integer>();
        char[] charInput= strInput.toCharArray();
        
        for(int i=0;i<charInput.length;i++)
            num.add(charInput[charInput.length-1-i]);
    }
    
    public LargeNumber(Long lngInput){
        num= new LinkedList<Integer>();
        
        while(lngInput>0){
            num.add(lngInput%10);
            lngInput=lngInput/10;
        }
    }
    
    public LargeNumber (){
        num= new LinkedList<Integer>();
    }
    
    
    public static void main(String args[]){
        //String input1="99";//"-75040970647524038461398929683905540248523961720824412136973299943953";   
        //String input2="100000";//"90569784495866770974195656280275310090138980613960953881501965823101";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter two number for Addition and Subtraction");
        System.out.println("Enter the first number.");
        String input1 = sc.nextLine();
        System.out.println("Enter the second number.");
        String input2 = sc.nextLine();
        System.out.println("Enter number for Power.");
        String powStr = sc.nextLine();
        System.out.println("Enter long value for Exponent.");
        long exponent = sc.nextLong();
        
        LargeNumber num1 = new LargeNumber(input1);
        LargeNumber num2 = new LargeNumber(input2);
        LargeNumber pow = new LargeNumber(powStr);
        
        int base=10;
        
        //time calculation for addition
        long startTime1 = System.currentTimeMillis();
        LargeNumber addition = add(num1, num2);
        long endTime1   = System.currentTimeMillis();
        long totalTime1 = endTime1 - startTime1;
        System.out.println("Total time for Addition is: "+totalTime1+"ms");
        printList(addition, base);
        
        //time calculation for subtraction
        System.out.println();
        long startTime2 = System.currentTimeMillis();
        LargeNumber subResult = subLargeNumber(num1, num2);
        long endTime2   = System.currentTimeMillis();
        long totalTime2 = endTime2 - startTime2;
        System.out.println("Total time for Subtraction is: "+totalTime2+"ms");
        printList(subResult, base);
        
        //time calculation for multiplication
        System.out.println();
        long startTime3 = System.currentTimeMillis();
        LargeNumber mulResult= Multiply(num1,num2);
        long endTime3   = System.currentTimeMillis();
        long totalTime3 = endTime3 - startTime3;
        System.out.println("Total time in ms for Multiplication is: "+totalTime3+"ms");
        printList(mulResult, base);
        
        //time calculation for exponent
        System.out.println();
        long startTime4 = System.currentTimeMillis();
        LargeNumber powResult= powerLargeNumber(pow,exponent);
        long endTime4   = System.currentTimeMillis();
        long totalTime4 = endTime4 - startTime4;
        System.out.println("Total time in ms for exponent is: "+totalTime4+"ms");
        printList(powResult, base);
        
    }
    
    //multiplcation module
    public static LargeNumber Multiply(LargeNumber num1, LargeNumber num2){
       int m=Math.max(num1.num.size(),num2.num.size());       
       int n= (m/2)+(m%2);
       
        //checking if either of the numbers have a negative sign
        if(checkMinus(num1)){
            num1Minus=true;
        }
        if(checkMinus(num2)){
            num2Minus=true;
        }
        
        //when signs are same we need to add
        if(num1Minus && num2Minus)
            minus=false;
        
        if((!num1Minus && num2Minus) || (num1Minus && !num2Minus))
            minus=true;
        
        if(num1Minus)
           num1.num.remove(num1.num.size()-1);
            
        if(num2Minus)
            num2.num.remove(num2.num.size()-1);
        
        LargeNumber[] nums=new LargeNumber[2];
        if(num1.num.size()!= num2.num.size()){      
            nums=checksize(num1, num2);
            num1=nums[0];
            num2=nums[1];
        }
       
       
        if(m<=1){
            Integer int1=Integer.parseInt(num1.num.get(0).toString());
            Integer int2=Integer.parseInt(num2.num.get(0).toString());
            Integer result=int1*int2;
            String s= String.valueOf(result);
            LargeNumber res= new LargeNumber(s);
            return res;          
        }
        else{      
        LargeNumber a1=topHalf(num1,n,m);
        LargeNumber a2=BottomHalf(num1,n,m);
        LargeNumber b1=topHalf(num2,n,m);
        LargeNumber b2=BottomHalf(num2,n,m);
        
        LargeNumber x0=Multiply(a1,b1);
        
        LargeNumber x1=Multiply(LargeNumber.add(a1, a2),LargeNumber.add(b1,b2));
        
        if(checkMinus(x1)){
          x1.num.remove(x1.num.size()-1);
        }
        
        LargeNumber x2=Multiply(a2,b2);
        
       
        LargeNumber aa, bb;
        
        if(m%2==0){
            bb=prx1(x1,x0,x2,n);
            aa=prx0(x0,m);    
        }
        
        else{
            bb=prx1(x1,x0,x2,n-(m%n));    
            aa=prx0(x0,m-1);        
        } 
        
        LargeNumber[] res1= new LargeNumber[2];
        
        LargeNumber ss=LargeNumber.add(aa,bb);
        
        LargeNumber[] res2= new LargeNumber[2];
         
        LargeNumber ss1= LargeNumber.add(ss,x2);
                
        //removing leading zeros
        ss1=removeLeadingZero(ss1);
        
          if(minus){
            ss1.num.add("-");
          }
          return ss1;
        }    
   } 
    public static LargeNumber prx0(LargeNumber x0, int n){
        for(int i=0;i<n;i++){ 
                 x0.num.addFirst(0);
            }
        return x0;
    }
    
    public static LargeNumber prx1(LargeNumber x1, LargeNumber x0, LargeNumber x2, int n){
        LargeNumber[] res= new LargeNumber[2];
        LargeNumber[] res2= new LargeNumber[2];
        res[0]=x1;
        res[1]=x0;
        
        LargeNumber p=LargeNumber.subLargeNumber(res[0],res[1]);
         
        res2[0]=p;
        res2[1]=x2;
         
        LargeNumber res1 = LargeNumber.subLargeNumber(res2[0],res2[1]);

           for(int i=0;i<n;i++){
                 res1.num.addFirst(0);
            }             
      return res1;       
    }
    
    
    //checking size of both the numbers and add zeroes to make the size same
    public static LargeNumber[] checksize(LargeNumber num1, LargeNumber num2){
       LargeNumber[] res= new LargeNumber[2];
         res[0]=num1;
         res[1]=num2;
       if(num1.num.size()!= num2.num.size()){                  
            if (num1.num.size()> num2.num.size()) {
	     res[1]=addMSZeros(num2,(num1.num.size() - num2.num.size()));
		} 
           else  {
	      res[0]=addMSZeros(num1, num2.num.size()- num1.num.size());
		}
    }
    return res;
    }
   
    //adding zeros to MSB
    public static LargeNumber addMSZeros(LargeNumber n,int number){
       for(int i=0;i<number;i++){
        n.num.addLast(0);
       }
       return n;
    }
    
    //adding zeros to LSB
    public static LargeNumber addLSZeros(LargeNumber n,int number){
       for(int i=0;i<number;i++){
        n.num.addFirst(0);
       }
   return n;
    }   

    //creating a2 and b2 for multiplication
   public static LargeNumber BottomHalf(LargeNumber bottom, int n, int m){
       StringBuilder sb = new StringBuilder();   

       if(m%2==0){
       for(int i=0;i<n;i++)
           sb.append(bottom.num.get(i).toString());
       }
       else {
       for(int i=0;i<n-1;i++)
           sb.append(bottom.num.get(i).toString());
       }
       
       String s= sb.reverse().toString();
       LargeNumber result=new LargeNumber(s);
   return result;
   }
   
      //creating a1 and b1 for multiplication
   public static LargeNumber topHalf(LargeNumber top, int n, int m){
       StringBuilder sb = new StringBuilder(); 
       if(m%2==0){
       for(int i=n;i<top.num.size();i++)
           sb.append(top.num.get(i).toString());
       }
       else {
       for(int i=n-1;i<top.num.size();i++)
           sb.append(top.num.get(i).toString());
       }
       String s= sb.reverse().toString();
       LargeNumber result=new LargeNumber(s);
   return result;
   }
    
 
    //function to add two large numbers
    public static LargeNumber add(LargeNumber num1, LargeNumber num2) {
        LargeNumber ans = new LargeNumber();
        
        int sum = 0, carry = 0;
        
        //checking if either of the numbers have a negative sign
        if(checkMinus(num1)){
            num1Minus=true;
        }
        
        if(checkMinus(num2)){
            num2Minus=true;
        }
        
        //when signs are not same we need to subtract
        if(!num1Minus && num2Minus){
            num1Minus=false;
            num2Minus=false;
            minus=false;
            ans = subLargeNumber(num1, num2);
            return ans;
        }
        else if(num1Minus && !num2Minus){
            num1Minus=false;
            num2Minus=false;
            minus=false;
            ans = subLargeNumber(num1, num2);  
            return ans;
        }
        else if(num1Minus && num2Minus)
            minus=true;
        
        
          //removing minus sign before addition
            if(num1Minus)
            num1.num.remove(num1.num.size()-1);
            if(num2Minus)
            num2.num.remove(num2.num.size()-1);
            
        //setting smaller number to num2 by default
        if(num1.num.size()<num2.num.size()){
            if(num2Minus)
                        minus=true;
                    else
                        minus=false;
        }
        else if(num1.num.size()==num2.num.size()){
            for(int i=num1.num.size()-1;i>=0;i--){
                if((Integer.parseInt(num1.num.get(i).toString()))<(Integer.parseInt(num2.num.get(i).toString()))){
                    if(num2Minus)
                        minus=true;
                    else
                        minus=false;
                    break;
                }
                else {
                    if(num1Minus)
                        minus=true;
                    else
                        minus=false;
                    break;
                }
            }
        }
        
        //started iterator here coz remove operation were performed before this
        Iterator listX = num1.num.iterator();
        Iterator listY = num2.num.iterator();
        
        //iterating through both the given lists simultaneously and adding each digit of each list  
        while (listX.hasNext() && listY.hasNext()) {
            sum = Integer.parseInt(listX.next().toString()) + Integer.parseInt(listY.next().toString()) + carry;
            ans.num.add(sum % 10);
            carry = sum / 10;
        }

        //if the list Y is smaller in size when compared to list X, then this will be executed
        while (listX.hasNext()) {
            sum = Integer.parseInt(listX.next().toString()) + carry;
            ans.num.add(sum % 10);
            carry = sum / 10;
        }

        //if the list X is smaller in size when compared to list Y, then this will be executed
        while (listY.hasNext()) {
            sum = Integer.parseInt(listY.next().toString()) + carry;
            ans.num.add(sum % 10);
            carry = sum / 10;
        }

        //any additional carry after adding all the digits is added to the Z list
        if (carry > 0) {
            ans.num.add(carry);
        }
        
        //adding minus if required
        if(minus){
            ans.num.add("-");
            minus=false;
        }
        
        return ans;
    }
    
    //function to subtract two large numbers
    public static LargeNumber subLargeNumber(LargeNumber num1, LargeNumber num2){
        LargeNumber result = new LargeNumber();
        int int1,int2;
        
        //checking if either of the numbers have a negative sign
        if(checkMinus(num1)){
            num1Minus=true;
        }
        if(checkMinus(num2)){
            num2Minus=true;
        }
        
        //when signs are same we need to add
        if(!num1Minus && num2Minus){
            num1Minus=false;
            num2Minus=false;
            minus=false;
            //inserting minus to show that same sign means addition
            num2.num.remove(num2.num.size()-1);
            result = add(num1, num2);
            return result;
        }
        else if(num1Minus && !num2Minus){
            num1Minus=false;
            num2Minus=false;
            minus=false;
            //inserting minus to show that same sign means addition
            num2.num.add("-");
            result = add(num1, num2);  
            return result;
        }
        
        //removing minus sign before subtraction
            if(num1Minus)
            num1.num.remove(num1.num.size()-1);
            if(num2Minus)
            num2.num.remove(num2.num.size()-1);
            
        //setting smaller number to num2 by default
        if(num1.num.size()<num2.num.size()){
            LargeNumber temp=num2;
            num2=num1;
            num1=temp;  
            if(num2Minus){ 
                        minus=false;}
                    else
                        minus=true;
        }
        else if(num1.num.size()==num2.num.size()){
            for(int i=num1.num.size()-1;i>=0;i--){
                if((Integer.parseInt(num1.num.get(i).toString()))<(Integer.parseInt(num2.num.get(i).toString()))){
                    LargeNumber temp=num2;
                    num2=num1;
                    num1=temp;
                    if(num2Minus)
                        minus=false;
                    else
                        minus=true;
                    break;
                }
                else {
                    if(num1Minus)
                        minus=true;
                    else
                        minus=false;
                    break;
                }
            }
        }
        else{
            if(num1Minus)
            minus=true;
        }
        
        //We iterate throught the smaller number
        Iterator itr1 = num1.num.iterator();
        Iterator itr2 = num2.num.iterator();
        while(itr2.hasNext()){
            int1 = Integer.parseInt(itr1.next().toString());
            int2 = Integer.parseInt(itr2.next().toString());
            
            //if we need to borrow from the current integer we subtract one
            int1 = isBorrow(int1);
            
            //if subtrahend is lesser than the minuend then add 10 subtrahend
            if(int1<int2){
                int1+=10;
                borrow=true;
            }
            
            result.num.add(int1-int2);
        }
        
        //iterating through the larger list
        while(itr1.hasNext()){
            int1 = Integer.parseInt(itr1.next().toString());
            
            int1 = isBorrow(int1);
            result.num.add(int1);
        }
        
        //removing leading zeros            
        result=removeLeadingZero(result);
           
        if(minus){
            result.num.add("-");
            minus=false;
        }  
        return result;
    }
    
        public static int isBorrow(int int1){
            if(borrow)
            {
                if(int1==0){
                    int1=9;
                    borrow=true;
                }
                else{
                int1=int1-1;
                borrow=false;
                }
            }
            return int1;
        }
        
        public static boolean checkMinus(LargeNumber num){
            if(num.num.size()>0){
            if(num.num.get(num.num.size()-1).toString().equals("-"))
                return true;
            return false;
            }
            else return false;
          }
        
        public static void printList(LargeNumber res, int base){
        System.out.print("Base = "+base+" :");
        ListIterator iter2 = res.num.listIterator(res.num.size());
        while (iter2.hasPrevious()) {
        System.out.print(" "+iter2.previous());
        }
        System.out.println();
        }
        
        public static LargeNumber removeLeadingZero(LargeNumber result){
            int j=result.num.size()-1;
           while(j>0){
            if(result.num.get(j).toString().equals("0")){
                result.num.remove(j);
                j--;
                if(!result.num.get(j).toString().equals("0"))
                break;   
            }
            else break;
        }
           return result;
        }
        
        public static LargeNumber powerLargeNumber(LargeNumber x, long y)
        {
        LargeNumber temp;
        if( y == 0)
        return new LargeNumber("1");
       
            temp = powerLargeNumber(x, y/2);       
            if (y%2 == 0)
            {
                return Multiply(temp,temp);
            }
            else
            {
                if(y > 0)
                {
                    LargeNumber n=null;
                    n=Multiply(x,temp);
                    return Multiply(n,temp);
                }
                else
                {
                    LargeNumber n=null;
                    n=Multiply(temp,temp);
                    return n;
                }
            }
        }
}