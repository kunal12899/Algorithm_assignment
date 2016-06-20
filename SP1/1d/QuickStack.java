import java.util.Stack;

public class QuickStack {
    
    static int partition(int a[], int low, int high)
        {
            int p = a[low];
            
            int l = low+1;
            int r = high;
            
            
            while(true)
            {
                while(l<=high && p>a[l])
                {
                    l++;
                }
                
                while(p<a[r])
                {
                    r--;
                }
                
                if(l<r)
                {
                 int t   = a[l];
                    a[l] = a[r];
                    a[r] = t;
                }
                
                else
                {
                    a[low] = a[r];
                    a[r]  = p;
                    break;
                }
            }
            
            return r;
        }
    
    static void quickSort(int a[], int low, int high)
        {
            Stack ls = new Stack();
            Stack hs = new Stack();
            
            
            ls.push(low);
            hs.push(high);
            
            
            while(!ls.empty())
            {
                low = (Integer) ls.pop();
                high = (Integer) hs.pop();
                
                int p = partition(a,low,high);
                
                if((p+1) <high)
                {
                    ls.push(p+1);
                    hs.push(high);
                }
                
                if(low<(p-1))
                {
                    ls.push(low);
                    hs.push(p-1);
                }
            }
        }
    
    public static void main(String args[])
    {
       
        int a[] = {7,4,5,1,3,8,9,2,6};
        
        int low = a[0];
        int high = a[a.length-1];
        
        quickSort(a,low,high);
   
       
    }
}

