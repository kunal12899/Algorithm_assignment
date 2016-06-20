import java.util.Scanner;



/**
 *
 * @author kunal
 */
public class SP5 {   
    
    static int size=5;
    static int count=0;
    public static void visit(boolean[] A){      
         
        for(int i=0;i<A.length;i++){
             if(A[i]){          
             System.out.print(i +" ");
             }
         }
    }
    
    public static void Combination(boolean[] A, int n, int k){
       if(k==0){
           System.out.print("(");
           visit(A);
           System.out.print(")");
          System.out.println(" ");
       
       }
       else if(k>n){
          return;       
       }
       else{               
            Combination(A,n-1,k);           
             A[n]=true;          
            Combination(A,n-1,k-1);
             A[n]=false;                   
       }
    }
    
    public static void visitP(int[] A){
    
         for(int i=0; i<A.length;i++){
            
                System.out.print(A[i]);
            
            }
            System.out.println(" ");  
       count++;
    }
    public static void Permute(int[] A, int n){
        if(n==0){
            visitP(A);         
        }
        else{
                for(int j=0;j<=n;j++){
                   swap(A,j,n);
                   Permute(A,n-1);
                   swap(A,j,n);  
            }
        }
    
    }
    
    public static void Permute_heap(int[] A, int n){
        if(n==0){
          visitP(A);
        }
        else{
        
        for (int i = 0; i <= n; i++)
            {
                Permute_heap(A, n - 1);
                if (n % 2 == 1)
                {
                    swap(A, 0, n - 1);
                }
                else
                {
                    swap(A, i, n - 1);
                }
            }      
        
        }

  }
    
    public static void swap(int[]A, int a, int b){
        int c= A[a];
        A[a]=A[b];
        A[b]=c;   
    }
    
    
    public static void Kunts(int[] A){
      
       int j=Integer.MIN_VALUE;
       int l=Integer.MIN_VALUE;
         
        //find max value of j such that a_j < a_j+1
           
            if(j==0){
            //stop
            }
        // find max value of l such that a_j < a_l
        
        swap(A,j,l);
      //  Reverse(A,j+1,A.length-1);
        visitP(A);
               
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int comb=2;
        
        System.out.println("Enter your size of the array elements");
         Scanner sc=new Scanner(System.in);
          size=sc.nextInt();
        System.out.println("Enter your number for combination array elements");
          comb=sc.nextInt();
          if(comb==size){
           System.out.println("You will get zero combination");
          }
         
          // Creating a array to get the required combination
          boolean[] a= new boolean[size];
          for(int i=0; i<size;i++){
            a[i]=false;
          }
           // method for combination
          Combination(a,size-1,comb);
         
          
          
          
          // Elements for perumtation;
          int[] A= new int[size];
          for(int j=0;j<size;j++){         
            A[j]=j+1;
          
          }
       
          
            //Permute function using take2
        System.out.println("Permutation of numbers Using Take2::");
        long startTime = System.currentTimeMillis();  
        Permute(A,A.length-1);
        long end = System.currentTimeMillis();
        long diff = end-startTime;
        System.out.println("Time taken : "+diff+" ms");
        System.out.println("Total number of permutation "+count);
         count=0;
         
         //Permute function using take3/ Heap Algorithm
        System.out.println("Permutation of numbers Using Take3/Heap Alogrithm::"); 
        long startTime1 = System.currentTimeMillis(); 
         Permute_heap(A,A.length-1);
         long end1 = System.currentTimeMillis();
         long diff1 = end1-startTime1;
         System.out.println("Time taken : "+diff1+" ms");
         System.out.println("Total number of permutation "+count);
         
         
         // Call method kunth:
           System.out.println("Permutation of numbers Using knuth's Alogrithm::");
         
    }
    
    
    
}
