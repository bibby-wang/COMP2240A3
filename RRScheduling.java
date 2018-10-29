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
	private LinkedList<Process> readyQueue= new LinkedList<Process>();
	//private Queue<Process> blockedQueue= new LinkedList<Process>();
	private int timeQ;
	boolean loopFlg=true;
	Memory[] memory;
	ArrayList<Process> jobsStack;
	//Construction
	public RRScheduling(ArrayList<Process> jobsStack,int timeQ,Memory[] memory){
		this.jobsStack=jobsStack;;
		this.timeQ=timeQ;
		this.memory=memory;
	
	}

	//start running algorithm
	public void run(boolean type){
		int cpuTime=0;
		int ptimeQ=timeQ;
		for (int i=0;i<jobsStack.size();i++){
			if (memory[i].hasPage(jobsStack.get(i).elementPage())){
				readyQueue.offer(jobsStack.get(i));
			}else{
				jobsStack.get(i).setFaultTime(cpuTime);
			}
		}
		cpuTime++;
		while(loopFlg){
			//System.out.println("["+cpuTime+"]");
			
			loopFlg=false;
			for (int i=0;i<jobsStack.size();i++){
				if (jobsStack.get(i).hasJobs()){
					loopFlg=true;
				}
			}
			
			
			
			
			//
			for (int i=0;i<jobsStack.size();i++){
				
				if( jobsStack.get(i).readyTime()==cpuTime && jobsStack.get(i).hasJobs() ){
					readyQueue.offer(jobsStack.get(i));
					

					int pageNum=jobsStack.get(i).elementPage();
					if(!memory[i].addPage(pageNum)){
						System.out.print("======full=> ");
						// type true for LRU, false for Clock
						if(type){
							System.out.println("type:T,LRU");
							//LRU
							//System.out.println("pang: " + pageNum);
							System.out.println("memory: " + i);
							memory[i].addByLRU(pageNum);

							
						}else{
							System.out.println("type:F,Clock");
							//Clock 
							
							
						}
						//System.out.println("==========");
					}
					//System.out.println("p"+i+" addtime="+cpuTime);
					
				}
			}			
			//
			if (!readyQueue.isEmpty()){
				currentJob=readyQueue.poll();
			
				int	cID=currentJob.getID()-1;
				currentJob.pollPage();
				//System.out.println("["+cpuTime+"]"+"=p["+currentJob.getID()+"]: ");
				//
				if(currentJob.hasJobs()){
					if (memory[cID].hasPage(currentJob.elementPage())){
						if(ptimeQ>1 && currentJob.hasJobs()){
							readyQueue.addFirst(currentJob);
							ptimeQ--;
						}else{
							ptimeQ=3;
							readyQueue.offer(currentJob);
						}
						
					}else{
						//System.out.println("+"+cpuTime+" to "+currentJob.getID());
						currentJob.setFaultTime(cpuTime+1);
						
					}
				}
				
			}
			


			cpuTime++;
		}		
		
		for (int i=0;i<jobsStack.size();i++){
			System.out.println("["+i+"]"+jobsStack.get(i).getFaultTimes());
			
		}
		
	}


}
