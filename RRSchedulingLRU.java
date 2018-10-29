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
public class RRSchedulingLRU{
	
	private Process currentJob;	
	private LinkedList<Process> readyQueue= new LinkedList<Process>();


	private int timeQ;
	boolean loopFlg=true;
	LRUMemory[] memory;
	ArrayList<Process> jobsStack;
	
	//Construction
	public RRSchedulingLRU(ArrayList<Process> jobsStack,int timeQ,LRUMemory[] memory){
		this.jobsStack=jobsStack;;
		this.timeQ=timeQ;
		this.memory=memory;
	
	}


	//start running algorithm
	public void run(){
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
			System.out.println("["+cpuTime+"]");
			
			loopFlg=false;
			for (int i=0;i<jobsStack.size();i++){
				if (jobsStack.get(i).hasJobs()){
					loopFlg=true;
				}else{
					jobsStack.get(i).setTurnaroundTime(cpuTime);
				}
			}

			//
			for (int i=0;i<jobsStack.size();i++){
				System.out.println("m"+i);
				memory[i].printMemory();
				if( jobsStack.get(i).readyTime()==cpuTime && jobsStack.get(i).hasJobs() ){
					readyQueue.offer(jobsStack.get(i));
					

					int pageNum=jobsStack.get(i).elementPage();
					if(!memory[i].addPage(pageNum)){
						//System.out.println("["+i+"]");

						memory[i].addByLRU(pageNum);


					}

					
				}
			}			
			//
			if (!readyQueue.isEmpty()){
				currentJob=readyQueue.poll();
			
				int	cID=currentJob.getID()-1;
				currentJob.pollPage();

				//
				if(currentJob.hasJobs()){
					if (memory[cID].hasPage(currentJob.elementPage())){
						if(ptimeQ>1 && currentJob.hasJobs()){
							readyQueue.addFirst(currentJob);
							ptimeQ--;
						}else{
							ptimeQ=timeQ;
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

		System.out.println("LRU - Fixed:");
		System.out.println("PID   Process Name      Turnaround Time     #Faults   Fault Times");
			

		for (int i=0;i<jobsStack.size();i++){
			int PID=i+1;
			String name=jobsStack.get(i).getName();
			int faults=jobsStack.get(i).faults();
			int turnaroundTime=jobsStack.get(i).turnaroundTime();
			String timelist=jobsStack.get(i).getFaultTimes();
			timelist=timelist.substring(0,timelist.length()-1);
			System.out.println(PID+"     "+name+"      "+ turnaroundTime+"                 "+faults+"        {"+timelist+"}");
			
		}
		System.out.println("");
	}


}
