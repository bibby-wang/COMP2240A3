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
	boolean blocked=true;
	Memory[] memory;
	ArrayList<Process> jobsStack;
	//Construction
	public RRScheduling(ArrayList<Process> jobsStack,int timeQ,Memory[] memory){
		this.jobsStack=jobsStack;;
		this.timeQ=timeQ;
		this.memory=memory;
	
	}
//readyQueue.offer()//in
//jobsQueue.poll()//out
	//start running algorithm
	public void run(){
		int cpuTime=6;
		
		for (int i=0;i<jobsStack.size();i++){
			readyQueue.offer(jobsStack.get(i));
			memory[i].addPage(jobsStack.get(i).elementPage());
		}
		
		while(true){

			if (cpuTime>50){break;}
			
			
			
			if (!readyQueue.isEmpty()){
				//unknown
				//if (readyQueue.element().readyTime()<=cpuTime){
					currentJob=readyQueue.poll();
					int cID=currentJob.getID()-1;
					
					//run timeQ times
					for(int i=0;i<timeQ;i++){
						
						
						if (currentJob.getPageQueueSize()>0){
							
							if (memory[cID].hasPage(currentJob.elementPage())){
								System.out.println("p["+currentJob.getID()+"] page="+currentJob.pollPage()+" CPU time="+cpuTime);
								blocked=false;
								cpuTime++;
							}
							else{
								//not in memory!!
								System.out.println("p["+currentJob.getID()+"] page="+currentJob.elementPage()+" CPU time="+cpuTime+" no in memony");
								blocked=true;
								break;
							}
							
						}
						
					}
					if (currentJob.getPageQueueSize()>0 && !blocked){
						readyQueue.offer(currentJob);
					}
			}else{
				
				cpuTime++;	
			}
			
		
		}
	}

}
