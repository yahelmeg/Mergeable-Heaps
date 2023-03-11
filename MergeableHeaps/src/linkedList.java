import java.util.*;
public class linkedList {			// linked list class ,contains: node head ( which contains int data and next node in the list) and next linkedList. 
    Node head; 
    linkedList nextList;
    static class Node {
    	int data;
        Node next;
        Node(int d) {
        	data = d;
            next = null;
        }
    }
    
    public static linkedList makeHeap(linkedList list) {	// makes a new heap.
    	list = new linkedList();
    	list.head = null;
    	list.nextList = null;
    	return list;
    }
    public static linkedList sortedInsert(linkedList list,int data) {	// insert a new number to the sorted list, goes through the list
    	Node temp;														// to find the right position to insert the number.		
    	Node new_node = new Node(data);
    	new_node.next = null;
    	if (list.head == null) {
    		list.head = new_node;
    	} else {
    		if( list.head.data >= new_node.data) {
    			temp = list.head;
    			list.head = new_node;
    			new_node.next = temp;
    		} else {
    			Node pos = list.head;
    			while (pos.next != null && pos.next.data <= new_node.data) {
    				pos = pos.next;
    			}
    			temp = pos.next;	
    			pos.next = new_node;
    			new_node.next = temp;
    		}
    	}	
    	return list;
    }
    
    public static int sortedMinimum(linkedList list) {		// returns the minimum number in the sorted list( the first number in the list).
    	return list.head.data;
    }
    
    public static linkedList sortedExtractMin(linkedList list) {	// extracts the minimum from the sorted list ( the first number in the list).
    	Node temp;
    	temp = list.head.next;
    	list.head = temp;
    	return list;
    }
    
    public static linkedList sortedUnion(linkedList list1,linkedList list2) {	// combines two sorted lists , compares the 2 numbers in each list
    	Node head1 = list1.head;												// and adds the lowest number of them to the combined list , if
    	Node head2 = list2.head;												// one of the lists ends it adds the whole other list at the end of
    	Node temp = new Node(0);												// the combined list.
    	Node tail = temp;
    	while(true) {
    		if(head1 == null) {
    			tail.next = head2;
    	        break;
    	    }
    	    if(head2 == null) {
    	    	tail.next = head1;
    	        break;
    	    }
    	    if(head1.data <= head2.data) {
    	    	tail.next = head1;
    	        head1 = head1.next;
    	    } else {
    	    	tail.next = head2;
    	        head2 = head2.next;
    	    }
    	    tail = tail.next;
    	}
    	linkedList union = new linkedList(); 
    	union.head = temp.next;
    	return union;
    }
    
    public static linkedList unsortedInsert(linkedList list,int data,Node tail) { // insert new number at the end of the unsorted list,
    	Node newNode = new Node(data);											  // also being used for disjoint lists if the number 
    	newNode.next = null;													// wasn't found in the previous list.
    	if (list.head == null) {
    		list.head = newNode;
    	} else {
    		tail.next = newNode;
    	}
    	return list;
    }
    
    public static int unsortedMinimum(linkedList list) {	//returns the minimum in the unsorted list , goes through the whole list and saves
    	int min = list.head.data;							// the minimal value found in min variable(also being used for disjoint unsorted lists).
    	Node temp = list.head.next;
    	while(temp != null) {
    		if(temp.data < min) {
    			min = temp.data;
    		}
    		temp = temp.next;
    	}
    	return min;
    }
    
    public static linkedList unsortedExtractMin(linkedList list) {	// extracts the minimum in the unsorted list, uses unsortedMinimum to find
    	int min = unsortedMinimum(list);										// what's the minimum and searches the list for it, then extracts it,
    	if(list.head.data == min) {												// ( also being used for unsorted disjoint list).
    		list.head = list.head.next;
    		return list;
    	}
    	Node pos = list.head.next;
    	Node prePos = list.head;
    	while(pos.data != min ) {
    		prePos = pos;
    		pos = pos.next;
    	}
    	if(pos.next == null) {
    		prePos.next = null;
    	} else {
    		prePos.next = pos.next;
    	}
    	return list;
    }
    
    public static linkedList unsortedUnion(linkedList list1, linkedList list2 , Node tail) {   // uses tail to connect the two lists
    	linkedList union = new linkedList();										          // if one of the heads of the lists is null
    	if(list1.head == null && list2.head == null) {								          //  it returns the other lists , if both of the heads
    		union.head = null;														          // are null nothing happens , otheriwse it connects
    		return union;															          // the lists using tail or using the current list head
    	}																					  //  if there's no tail.
    	if(list1.head == null) {
    		union.head = list2.head;
    		return union;
    	}
    	if(list2.head == null) {
    		union.head = list1.head;
    		return union;
    	}
    	if(tail == null) {
    		union.head = list1.head;
    		union.head.next = list2.head;
    		return union;
    	}
    	tail.next = list2.head;
    	union.head = list1.head;
    	return union;
    }
    
    public static boolean search(linkedList list, int data) {	// searches the list for a specific number, return true if it found it false otherwise. 
    	Node temp = list.head;
    	while(temp != null) {
    		if(temp.data == data) {
    			return true;
    		}
    		temp = temp.next;
    	}
    	return false;
    }
    
    public static void printHeap(linkedList list)	// prints the heap.
    {
    	Node pos = list.head;
    	System.out.print("Heap: ");
    	while (pos != null) {
    		System.out.print(pos.data + " ");
    		pos = pos.next;
    	}
    	System.out.println();
    }
}   
 