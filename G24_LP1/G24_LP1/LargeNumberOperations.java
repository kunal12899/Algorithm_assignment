package lp1;

import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author kunal krishna
 */
public class LargeNumberOperations {
    
    public static void main(String args[]){
        String input1="95";
        String input2="75";
        //String input1="90569784495866770974195656280275310090138980613960953881501965823101";
        //String input2="75040970647524038461398929683905540248523961720824412136973299943953";
        LargeNumber num1 = new LargeNumber(input1);
        LargeNumber num2 = new LargeNumber(input2);

        LargeNumber subResult = subLargeNumber(num2, num1);
        
        ListIterator iter = subResult.num.listIterator(subResult.num.size());
        System.out.println();
        while (iter.hasPrevious()) {
        System.out.print(iter.previous());
        }
        System.out.println();
    }
    
    public static LargeNumber subLargeNumber(LargeNumber num1, LargeNumber num2){
        boolean borrow=false, minus=false;
        LargeNumber result = new LargeNumber();
        int int1,int2;
      //  System.out.println("num1.num.get(size "+num1.num.get(num1.num.size()-1));
       // System.out.println("num2.num.get(size "+num2.num.get(num2.num.size()-1));
        
        //setting smaller number to num2 by default
        if(num1.num.size()<num2.num.size()){
            LargeNumber temp=num2;
            num2=num1;
            num1=temp;
            minus=true;
        }
        else if(num1.num.size()==num2.num.size()){
            for(int i=num1.num.size()-1;i>=0;i--){
                if((Integer.parseInt(num1.num.get(i).toString()))<(Integer.parseInt(num2.num.get(i).toString()))){
                    minus=true;
                    LargeNumber temp=num2;
                    num2=num1;
                    num1=temp;
                    break;
                }
                else continue;
            }
        }
        
        //We iterate throught the smaller number
        Iterator itr1 = num1.num.iterator();
        Iterator itr2 = num2.num.iterator();
        while(itr2.hasNext()){
            int1 = Integer.parseInt(itr1.next().toString());
            int2 = Integer.parseInt(itr2.next().toString());
            
            //if we need to borrow from the current integer we subtract one
            if(borrow){
                if(int1==0){
                    int1=9;
                    borrow=true;
                }
                else{
                int1=int1-1;
                borrow=false;
                }
            }
            
            //if subtrahend is lesser than the minuend then add 10 subtrahend
            if(int1<int2){
                int1+=10;
                borrow=true;
            }
            
           // System.out.println("What have we got here BEFORE ADDING: "+int1+ " "+int2);
            result.num.add(int1-int2);
            
            itr2.remove();
            itr1.remove();
        }
        
        //iterating through the larger list
        while(itr1.hasNext()){
            int1 = Integer.parseInt(itr1.next().toString());
            
            if(borrow){
                if(int1==0){
                    int1=9;
                    borrow=true;
                }
                else{
                int1=int1-1;
                borrow=false;
                }
            }
            result.num.add(int1);
            itr1.remove();
        }
        
        //if(minus)
          //  result.num.add("-");
        
        return result;
    }
    
}
