import java.util.*;
public class FindFibonnaci {

	
	public static long linearFibonacci(long n, long p){
		long f[] = new long[100000000];
 		
		 long res = 0;
		 f[0] = 0 ;
		 f[1] = 1;
		 
		 for(int i=2;i<=n;i++){
			 f[i] = (f[i-1]+f[i-2]);
			// res = f[i]%p;
		 }
		 
		 return f[(int) n];
	
	}
	
	
	public static long logFibonacci(long n, long p){
		
		if(n<0){
		throw new IllegalArgumentException("The fibonacci value cannot be negative");
		}
		
		if(n==0){
			return 0;
		}
		
		long A[][]= {{1,1},{1,0}};
		long[][] ans;
		ans= power(A,n,p);
		
		//ans = multiply(ans,A,p);
		
		return ans[0][0];
		
	}
	
	static long[][] multiply(long X[][], long Y[][], long p){
		
		long x = X[0][0] * Y[0][0] + X[0][1] * Y[1][0];
		long y = X[0][0] * Y[0][1] + X[0][1] * Y[1][1];
		long z = X[1][0] * Y[0][0] + X[1][1] * Y[1][0];
		long w = X[1][0] * Y[0][1] + X[1][1] * Y[1][1];
		
        X[0][0] = x;
        X[0][1] = y;
        X[1][0] = z;
        X[1][1] = w;
        
        System.out.println();
        return X;
	}
	
	static  long[][] power(long X[][],long n,long p){
		long I[][] = {{1,0},{0,1}};
		long[][] A;
		long R[][] = new long[][]{{1,1},{1,0}};
		
		
		if(n==0){
			 
			return I;
		}
		
		
		else if(n==1){
	
			return X;
		}
		else{
		
		 int i;
		
		  A=new long[][]{{1,1},{1,0}};
		 
		R =  power(multiply(X,X,p),n/2,p);
		
		
		 //System.out.println(R[0][0]);
		if (n%2 == 0){
			return R;
				
		}
		else{
				return (multiply(R,X,p));
		}
		  
		
		
		
//		for(i=3;i<=n;i++){
//			X = multiply(X,A,p);
		
		
//		}
		
		
			}
		
		
		
		//return X;
	}
	
	public static void main(String args[]){
		
		long a;
		long n = 7;
		long p = 999953;
		
		long startTime = 0;
		long endTime = 0;
		long totalTime = 0;
		
		startTime = System.currentTimeMillis();
		a = linearFibonacci(n,p);
		endTime = System.currentTimeMillis();
		
		totalTime = endTime - startTime;

		System.out.println("Time for n complexity="+totalTime);
		
		//System.out.println("Fibos");
		
		//System.out.println("The Fibonacci Number with n complexity of "+n+"th element="+a);
		
		System.out.println("**********************************************");
		
		 startTime = 0;
		 endTime = 0;
		 totalTime = 0;
		 
		 startTime = System.currentTimeMillis();
		a = logFibonacci(n-1,p);
		endTime = System.currentTimeMillis();
		
		totalTime = endTime - startTime;

		System.out.println("Time for logn complexity="+totalTime);
		
		//System.out.println("The Fibonacci Number with logn complexity of "+n+"th element="+a);
	}
}
