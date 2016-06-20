/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lp1;

import java.util.Random;

/**
 *
 * @author kunal krishna
 */
public class QuickSort {
    
   private static long startTime, endTime, elapsedTime;
   private static int phase = 0;
   
   public static<T extends Comparable<T>> void swap (T A[], int x, int y){
       T temp = A[x];
      A[x] = A[y];
      A[y] = temp;
   }
   
    public static <T extends Comparable<T>> void sort(T[] a) 
    {
	     simplequick(a, 0, a.length-1);
    }
    
    public static <T extends Comparable<T>> void sort1(T[] a) 
    {
	     multipivotquick(a, 0, a.length-1);
    }
    
    public static<T extends Comparable<T>> void multipivotquick(T[]a, int start, int last){
           if(start<last){
        int res[]= partion2(a,start,last);
        multipivotquick(a,start,res[0]);
        multipivotquick(a,res[0]+1, res[1]);
        multipivotquick(a,res[1]+1,last);
        }
    
    }
    
    public static<T extends Comparable<T>> int[] partion2(T a[], int p, int r){
      int[] res= new int[2];
      Random c= new Random();
      int l1=1+p; 
      int l2=r-1;     
      T x1=a[l1];
      T x2=a[l2];
    
      if(x1.compareTo(x2)>=0){
         swap(a,l1,r);
         swap(a,l2,p);         
      }
      else{
         swap(a,l2,r);
         swap(a,l1,p);
           
      }      
      int i=p+1;
      int j=r-1;
      int l=p+1;
      int cond=r-p-1;
          while(cond>0){
             while(a[i].compareTo(x2)<=0 && cond>0){
               if(a[i].compareTo(x1)>=0){             //case1
                   i++;
                   cond--;
               }
               else{                           //case2
                swap(a,l,i);
                 i++;
                 cond--;
                 l++;
               }
             }
          if(cond<=0)
              break;
          while(a[j].compareTo(x2)>0&& cond>0){            //case3
              j--;
              cond--;
          }
          if(cond<=0)
              break;
          
          if(a[j].compareTo(x1)<0){                                 //case4
               if(l!=i){
               T temp=a[l];
               a[l]=a[j];
               a[j]=a[i];
               a[i]=temp;
               }
               else{
                 swap(a,i,j);
               } 
               l++;
          }
          else{
            swap(a,i,j);
          } 
         i++;
         j--;
         cond-=2;
          }
         swap(a,p,l-1);
         swap(a,j+1,r);
         res[0]=l-1;
         res[1]=j+1;
          
    return res;
    }
    public static<T extends Comparable<T>> void simplequick(T a[],int start, int last){
        if(start<last){
        int q= partion1(a,start,last);
        simplequick(a,start,q-1);
        simplequick(a,q+1,last);
        }  
    }
    public static<T extends Comparable<T>> int partion1(T a[], int p, int r){
       int var1;
       Random c= new Random();
       int i= c.nextInt(r-p)+p;
       swap(a,i,r);
       T x;
       x=a[r];
       var1=p-1;
       for(int j=p;j<r-1;j++){
           if(a[j].compareTo(x)>=0){
              var1=var1+1;
              swap(a,var1,j);
           }               
       }
        swap(a,var1+1,r);  
       return var1+1;       
    }
    
    
    /* Timer function*/
      public static void timer()
    {
        if(phase == 0) {
	    startTime = System.currentTimeMillis();
	    phase = 1;
	}    else {
	      endTime = System.currentTimeMillis();
            elapsedTime = endTime-startTime;
            System.out.println("Time: " + elapsedTime + " msec.");
            phase = 0;
        }
    }
    
    public static void main(String args[]){   
     	int size=1000002;
        Integer[] a = new Integer[size];
        Random rand = new Random();
	   for(int i=0; i<size; i++) {
	      a[i] = rand.nextInt(10*size);
	}
      System.out.println("Dual pivot quick sort for million record");  
      timer();
     sort1(a);
     timer();
     
     int size1=1000002;
        Integer[] b = new Integer[size1];
	   for(int i=0; i<size1; i++) {
	      b[i] = rand.nextInt(10*size1);
	}
      System.out.println("Simple quick sort for million record");
      timer();
      sort(b);
      timer();
      
      
      int size2=1002;
        Integer[] c = new Integer[size2];
	   for(int i=0; i<size2; i++) {
	      c[i] = 5;
	}
      System.out.println("Dual pivot quick sort for similar item");
      timer();
      sort1(c);
      timer();
      
      
      int size3=1002;
        Integer[] d = new Integer[size3];
	   for(int i=0; i<size3; i++) {
	      c[i] = 5;
	}
      System.out.println("Simple quick sort for  similar item");
      timer();
      sort(c);
      timer();
      
     
     
    }
    
    
    
    
    
    
    
    
    
}
