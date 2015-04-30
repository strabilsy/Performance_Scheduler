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

/**
 * The PerformanceList class implements an abstract data type for a list of performances supporting some common operations on such lists.
 */
public class PerformanceList {
	private PerformanceNode head;
	private PerformanceNode tail;
	private PerformanceNode cursor;
	private int size;
	
	/**
	 * Creates an empty PerformanceList
	 * 
	 * <dt><b>Postcondition:</b><dd> This PerformanceList has been initialized to an empty list of PerformanceNodes.
	 */
	public PerformanceList() {
		head = null;
		tail = null;
		cursor = null;
		size = 0;
	}
	
	/**
	 * Inserts the new performance at the end of the PerformanceList. 
	 * NOTE: This method may still be used even when the list is empty. 
	 * 
	 * @param newPerformance the new PerformanceNode to add to this list
	 * 
	 * <dt><b>Precondition:</b><dd> This PerformanceList has been instantiated.
	 * 
	 * <dt><b>Postcondition:</b><dd> The new PerformanceNode is now at the end of the PerformanceList.
	 * The new PerfomanceNode is now the cursor and tail(and if the list was empty, it's also the head).
	 * The size is incremented.
	 */
	public void addToEnd(PerformanceNode newPerformance) {
		if (tail != null) { //if tail (or head or cursor, technically) is null, list is empty
			tail.setNext(newPerformance);
			newPerformance.setPrev(tail);
			newPerformance.setStart(tail.getStart() + tail.getDuration());
		}
		else { //if list is empty, make the newPerformance the head
			head = newPerformance;
		}
		tail = newPerformance;
		cursor = tail;
		newPerformance.setNext(null);
		size++;
		newPerformance.setPos(size);
	}
	
	/**
	 * Inserts the new data into the PerformanceList such that the new node directly 
	 * follows the current node, if it exists. 
	 * If there is no current node (i.e., the current node is null), inserts the new node at the end of the list. 
	 * 
	 * @param newPerformance the new PerformanceNode to add to this list
	 * 
	 * <dt><b>Precondition:</b><dd> This PerformanceList has been instantiated.
	 * 
	 * <dt><b>Postcondition:</b><dd> The new PerformanceNode now follows the current node, if it exists, 
	 * and is now the cursor. If there was no current node, the new PerformanceNode is now at the end of the 
	 * PerformanceList and is the head, cursor, and tail. The starting times of all subsequent 
	 * performances are extended by the value of the duration of the newly added performance. 
	 * The size is incremented.
	 */
	public void addAfterCurrent(PerformanceNode newPerformance) {
		if (cursor != null) {
			newPerformance.setNext(cursor.getNext());
			newPerformance.setPrev(cursor);
			cursor.setNext(newPerformance);
			newPerformance.setStart(cursor.getStart() + cursor.getDuration());
			newPerformance.setPos(cursor.getPosition() + 1);
			if (cursor != tail) 
				newPerformance.getNext().setPrev(newPerformance);
			else
				tail = newPerformance;
			cursor = newPerformance;
			while (cursor != tail) {
				cursor.getNext().setStart(cursor.getNext().getStart() + newPerformance.getDuration());
				cursor.getNext().setPos(cursor.getNext().getPosition() + 1);
				cursor = cursor.getNext();
			}
			cursor = newPerformance;
			size++;
		}
		else {
			addToEnd(newPerformance);
		}
	}
	
	/**
	 * Removes the current node, if it exists. 
	 * 
	 * <dt><b>Precondition:</b><dd> This PerformanceList has been instantiated
	 * 
	 * <dt><b>Postcondition:</b><dd> The current PerformanceNode has been removed, if it existed.
	 * If so, the positions of all subsequent performances are decremented and their starting times are reduced
	 * by the value of the duration of the removed performance. The current node is now the node after 
	 * the one that was just removed. If there is no node after the one that was just removed, the current node is 
	 * now the node before the one that was just removed. If the node removed was the 
	 * only one in the PerformanceList, the cursor is now null. 
	 * The size is decremented.
	 * 
	 * @return True, if the current node was removed, false otherwise.
	 * @throws EmptyListException Indicates that there are no PerformanceNode's inside of the PerformanceList 
	 * (the list is empty). In this case, it is thrown if moveCursorForward() or moveCursorBackward() throws it.
	 */
	public boolean removeCurrentNode() throws EmptyListException {
		if (cursor != null) {
			if (head != tail) { //check if the size is 1
				if (cursor != tail) {	//check if there is a node after the current node (if current node is not the tail)		
					if (cursor != head) { //check if there is a node before the current node (if current node is not the head)
						cursor.getPrev().setNext(cursor.getNext());
						cursor.getNext().setPrev(cursor.getPrev());
					}
					else { // if current node is head
						cursor.getNext().setPrev(null);
						head = cursor.getNext();
					}
						
					PerformanceNode temp = cursor;
					while (temp != tail) {
						temp.getNext().setStart(temp.getNext().getStart() - cursor.getDuration());
						temp.getNext().setPos(temp.getNext().getPosition() - 1);
						temp = temp.getNext();
					}
					moveCursorForward();//cursor = cursor.getNext();
				}
				else { // if current node is the tail
					cursor.getPrev().setNext(null);
					moveCursorBackward(); //cursor = cursor.getPrev();
					tail = cursor;
				}
			}
			else {
				cursor = null;
				head = null;
				tail = null;
			}
			size--;
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Prints the data in the current node
	 * 
	 * <dt><b>Precondition:</b><dd> This PerformanceList has been instantiated
	 * 
	 * @throws EmptyListException Indicates that there are no PerformanceNode's inside of the PerformanceList 
	 * (the list is empty)
	 */
	public void displayCurrentPerformance() throws EmptyListException {
    	if (cursor != null) {
    		System.out.println("      * " + cursor);
    	}
    	else {
    		throw new EmptyListException();
    	}
	}
	
	/**
	 * Moves the reference of the current node forward in the list by one position 
	 * if a node exists after the current one. If there is no next node, the current node 
	 * remains the same. 
	 * 
	 * <dt><b>Precondition:</b><dd> This PerformanceList has been instantiated and there is a current node.
	 * 
	 * <dt><b>Postcondition:</b><dd> The reference to the current node was moved to the subsequent node. 
	 * 
	 * @return True, if the cursor was moved forward, false otherwise
	 * @throws EmptyListException Indicates that there are no PerformanceNode's inside of the PerformanceList 
	 * (the list is empty)
	 */
	public boolean moveCursorForward() throws EmptyListException {
		if (cursor != null) {
			if (cursor != tail) {
				cursor = cursor.getNext();
				return true;
			}
			else
				return false;
		}
		else {
			throw new EmptyListException();
		}
	}
	
	/**
	 * Moves the reference of the current node backwards in the list by one position 
	 * if a node exists before the current one. If there is no previous node, the current node remains 
	 * the same. 
	 * 
	 * <dt><b>Precondition:</b><dd> This PerformanceList has been instantiated and there is a current node.
	 * 
	 * <dt><b>Postcondition:</b><dd> The reference to the current node was moved to the previous node. 
	 * 
	 * @return True, if the cursor was moved backwards, false otherwise
	 * @throws EmptyListException Indicates that there are no PerformanceNode's inside of the PerformanceList 
	 * (the list is empty)
	 */
	public boolean moveCursorBackward() throws EmptyListException {
		if (cursor != null) {
			if (cursor != head) {
				cursor = cursor.getPrev();
				return true;
			}
			else
				return false;
		}
		else
			throw new EmptyListException();
	}
	
	/**
	 * Moves the current node to the given position in the PerformanceList. 
	 * If the given position doesn't exist in the list, leaves the current node where it was. 
	 * 
	 * @param position The position to move the cursor to
	 * @return True, if the cursor was moved to the given position, false otherwise
	 * @throws EmptyListException Indicates that there are no PerformanceNode's inside of the PerformanceList 
	 * (the list is empty). In this case, it is thrown if moveCursorForward() or moveCursorBackward() throws it.
	 */
	public boolean jumpToPosition(int position) throws EmptyListException {
		if (position > size || position < 1)
			return false;
		else {
			if (cursor.getPosition() != position) {
				cursor = head;
				while (cursor.getPosition() != position) {
					moveCursorForward();
				}
			}
			return true;
		}
	}
	
	/**
	 * Returns a neatly formatted table of all information for all the scheduled performances.
	 * 
	 * @return The String representation of this PerformanceList
	 */
	public String toString() {
		printTable();
		String table = "";
		
		PerformanceNode temp = head;
		while (temp != tail) {
			if (temp == cursor)
				table += "      * ";
			else
				table += "        ";
			table += temp;
			temp = temp.getNext();
		}
		//Include the tail (I kept it out of the loop to avoid a NullPointerException from temp.getNext()
		if (tail == cursor)
			table += "      * ";
		else
			table += "        ";
		table += String.format("%-4d%-25s%-25s%-14d%-10d%d", temp.getPosition(), temp.getName(), 
				temp.getLead(), temp.getTotalPar(), temp.getDuration(), temp.getStart()) + "\n";
		return table;
	}
	
	/**
	 * (helper method) Prints a neatly formatted table header
	 */
	public void printTable() {
		String table = String.format("%-8s%-4s%-25s%-25s%-14s%-10s%s", "Current", "No.", "Performance Name", 
				"Lead Performer Name", "Participants", "Duration", "Start Time") + "\n";
		table += String.format("%-8s%-4s%-25s%-25s%-14s%-10s%s", "_______", "___", "________________", 
				"___________________", "____________", "________", "___________") + "\n";
		System.out.print(table);
	}
	
	//getters
	public int getSize() {
		return size;
	}
	
	public PerformanceNode getCursor() {
		return cursor;
	}
}
