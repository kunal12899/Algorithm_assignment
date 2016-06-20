/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kunal
 */

public class KnuthLAlg {
    static Integer[] a=null;
    
    static String[] s=null;
    
    public static void main(String[] args){
        
    	KnuthLAlg d= new KnuthLAlg();
    	
    	
         a= new Integer[]{1,2,2,3,3,4,5};
        String[] s= new String[]{"abc","def","abcd","xyz"};
       
         d.KnuthL(a);
         d.KnuthL(s);
        
        //j=right most index such that a[j]<a[j+1]
        //l=max index such that a[j]<a[l]
        
        //find max value of j such that a[j]<a[j+1]
         
    }
         
         public <T extends Comparable<T>> void KnuthL(T[] a){
         
         
        int j=-Integer.MAX_VALUE;
        while(j!=0){
            for(int i=1;i<a.length;i++){
            if(a[i-1].compareTo(a[i])<0){
                j=i-1;
                
            }
        }
        
        int l=0;
        for(int i=j+1;i<a.length;i++){
            if(a[j].compareTo(a[i])<0){
                l=i;
                
        }
        }
        
        T temp=null;
        temp=a[j];
        a[j]=a[l];
        a[l]=temp;
        
        
            revArray(a,j+1,a.length-1);
        
        for(int i=0;i<a.length;i++)
            System.out.print(a[i]+"\t");
        System.out.println("");
        
        }
        

    }
    
         static <T extends Comparable<T>> void revArray(T a[], int start, int end)
         {
             T temp;
             if (start >= end)
                 return;
             temp = a[start];
             a[start] = a[end];
             a[end] = temp;
             revArray(a, start+1, end-1);
         }
    
    
}
