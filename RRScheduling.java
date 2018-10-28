// - University of Newcastle
// - School of Electrical Engineering and Computer Science
// - COMP2240 Operating Systems
// - Assignment 3
// - Scheduling Algorithms 
// - Round Robin
// - Round Robin time quantum is timeQ
// - Name: Binbin Wang
// - Student No: 3214157
// - Date: 31-10-2018

import java.util.*;
public class RRScheduling{
	
	private Process currentJob;	
	private Queue<Process> readyQueue= new LinkedList<Process>();
	private Queue<Process> blockedQueue= new LinkedList<Process>();
	private int timeQ;
	ArrayList<Process> jobsStack;
	//Construction
	public RRScheduling(ArrayList<Process> jobsStack,int timeQ){
		this.jobsStack=jobsStack;;
		this.timeQ=timeQ;

	}
//readyQueue.offer()//in
//jobsQueue.poll()//out
	//start running algorithm
	public void run(){
		int cpuTime=0;
		
		for (int i=0;i<jobsStack.size();i++){
			readyQueue.offer(jobsStack.get(i));
		}
		
		while(true){

			if (cpuTime>50){break;}
			
			
			
			if (!readyQueue.isEmpty()){
				//unknown
				//if (readyQueue.element().readyTime()<=cpuTime){
					currentJob=readyQueue.poll();
			
					//run timeQ times
					for(int i=0;i<timeQ;i++){
						if (currentJob.getPageQueueSize()>0){
							System.out.println("p["+currentJob.getID()+"] page="+currentJob.pollPage()+" CPU time="+cpuTime);
							cpuTime++;
						}
						
					}
					if (currentJob.getPageQueueSize()>0){
						readyQueue.offer(currentJob);
					}
			}else{
				
				cpuTime++;	
			}
			
		
		}
	}

}
