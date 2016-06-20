import java.util.*;

public class PrintReverseLinkedList<T>{
	public class Entry<T>{
		T element;
        Entry<T> next;

        Entry(T x, Entry<T> nxt) {
            element = x;
            next = nxt;
        }
	}

    Entry<T> header, tail;
    int size;

    PrintReverseLinkedList() {
        header = new Entry<>(null, null);
        tail = null;
        size = 0;
    }

    void add(T x) {
        if(tail == null) {
            header.next = new Entry<>(x, header.next);
            tail = header.next;
        } else {
            tail.next = new Entry<>(x, null);
            tail = tail.next;
        }
	size++;
    }

     /*  
         Recursive method to print the linkedList
     */
    void printListRecursive() { 
       Entry<T> printHead= header.next;
          reversePrintRecursive(printHead);
          System.out.println();
        }

    void reversePrintRecursive(Entry<T> printHead)    
    {
           if (printHead.next==null) 
           {
             System.out.print(printHead.element + " "); 
             return;     
           }
           else
           {
           T e=printHead.element; 
           printHead=printHead.next;     
           reversePrintRecursive(printHead);
           System.out.print(e + " ");
           }    
    }

    /* 
       Used stack to print the list in reverse order
    */

     void printListNonRecursive() {        
      Entry<T> head1= header.next;
      Stack<T> s = new Stack<T>();
           
           while(head1!=null)
           {
             s.push(head1.element);
             head1=head1.next;
           }
           while(!s.isEmpty())
           {
            System.out.print(s.pop()+ " ");
           }
           System.out.println();
      }
 
     public static void main(String[] args) {
        int n = 1000;
        if(args.length > 0) {
            n = Integer.parseInt(args[0]);
        }
        PrintReverseLinkedList<Integer> lst = new PrintReverseLinkedList<>();
        for(int i=1; i<=n; i++) {
            lst.add(new Integer(i));
        }
          lst.printListNonRecursive();  /*(E) part b */ 
          lst.printListRecursive();     /*(E) part b */ 
    }


}