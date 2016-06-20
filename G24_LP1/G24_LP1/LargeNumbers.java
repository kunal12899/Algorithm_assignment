
import java.util.ArrayList;

import java.util.Iterator;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kunal krishna
 */
public class LargeNumbers {

    ArrayList<Integer> al;
    static long counter = 0;

    public LargeNumbers(String s) {

        char[] c = s.toCharArray();
        al = new ArrayList<>();
        for (int i = c.length - 1; i >= 0; i--) {
            al.add(Integer.parseInt(Character.toString(c[i])));
        }

    }

    public LargeNumbers() {
        al = new ArrayList<>();
    }

    public static void main(String[] args) {

        LargeNumbers num1 = new LargeNumbers("75040970647524038461398929683905540248523961720824412136973299943953");
        LargeNumbers num2 = new LargeNumbers("90569784495866770974195656280275310090138980613960953881501965823101");

        //LargeNumbers num1 = new LargeNumbers("3");
        //LargeNumbers num2 = new LargeNumbers("2");
        LargeNumbers addition = add(num1, num2);
        
        LargeNumbers diff = subtract(num1, num2);
        System.out.println("addition\t" + convertToString(addition));
        
        System.out.println("diff\t\t"+convertToString(diff));

    }

    public static String convertToString(LargeNumbers lanum) {

        StringBuilder sb = new StringBuilder();
        for (Integer number : lanum.al) {
            sb.append(number != null ? number.toString() : "");
        }

        return sb.reverse().toString();
    }

    public static LargeNumbers add(LargeNumbers num1, LargeNumbers num2) {

        Iterator listX = num1.al.iterator();
        Iterator listY = num2.al.iterator();
        LargeNumbers ans = new LargeNumbers();

        //ArrayList<Integer> ans = new ArrayList();
        Integer sum = 0, carry = 0;
        //iterating through both the given lists simultaneously and adding each digit of each list  
        while (listX.hasNext() && listY.hasNext()) {
            sum = (Integer) listX.next() + (Integer) listY.next() + carry;
            ans.al.add(sum % 10);
            carry = sum / 10;
        }

        //if the list Y is smaller in size when compared to list X, then this will be executed
        while (listX.hasNext()) {
            sum = (Integer) listX.next() + carry;
            ans.al.add(sum % 10);
            carry = sum / 10;
        }

        //if the list X is smaller in size when compared to list Y, then this will be executed
        while (listY.hasNext()) {
            sum = (Integer) listY.next() + carry;
            ans.al.add(sum % 10);
            carry = sum / 10;
        }

        //any additional carry after adding all the digits is added to the Z list
        if (carry > 0) {
            ans.al.add(carry);
        }

        return ans;
    }

    public static LargeNumbers subtract(LargeNumbers num1, LargeNumbers num2) {
        //System.out.println("num1"+convertToString(num1)+"\tnum2\t"+convertToString(num2));
        boolean borrow = false, minus = false;
        LargeNumbers result = new LargeNumbers();
        int int1, int2;
        //System.out.println("num1.num.get(size "+num1.al.get(num1.al.size()-1));
        //System.out.println("num2.num.get(size "+num2.al.get(num2.al.size()-1));
        int last = num1.al.size() - 1;
        if(num1.al.get(last)==0){
           num1.al.remove(last);
        }

        //setting smaller number to num2 by default
        if (num1.al.size() < num2.al.size()) {

            LargeNumbers temp = num2;
            num2 = num1;
            num1 = temp;
            minus = true;
        }

        if (num1.al.size() == num2.al.size()) {

            if ((Integer.parseInt(num1.al.get(num1.al.size() - 1).toString())) < (Integer.parseInt(num2.al.get(num1.al.size() - 1).toString()))) {

                minus = true;
                LargeNumbers temp = num2;
                num2 = num1;
                num1 = temp;
                //break;
            } else if ((Integer.parseInt(num1.al.get(num1.al.size() - 1).toString())) == (Integer.parseInt(num2.al.get(num1.al.size() - 1).toString()))) {
                ++counter;
                return result;

            }

        }

        

        //We iterate throught the smaller number
        Iterator itr1 = num1.al.iterator();
        Iterator itr2 = num2.al.iterator();
        while (itr2.hasNext()) {
            int1 = Integer.parseInt(itr1.next().toString());
            int2 = Integer.parseInt(itr2.next().toString());

            //if we need to borrow from the current integer we subtract one
            if (borrow) {
                if (int1 == 0) {
                    int1 = 9;
                    borrow = true;
                } else {
                    int1 = int1 - 1;
                    borrow = false;
                }
            }

            //if subtrahend is lesser than the minuend then add 10 subtrahend
            if (int1 < int2) {
                int1 += 10;
                borrow = true;
            }

            //System.out.println("What have we got here BEFORE ADDING: "+int1+ " "+int2);
            result.al.add(int1 - int2);

        }

        //iterating through the larger list
        while (itr1.hasNext()) {
            int1 = Integer.parseInt(itr1.next().toString());

            if (borrow) {
                if (int1 == 0) {
                    int1 = 9;
                    borrow = true;
                } else {
                    int1 = int1 - 1;
                    borrow = false;
                }
            }
            result.al.add(int1);
        }

       
        return result;
    }

    public static LargeNumbers divide(LargeNumbers num1, LargeNumbers num2) {

        LargeNumbers div = new LargeNumbers();

        LargeNumbers sub = subtract(num1, num2);
        //System.out.println(""+convertToString(sub));
        //sub = subtract(sub, num2);

        // System.out.println(""+convertToString());
        while (!convertToString(sub).isEmpty()) {

            ++counter;
            sub = subtract(sub, num2);

        }
        System.out.println("div is" + counter);

        System.out.println("" + convertToString(num1));
        div = num1;
        return div;

    }

    

}
