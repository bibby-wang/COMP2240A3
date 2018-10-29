// - University of Newcastle
// - School of Electrical Engineering and Computer Science
// - COMP2240 Operating Systems
// - Assignment 3
// - simulate a system that uses paging with virtual memory
// - Name: Binbin Wang
// - Student No: 3214157
// - Date: 31-10-2018


import java.util.*;
public class Process{

	private int processID;
	private int readyTime;

	
	private String processName;
	//ArrayList<Integer> pagesList= new ArrayList<Integer>();
	Queue<Integer> pageQueue= new LinkedList<Integer>();

	
	private int turnaroundTime = -1;
	private int waitingTime = 0;
	private int faults = 0;
	private String faultTimes="";
	
	
	//Construction
	Process(int processID,String processName,Queue<Integer> pageQueue){
		this.processID=processID;
		this.processName=processName;
		this.pageQueue = pageQueue;
		
	}

	// get page number 	
	public int pollPage(){
		return pageQueue.poll();
	}
	
	// get page number 	
	public int elementPage(){
		return pageQueue.element();
	}
		
	// get pageQueue	
	public Queue<Integer> getPageQueue(){
		return pageQueue;
	}
	// get pages list size
	public int getPageQueueSize(){
		return pageQueue.size();
	}
	// is process has more Jobs
	public boolean hasJobs(){
		return !pageQueue.isEmpty();
	}

	// get id of process	
	public int getID(){
		return processID;
	}
	//
	public String getFaultTimes(){
		return faultTimes;
	}
	//
	public void setFaultTime(String data){
		faultTimes+=data+",";
	}
	//	
	public void setFaultTime(int time){
		readyTime=time+6;
		this.setFaultTime(Integer.toString(time));
		faults++;
	}
	//
	public int readyTime(){
		return readyTime;
	}
	//
	public int faults(){
		return faults;
	}
	//
	public void setTurnaroundTime(int time){
		if (turnaroundTime<0) {turnaroundTime=time;}
	}
	//
	public int turnaroundTime(){
		return turnaroundTime;
	}
	
	// get name of process	
	public String getName(){
		return processName;
	}


}
