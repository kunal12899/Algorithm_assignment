
  import java.util.ArrayList;
  import java.util.Iterator;
  import java.util.List;

  public class UnionCheck {
     
   public static <T extends Comparable<T>>
              void union(List<T> l1, List<T> l2, List<T> outList) {

          Iterator it1 = l1.iterator();
          Iterator it2 = l2.iterator();
          
          Object bigNum = Integer.MAX_VALUE;
          
              T element1 = (T) it1.next();
              T element2 = (T) it2.next();

   
              while (it1.hasNext() || it2.hasNext()) {
               
           
              
                  if (element1.compareTo(element2)==0) {
                       while(element1.compareTo(element2)==0){
                
                      outList.add((T) element1);
                      
                   
                      if (it1.hasNext()) {
                          element1 = (T) it1.next();
                          
                      }
                      else
                     {
                         element1  =  (T) bigNum;
                     }
                      
              
                      if (it2.hasNext())
                      {
                          element2 = (T) it2.next();
                         
                      }
                      
                      else
                     {
                         element2  =  (T) bigNum;
                     }           
            
                   }
                  }

                  else if (element1.compareTo(element2) > 0) {
                      
                      while(element1.compareTo(element2) > 0){
                  
                   outList.add((T) element2);
                  
                      if (it2.hasNext()) 
                      {
                     
                          element2 = (T) it2.next();
                         
                      } 
                      
                      else
                     {
                         element2 =  (T) bigNum;
                     }
                       
                  }
                  }
                  
                  
                  else if(element1.compareTo(element2) < 0)
                  {  
                      while(element1.compareTo(element2) < 0)
                      {
                   //   System.out.println("Here ele1<ele2");
                       outList.add((T) element1);
                    //   System.out.println("Outlist element1 = "+element1);
                     if (it1.hasNext()) 
                      {
                    
                      element1 = (T) it1.next();
                      
                      }
                     
                     else
                     {
                         element1  =  (T) bigNum;
                     }
                     
                      }  

              }
            }
          }

      public static void main(String[] args) {

          List l1 = new ArrayList();
    
          List l2 = new ArrayList();

          List l3 = new ArrayList();
          
          for(int i=0;i<1000000;i++)
          l1.add(i);
          for(int i=0;i<100;i=i+2)
          l2.add(i);
          
          
          

          UnionCheck sp = new UnionCheck();
       //   System.out.println("Hello");
          long startTime = 0;
  		    long endTime = 0;
  		    long totalTime = 0;
                  
       	  startTime = System.currentTimeMillis();
          union(l1, l2, l3);
          endTime = System.currentTimeMillis();
          totalTime = endTime - startTime;
          System.out.println("Time="+totalTime);
          System.out.println("Size of l3=" + l3.size());

        
      }

  }
      
      

