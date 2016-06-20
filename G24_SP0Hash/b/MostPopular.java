import java.util.*;
import java.util.Random;
import java.lang.*;


/*
  @kunal krishna 


*/

public class MostPopular {

	private static long startTime, endTime, elapsedTime;
   private static int phase = 0;

 public	int freq(int a[])
 {
	 Arrays.sort(a);
	 
	 
	 int frequent = a[0];
	 int max = 1;
	 int count = 1;
	 int x = a[0];
	 
	 for(int i=1; i<a.length; i++)
	 {
		 if(a[i]==x)
			 count++;
		 
		 else 
		 {
			 if(max<count)
		 {
			 max = count;
			frequent = a[i-1];
			 
		 }
		 
		 	count = 1;
		 	x = a[i];
		 }
	 }
	 
	 
	 if(count>max)
		 return a[a.length-1];
	 
	 else
	 {
		 return frequent;
	 
	 }
 }

 /*

   @using HashMap to find the most repeated element.
       Using Integer as a key and value.
       If element comes for the first time then it value will be zero else will insert with its count
 */

 public int compfreq(int[] a){
       HashMap<Integer,Integer> h =new HashMap<Integer,Integer>();
         for(int i=0;i<a.length;i++){
            Integer count=h.get(a[i]);
            if(count!=null){

            	h.put(a[i],count+1);
            }
            else{

            	h.put(a[i],0);
            }

         }

         int max=0;

         for(Integer i:h.keySet()){
            if(h.get(i)>max){
               max=i;
            }
         }
return max;

 }


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

	
 	public static void main(String args[])
 	{
 		MostPopular p = new MostPopular();
        int size=1000002;
        int[] a = new int[size];
        Random rand = new Random();
	   for(int i=0; i<size; i++) {
	      a[i] = rand.nextInt(10*size);
      }
        System.out.println("***************************************");
        timer();
 		int ans = p.freq(a);
 		timer();
 		System.out.println("Ans using Array.sort="+ ans);
 		System.out.println("***************************************");
 		timer();
 		int ans1=p.compfreq(a);
 		timer();
 		System.out.println("Answer Using HashMap  "+ ans1);
 		System.out.println("***************************************");
 		timer();

 	}
 
}


