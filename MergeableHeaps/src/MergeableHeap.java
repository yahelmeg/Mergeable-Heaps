import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner; // Import the Scanner class to read text files
public class MergeableHeap {
	public static void main(String[] args) {
    	Scanner scan= new Scanner(System.in); 
    	System.out.println("Please choose which type of list would you like to use:");
    	System.out.println("1 - Lists are sorted , type 1 to pick this option."); 
    	System.out.println("2 - Lists are unsorted , type 2 to pick this option."); 
    	System.out.println("3 - Lists are unsorted, and dynamic sets to be merged are disjoint, type 3 to pick this option."); 
    	int optionFlag;
    	do {
    		optionFlag = scan.nextInt();	// stores the option the user picked.
    		String line = scan.nextLine();
    		if(optionFlag == 1) {
    			System.out.println("You picked sorted lists.");
    		} else if(optionFlag == 2) {
    			System.out.println("You picked unsorted lists.");
    		} else if(optionFlag == 3) {
    			System.out.println("You picked unsorted lists, and dynamic sets to be merged are disjoint.");
    		} else {
    			System.out.println("Invalid input , please enter a valid input 1,2 or 3.");
    		}
    	} while( optionFlag != 1 && optionFlag != 2 && optionFlag != 3);
    	int i = 0; // index
    	File file = null;
    	String[] lines = new String[100];  // contains the commands from the file
    	try {
    		file = new File("input.txt");    
    		Scanner readFile = new Scanner(file);
    		while (readFile.hasNextLine()) {      // get input from file into 
    	        lines[i] = readFile.nextLine();
    	        if(lines[i] == null ) {
    	        	break;
    	        }
    	        i++;
    		}
    		lines[i-1] = "Stop";
    		readFile.close();
    	} catch (FileNotFoundException e) {     // get input from console in case there's no file.
    		System.out.println("File wasn't found, this program allows manual input.");
    		System.out.println("Please choose a command:");
        	System.out.println("MakeHeap - make a new heap.");
        	System.out.println("Insert X - x  being the number you want to insert to the current heap.");
        	System.out.println("Minimum  - print the current heap's minimum to the screen."); 
        	System.out.println("ExtractMin - remove the minimum number from the current heap."); 
        	System.out.println("Union - combine the last two heaps."); 
        	System.out.println("Print - print the current heap.");
        	System.out.println("Stop - stop the current program.");
    	} 
    	int counter = 0;				// traverses through the lines array for new commands.
    	int min = 0;					// stores the minimal value in a heap.
    	int insert = 0;					// number to insert to the current heap.
    	int heapCount = 0;				// amount of heaps were created before using union.
    	String command = null;			// stores the command
    	linkedList.Node tail = null;	// points to the end of the unsorted heap.
    	linkedList.Node pos = null; 	// adjuster for tail node.
    	linkedList.Node prePos = null; 	// adjuster for tail node.
    	linkedList prev = null;			// points to the previous heap.
    	linkedList next = null;			// points to the current heap.
    	linkedList union = null;		// points to the union of the last 2 heaps.
    	while(true) {
    		if(!file.exists()) {
    			command = scan.nextLine();
    		} else {
    			command = lines[counter];
    		}	
    		if(command.startsWith("MakeHeap")) {				 // make heap command , counts if 2 heaps were created to check if it's possible to use union,
    			System.out.println("New heap created.");		 // when a second heap is created it makes prev point at the prev list and next at the new one.
    			if(heapCount < 2) {				
    				heapCount++;
    			}
    			if(next == null) {
    				next = linkedList.makeHeap(next);
    			} else {
    				tail = null;
    				prev = next;
    				next = next.nextList;
    				next = linkedList.makeHeap(next);
    			}
    			
    		} else if(command.startsWith("Insert")) {		// parses the number from the command and then uses the matching insert according to the 
    														// option the user picked(sorted/unsorted list) ,  in case of unsorted and disjoint list		
    			insert = Integer.parseInt(command.substring(7));	// it first checks if previous list exists and if it does it uses the method search
    			if(optionFlag == 1) {								// to check if the number exists in it , if it doesn't it uses unsortedInsert method
    				next = linkedList.sortedInsert(next,insert);	// to insert it.
    				System.out.println("Inserted " + insert + " to the current heap.");
    			} else if(optionFlag == 2) {
    				System.out.println("Inserted " + insert + " to the current heap.");
    				if(next.head == null) {
    					next = linkedList.unsortedInsert(next,insert,tail);
    					tail = next.head;
    				} else {
    					next = linkedList.unsortedInsert(next,insert,tail);
    					tail = tail.next;
    				}
    			} else if(optionFlag == 3) {	
    				if( prev == null ) {
    					System.out.println("Inserted " + insert + " to the current heap.");
    					if(next.head == null) {
    						next = linkedList.unsortedInsert(next,insert,tail);
    						tail = next.head;
    					} else {
    						next = linkedList.unsortedInsert(next,insert,tail);
    						tail = tail.next;
    					} 	
    				} else {
    					if(linkedList.search(prev,insert) == false) {
    						System.out.println("Inserted " + insert + " to the current heap.");
    						if(next.head == null) {
        						next = linkedList.unsortedInsert(next,insert,tail);
        						tail = next.head;
        					} else {
        						next = linkedList.unsortedInsert(next,insert,tail);
        						tail = tail.next;
        					} 
    					} else {
    						System.out.println("Couldn't insert " + insert + " because it was already found in the previous heap.");
    					}
    				}
    				
    			}
    		}
    		else if(command.startsWith("Minimum")) {	// returns the minimum using the matching minimum method according to the option the user picked.
    			
    			if(optionFlag == 1) {
    				min = linkedList.sortedMinimum(next);
    				System.out.println("The minimum number in the current list is: " + min);
    			} else if(optionFlag == 2) {
    				min = linkedList.unsortedMinimum(next);
    				System.out.println("The minimum number in the current list is: " + min);	
    			} else if(optionFlag == 3) {														
    				min = linkedList.unsortedMinimum(next);													
    				System.out.println("The minimum number in the current list is: " + min);			 
    			}
    		
    		} else if (command.startsWith("ExtractMin")) {	//extracts the minimum using the matching extractMin method according to the option 
    													// the user picked.
    			if(optionFlag == 1) {
    				next = linkedList.sortedExtractMin(next);
    				System.out.println("Extracted minimum");
    			} else if(optionFlag == 2) {
    				if(tail != null) {
    					min = linkedList.unsortedMinimum(next);
    					if( tail.data == min) {
    						if(next.head.next == tail) {
    							tail = null;
    						} else {
    							pos = next.head.next;
    							prePos = next.head;
    							while(pos.next != null) {
    								prePos = pos;
    								pos = pos.next;
    							}
    							tail = prePos;
    						}
    					}
    				}
    				next = linkedList.unsortedExtractMin(next);	
    				System.out.println("Extracted minimum");
    			} else if(optionFlag == 3) {												 
    				if(tail != null) {
    					min = linkedList.unsortedMinimum(next);
    					if( tail.data == min) {
    						if(next.head.next == tail) {
    							tail = null;
    						} else {
    							pos = next.head.next;
    							prePos = next.head;
    							while(pos.next != null) {
    								prePos = pos;
    								pos = pos.next;
    							}
    							tail = prePos;
    						}
    					}
    				}
    				next = linkedList.unsortedExtractMin(next);	
    				System.out.println("Extracted minimum");
    			}
    		
    		} else if (command.startsWith("Union")) {	// checks if the user made 2 heaps to combine , if he did it uses the matching Union method
    			if(heapCount == 2) {				// to combine the 2 last heaps, prints an error message if he didn't make 2 heaps.
    				System.out.println("Unioned the last 2 heaps");
    				heapCount = 0;
    				if(optionFlag == 1) {
    					union = linkedList.sortedUnion(next,prev);
    					prev = next;
    					next = union;
    				} else if(optionFlag == 2) {
    					union = linkedList.unsortedUnion(next,prev,tail);
    					prev = next;
    					next = union;	
    					tail = null;
    				} else if(optionFlag == 3) {												 
    					union = linkedList.unsortedUnion(next,prev,tail);
    					prev = next;
    					next = union;
    					tail = null;
    				} 
    			} else {
					System.out.println("Make 2 heaps before using Union");
				}
    		} else if (command.startsWith("Stop")) {	// stops the program.
    			
    			break;
    			
    		} else if (command.startsWith("Print")) {	// prints the heap.
    			
    			linkedList.printHeap(next);
    			
    		} else {	// invalid command.
    			
    			System.out.println("Invalid command , please enter a valid command.");
    		}
    		if(!file.exists()) {
    			System.out.println("Please enter next command.");
    		} else {
    			counter++;
    		}
    	}
    	scan.close();
    }
}
