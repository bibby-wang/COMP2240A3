// - University of Newcastle
// - School of Electrical Engineering and Computer Science
// - COMP2240 Operating Systems
// - Assignment 3
// - Scheduling Algorithms 
// - Round Robin
// - Round Robin time quantum is 4 ms
// - Name: Binbin Wang
// - Student No: 3214157
// - Date: 31-10-2018

import java.util.*;
public class AlgRR extends SchedulingAlgorithms{
	private Process currentJob;	
	private Queue<Process> readyQueue= new LinkedList<Process>();
	private int decreaseTimeQ;
	//Construction
	public AlgRR(Process[] jobsStack,int DISP,int decreaseTimeQ){
		super(jobsStack,DISP);
		this.decreaseTimeQ=decreaseTimeQ;

	}

	//satar running algorithm
	public String runningAlgorithm(){
		String outputString="";
		do{
			//check job arrival
			while(!jobsQueue.isEmpty()){
				int arrTime=jobsQueue.element().getArriveTime();
				int timeQ=0;
				int tempTime=0;
				if (!readyQueue.isEmpty()){
					timeQ=readyQueue.element().getTimeQ()+DISP;
					tempTime=readyQueue.element().getSurplusTime()+DISP;
				}
				//get arrival job
				if (tempTime>timeQ){tempTime=timeQ;}
				if (arrTime<=cpuTime+tempTime){
					readyQueue.offer(jobsQueue.poll());//inster to the ready Queue
				}else{
					break;
				}
			}
			//is a job in ready queue
			if (!readyQueue.isEmpty()){
				currentJob=readyQueue.poll();//get the job from ready queue
				int jobID=currentJob.getID();
				int jobArriveTime=currentJob.getArriveTime();
				int jobExecSize=currentJob.getSurplusTime();
				
				cpuTime+=DISP;//add the dispatcher running time
				if (jobExecSize<=currentJob.getTimeQ() || readyQueue.isEmpty()){
					
					outputString+=getTPString(cpuTime,jobID);//output String Ti: pj
					cpuTime+=jobExecSize;
					currentJob.setTWTime(cpuTime);
					
					
				}else{
					
					
					outputString+=getTPString(cpuTime,jobID);//output String Ti: pj
					cpuTime+=currentJob.getTimeQ();
					currentJob.setSurplusTime(jobExecSize-currentJob.getTimeQ());
					currentJob.shortTimeQ(decreaseTimeQ);//short Time Quantum

					readyQueue.offer(currentJob);//inster to the end of ready Queue
				}

				
			}else{
				cpuTime++;
			}
			
		}while(!readyQueue.isEmpty() || !jobsQueue.isEmpty());
		return outputString;
	}

}
