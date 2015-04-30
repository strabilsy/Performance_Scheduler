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
 * The PerformanceNode class contains basic information about a performance in a festival such as 
 * the name of the performance, the name of the lead performer, total participants, 
 * duration of the performance (in minutes), and the start time of the performance (in minutes for simplicity). 
 */
public class PerformanceNode {
	private String name;
	private String lead;
	private int totalPar; //total participants
	private int duration;
	private int start;
	private PerformanceNode next;
	private PerformanceNode prev;
	private int position;
	
	/**
	 * Creates a new PerformanceNode with default (null) values
	 */
	public PerformanceNode() {
		name = "";
		lead = "";
		totalPar = 0;
		duration = 0;
		start = 0;
		next = null;
		prev = null;
		position = 1;
	}
	
	/**
	 * Creates a new PerformanceNode with specified values
	 * 
	 * @param name The name of the performance
	 * @param lead The name of the lead performer
	 * @param participants Total # of participants
	 * @param duration Duration of performance (in minutes)
	 */
	public PerformanceNode(String name, String lead, int participants, int duration) {
		this.name = name;
		this.lead = lead;
		this.totalPar = participants;
		this.duration = duration;
		start = 0;
		next = null;
		prev = null;
		position = 1;
	}
	
	/* set/get methods
	 * include mutator and accessor methods for all data fields
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLead(String lead) {
		this.lead = lead;
	}
	
	public void setTotalPar(int participants) {
		this.totalPar = participants;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public void setNext(PerformanceNode node) {
		next = node;
	}
	
	public void setPrev(PerformanceNode node) {
		prev = node;
	}
	
	public void setPos(int position) {
		this.position = position;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLead() {
		return lead;
	}
	
	public int getTotalPar() {
		return totalPar;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public int getStart() {
		return start;
	}
	
	public PerformanceNode getNext() {
		return next;
	}
	
	public PerformanceNode getPrev() {
		return prev;
	}
	
	public int getPosition() {
		return position;
	}
	
	/**
	 * Gets the String representation of this PerformanceNode, which is a neatly formatted string 
	 * containing all the data of this PerformanceNode
	 * 
	 * @return The String representation of this PerformanceNode.
	 */
	public String toString() {
		String s = String.format("%-4d%-25s%-25s%-14d%-10d%d\n", position, name, lead, totalPar, duration, start);
		return s;
	}
}
