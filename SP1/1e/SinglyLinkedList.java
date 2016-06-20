/** @author kunal krishna
 */
import java.util.*;
public class SinglyLinkedList<T> {
    public class Entry<T> {
        T element;
        Entry<T> next;

        Entry(T x, Entry<T> nxt) {
            element = x;
            next = nxt;
        }
    }

    Entry<T> header, tail;
    int size;

    SinglyLinkedList() {
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

    void multiunzip(int k) {
	if(size < 3) {  // Too few elements.No change.
	    return;
	}
	  Entry[] head= new Entry[k];
    Entry[] tail= new Entry[k];
    Entry<T> current;
	
  // Invariant: creating a array of pointer for head and tail node, number of pointer would be value of k
	// setting null value at the end of the  tail pointer(to create separate list), and use current pointer to point the remaing list after separting
	// now tail pointer of first llist will point to current one and tail pointer of 2nd list will point to current.next and similarly others pointer
	// At the end of all we will set null to each pointer
	// Lastly will combine all the list   
    head[0]=header.next;
    tail[0]=header.next;
     for(int i=1;i<k;i++)
     {
        head[i]=head[i-1].next;
        tail[i]=head[i];
        tail[i-1].next=null;
     }
      int j=0;
      current=head[k-1].next;
      head[k-1].next=null;

      while(current!=null){
         tail[j%k].next=current;
         tail[j%k] = tail[j%k].next;
         current=current.next;
         j=j+1;
      }

   for(int i=0;i<k-1;i++)
   {
     current = tail[i];
     current.next=head[i+1];
   }

}


    public static void main(String[] args) {
        int n = 1000002;
        if(args.length > 0) {
            n = Integer.parseInt(args[0]);
        }
        SinglyLinkedList<Integer> lst = new SinglyLinkedList<>();
        for(int i=1; i<=n; i++) {
            lst.add(new Integer(i));
        }
      lst.multiunzip(5);    
      lst.printList();
    }
}

