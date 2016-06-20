import java.util.Iterator;
import java.util.*;

public class IntersectionCheck {

    public static <T extends Comparable<T>>
            void intersect(List<T> l1, List<T> l2, List<T> outList) {

        Iterator it1 = l1.iterator();
        Iterator it2 = l2.iterator();

        if (it1.hasNext() && it2.hasNext()) {

            T element1 = (T) it1.next();
            T element2 = (T) it2.next();

        
            while (true) {
         
                if (element1.compareTo(element2)==0) {

             
                    outList.add((T) element1);

                    if (it1.hasNext()) {
                        element1 = (T) it1.next();
                    } else {
                        break;
                    }

                    if (it2.hasNext())
                    {
                        element2 = (T) it2.next();
                    }
                    else
                    {
                        break;
                    }
              
                }

                if (element1.compareTo(element2) > 0) {
                    
               //   System.out.println("Here ele1>ele2");
                    if (it2.hasNext()) {
                        element2 = (T) it2.next();
                    } else {
                        break;
                    }
                }
                
                else if(element1.compareTo(element2) < 0)
                {  
                    if (it1.hasNext()) {
                //  System.out.println("Here ele1<ele2");
                    element1 = (T) it1.next();
                } else {
                    break;
                }

               }
            }
        }

    }

    public static void main(String[] args) {

        List l1 = new ArrayList();

        for(int i=0; i<1000000;i++)
        l1.add(i);
        

        List l2 = new ArrayList();

         for(int j=0; j<1000000;j++)
             l2.add(j);
        

        List l3 = new ArrayList();

                long startTime = 0;
		long endTime = 0;
		long totalTime = 0;
                
     	startTime = System.currentTimeMillis();
        intersect(l1, l2, l3);
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("Time="+totalTime);
        System.out.println("size of l3=" + l3.size());
        
    }

}
