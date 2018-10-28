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

	private String processName;
	ArrayList<Integer> pagesList= new ArrayList<Integer>();

	private int readyTime;
	
	private int turnaroundTime = 0;
	private int waitingTime = 0;
	private int faults = 0;
	private String faultTimes;
	
	
	//Construction
	Process(int processID,String processName,ArrayList<Integer> pagesList){
		this.processID=processID;
		this.processName=processName;
		this.pagesList = pagesList;
	}

	// get page number 	
	public int getPage(int index){
		return pagesList.get(index);
	}
		
	// get pagesList	
	public ArrayList<Integer> getPagesList(){
		return pagesList;
	}
	// get pages list size
	public int getPagesListSize(){
		return pagesList.size();
	}
	// get time of process ready	
	public int readyTime(){
		return readyTime;
	}
	// set next ready time of process	
	public void setReadyTime(int time){
		readyTime=time;
	}		
	
	// get id of process	
	public int getID(){
		return processID;
	}
	public String getFaultTimes(){
		return faultTimes;
	}
	public void getFaultTimes(String data){
		faultTimes+=data+",";
	}
	
	// get name of process	
	public String getName(){
		return processName;
	}


}
