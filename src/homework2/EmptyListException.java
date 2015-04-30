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
 * The EmptyListExcepiton class is thrown to indicate that there are no PerformanceNode's inside of the PerformanceList 
 * (the list is empty).
 */
public class EmptyListException extends Exception {

	public EmptyListException() {
		super("There is no current performance; the list is empty.");
	}

}
