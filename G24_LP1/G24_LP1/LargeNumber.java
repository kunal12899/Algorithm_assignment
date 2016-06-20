/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kunal krishna
 */
public class LargeNumber {
    List num;
    
    public LargeNumber(String strInput){
        num= new ArrayList<Integer>();
        char[] charInput= strInput.toCharArray();
        
        for(int i=0;i<charInput.length;i++)
            num.add(charInput[charInput.length-1-i]);
    }
    
    public LargeNumber(Long lngInput){
        num= new ArrayList<Integer>();
        
        while(lngInput>0){
            num.add(lngInput%10);
            lngInput=lngInput/10;
        }
    }
    
    public LargeNumber (){
        num= new ArrayList<Integer>();
    }
}
