import java.util.*;

public class ReverseLinkedList<T>{
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

    ReverseLinkedList() {
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

    void printList() {
        Entry<T> x = header.next;
        while(x != null) {
            System.out.print(x.element + " ");
            x = x.next;
        }
        System.out.println();
    }


   /* Two invariant 
  
  */
    void ReverseIterative()
    {
       Entry<T> head1= header.next;
       Entry<T> prev=null;
       Entry<T> temp;
       while(head1!=null)
       {
          temp=head1.next;
          head1.next=prev;
          prev=head1;
          head1=temp;
       }
       header.next=prev;
    }
    
    /* Two invariant 
  
    */

    Entry<T> Rprev=null;
    void ReverseRecursive()
    {
     
      if(header.next.next==null)
         { 
             Entry<T> temp= header.next;
             header.next.next=Rprev;
             Rprev=temp;
             return;
         }
        Entry<T> next1 = header.next.next;
        header.next.next = Rprev;
        Rprev=header.next;
        header.next=next1;
        ReverseRecursive();
        header.next=Rprev;
        
    }

     public static void main(String[] args) {
        int n = 10;
        if(args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        ReverseLinkedList<Integer> lst = new ReverseLinkedList<>();
        for(int i=1; i<=n; i++) {
            lst.add(new Integer(i));
        }

        lst.ReverseIterative();       /*(E) part a */
        lst.printList();              /*(E) part a */
      
        lst.ReverseRecursive();      /*(E) part a as in previous step we already reverse the list now will get in orginal order*/
        lst.printList();             /*(E) part a */
        
    }


}