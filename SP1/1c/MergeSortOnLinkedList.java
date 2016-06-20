import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author kunal krishna
 */
//---------problem statement------------------------
/*Write the Merge sort algorithm that works on linked lists.  This
   will be a member function of a linked list class, so that it can
   work with the internal details of the class.  The function should
   use only O(log n) extra space (mainly for recursion), and not make
   copies of elements unnecessarily.
*/

public class MergeSortOnLinkedList {
    
    public static void main(String [] args){
        
        LinkedList<Integer> sl = new LinkedList<>();
        
       
        for(int i=10;i>0;i--){
           sl.add(i);
        }

        System.out.println("");
        long starttime = System.currentTimeMillis();      
        mergeSort(sl,0,sl.size()-1);
        long endtime = System.currentTimeMillis();
        long diff = endtime-starttime;
        System.out.println("exec\t"+diff+" ms");
     
    }
    
    
    public static void mergeSort(LinkedList<Integer> sl,int i,int j){
      
        if(j-i<1){
            return ;
        }
        
        MergeSortOnLinkedList msll = new MergeSortOnLinkedList();
        int mid = (i+j)/2;
        
        mergeSort(sl,i,mid);
        mergeSort(sl,mid+1,j);
        
        msll.merge(sl,i,mid,j);
        
    }
    
    public void merge(LinkedList<Integer> sl,int p,int mid,int q){
        
        LinkedList<Integer> temp = new LinkedList<>();
        
        
        int i = p;
	int j = mid+1;
	int k = 0;
        
        
	while (i <= mid && j <= q) {
	    if (sl.get(i).compareTo(sl.get(j))<=0){
                //temp.set(k,sl.get(i));
                temp.add(sl.get(i));
                ++i;
            }
		
                
            else{
               
                temp.add(sl.get(j));
               ++j;
               ++k; 
            }
		
	}
	if (i <= mid && j > q) {
	    while (i <= mid){
                
                temp.add(sl.get(i));
                ++k;++i;
            } 
		
	} else {
	    while (j <= q){
                
                temp.add(sl.get(j));
                ++k;++j;
            }
		
	}
        Iterator<Integer> itTemp = temp.iterator();
        k=0;
        while(itTemp.hasNext()){
            sl.set(k+p,temp.get(k)); 
            k++;
            itTemp.next();
        }
        
    }  
    
}
