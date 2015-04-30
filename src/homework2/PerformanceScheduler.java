/**
 * Samier Trabilsy
 * Student ID: 109839226
 * Homework #2
 * Thursday: R04
 * Gustavo Poscidonio
 * Mahsa Torkaman
 * @author Samier
 */
package homework2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PerformanceScheduler {
	/**
	 * The main method presents a menu that allows the user to access data from the API, store it in a linked list, 
	 * and interact with it using the following commands:
	 * <br><br>
	 	A (Add a new performance to the list) <br>
			Prompts the user for information regarding a performance. Create a new PerformanceNode for this performance, 
			and add it to the end of the list. <br><br>
		I (Insert a new performance after the current node) <br>
			Prompts the user for information regarding a performance. Create a new PerformanceNode and add it after the current performance.
			If there is no current node, insert the new node at the end of the list. <br><br>
		R (Remove the current node from the list) <br>
			If there is a current node, it is removed from the list and the current node is moved to the next appropriate current node. 
			If there is no current node, inform the user of the situation with an appropriate error message. <br><br>
		C (Display the current node) <br>
			Displays the contents of the current node. 
			If there is no current node, display an appropriate error message to the user.  <br><br>
		D (Display all nodes)<br>
			Displays the contents of all nodes, in order.
			If there are no nodes, display an appropriate error message to the user.  <br><br>
		F (Move cursor forward)<br>
			Moves the reference of the current node forward by one position if there is another node in the list. 
			If the current slide is the last in the list, simply display an appropriate message to the user and do nothing.<br><br>
		B (Move cursor backwards)<br>
			Moves the reference of the current node backwards by one position if there is a previous node in the list. 
			If the current node is the first in the list, simply display an appropriate message to the user and do nothing.<br><br>
		J (Jump to a given position)<br>
			Prompts the user for a position number and moves the current node to that number node, if it exists. 
			If it does not exist, simply display an error message to the user and do nothing.<br><br>
		Q (Exit the program.)<br>
		
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		PerformanceList list = new PerformanceList();
		String menu = "\nMain menu:\n\nA) Add to end\nB) Move current node backward\n"
				+ "C) Display current node\nD) Display all nodes\nF) Move current node forward\n"
				+ "I) Insert after current node\nJ) Jump to position\nR) Remove current node\n"
				+ "Q) Exit\n\nChoose an operation: ";
		Scanner input;
		String choice;
		char letter;
		do {
			System.out.print(menu);
			input = new Scanner(System.in);
		    choice = input.next();
		    letter = choice.charAt(0);
		    String name, lead; 
		    int participants, duration, position; 
		    switch(Character.toUpperCase(letter)) {
		    
			    case('A'): //add a new performance to the end of the list
			    	
			    	try {
			    		System.out.print("\nEnter name of performance: ");
			    		input.nextLine();
				    	name = input.nextLine();
				    	System.out.print("Enter name of lead performer: ");
				    	//input.nextLine();
				    	lead = input.nextLine();
				    	System.out.print("Enter the total participants: ");
				    	participants = input.nextInt();
				    	System.out.print("Enter the duration of the performance: ");
				    	duration = input.nextInt();
			    	}catch(InputMismatchException e) {
			    		e.printStackTrace();
			    		break;
			    	}
			    	PerformanceNode a = new PerformanceNode(name, lead, participants, duration);
			    	list.addToEnd(a);
			    	System.out.printf("\nNew performance %s is added to the end of the list.\n", name);
			    	break;
			    	
			    case('B'): //move current node backward
					try {
						if (list.moveCursorBackward())
							System.out.println("\nCursor has been moved backwards.");
						else
							System.out.println("\nCursor is already at the beginning of the list. Cursor cannot be moved backwards.");
					} catch (EmptyListException e) {
						System.out.println("\n" + e.getMessage());
					}
			    	break;
			    	
			    case('C'): //display the current node
			    	
			    	if (list.getSize() != 0) {
			    		System.out.println("\nCurrent node: \n");
			    		list.printTable();
			    	}
		    		try {
						list.displayCurrentPerformance();
					} catch (EmptyListException e) {
						System.out.println("\n" + e.getMessage());
						//e.printStackTrace();
					}
			    	break;
			    	
			    case('D'): //display all nodes
			    	
			    	if (list.getSize() != 0) {
			    		System.out.println("\nSchedule: \n\n");
			    		System.out.println(list);
			    	}
			    	else
			    		System.out.println("\nThe list is empty; there are no performances to display.");
			    	break;
			    	
			    case('F'): //Move current node forward
			    	
					try {
						if (list.moveCursorForward())
							System.out.println("\nCursor has been moved forward.");
						else
							System.out.println("\nCursor is already at the end of the list. Cursor cannot be moved forwards.");
					} catch (EmptyListException e1) {
						System.out.println("\n" + e1.getMessage());
					}
			    	break;
			    	
			    case('I'): //insert a new performance after the current node
			    	
			    	try {
			    		System.out.print("\nEnter name of performance: ");
			    		input.nextLine();
				    	name = input.nextLine();
				    	System.out.print("Enter name of lead performer: ");
				    	//input.nextLine();
				    	lead = input.nextLine();
				    	System.out.print("Enter the total participants: ");
				    	participants = input.nextInt();
				    	System.out.print("Enter the duration of the performance: ");
				    	duration = input.nextInt();
				    	a = new PerformanceNode(name, lead, participants, duration);
				    	list.addAfterCurrent(a);
				    	System.out.printf("\nNew performance %s is added after the current performance.\n", name);
			    	}catch(InputMismatchException e) {
			    		e.printStackTrace();
			    	}
			    	break;
			    	
			    case('J'): //jump to position
			    	
			    	try {
				    	System.out.print("Enter the position: ");
				    	position = input.nextInt();
						if (list.jumpToPosition(position))
							System.out.printf("\nCursor has been moved to position %d\n", position);
						else
							System.out.println("\nThat position doesn't exist in the list.\n");
					} catch (EmptyListException e) {
						System.out.println("\n" + e.getMessage());
					} catch (InputMismatchException e1) {
						System.out.println("\nInvalid input. Please enter an integer.\n");
					}
			    	break;
			    
			    case('R'): //remove current node
			    	
					try {
						String removed = "";
						if (list.getCursor() != null)
							removed = list.getCursor().getName();
						if (list.removeCurrentNode())
							System.out.printf("\n%s has been removed.\n", removed);
						else
							System.out.println("\nThere is no current performance; the list is empty.");
					} catch (EmptyListException e) {
						System.out.println("\n" + e.getMessage());
					}
			    	break;
			    	
			    case('Q'): //exit
			    	System.out.println("Quitting.");
			    	System.exit(0);
		    }
		    	
		}while(letter!='Q');
	}

}
