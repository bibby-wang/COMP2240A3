// - University of Newcastle
// - School of Electrical Engineering and Computer Science
// - COMP2240 Operating Systems
// - Assignment 3
// - Scheduling Algorithms
// - Name: Binbin Wang
// - Student No: 3214157
// - Date: 31-10-2018


import java.util.*;

public class SchedulingAlgorithms{
	public static int DISP;
	public static int jobsQuantity;
	public Process[] jobsStack;
	public int cpuTime;
	public float allJobsTurnaroundTime;
	public float allJobsWaitingTime;	
	public float averageTurnaroundTime;
	public float averageWaitingTime;
	public Queue<Process> jobsQueue= new LinkedList<Process>();
	//Construction
	public SchedulingAlgorithms(Process[] jobsStack,int DISP){
		
		this.jobsStack=jobsStack;		
		this.DISP=DISP;
		this.jobsQuantity=jobsStack.length;
		for (int i=0;i<jobsQuantity;i++){
			jobsQueue.offer(jobsStack[i]);
		}
		cpuTime=jobsQueue.element().getArriveTime();	
	}

	
	//list the Process Turnaround Time Waiting Time
	public String getJobsInformation(){
		String jobsInformation="";
		for (int i=0;i<jobsQuantity;i++){
			
			//format output 
			String strID="p"+jobsStack[i].getID();
			strID=stringFormat(strID,8);
			String strTT=" "+jobsStack[i].getTurnaroundTime();
			strTT=stringFormat(strTT,16);
			String strWT=" "+jobsStack[i].getWaitingTime();
			
			
			jobsInformation+=strID+strTT+strWT+"\r\n";
			allJobsTurnaroundTime+=jobsStack[i].getTurnaroundTime();
			allJobsWaitingTime+=jobsStack[i].getWaitingTime();
		}
		return jobsInformation;
	}
	
		
	//get summary information Average Turnaround Time and Average Waiting Time
	public String getSummary(){
		String sumString="";
		if (jobsQuantity!=0){
			//format output
			averageTurnaroundTime=allJobsTurnaroundTime/jobsQuantity;
			averageWaitingTime=allJobsWaitingTime/jobsQuantity;
			String aTT=String.format("%.2f",averageTurnaroundTime);
			String aWT=String.format("%.2f",averageWaitingTime);
			aTT=stringFormat(aTT,27);
			sumString+=aTT+aWT;
		}else{
			sumString+="No Jobs Count!";	
		}
		return sumString;
	}
	//format output String (Add spaces to align)
	public String stringFormat(String formatStr,int length){

		int l = length-formatStr.length();
        if(l>0){
			for (int i = 0; i < l; i++) {
                formatStr +=" ";
            }
        }
		return formatStr;
	}
	//ouput Ti: pj in string
	public String getTPString(int time,int processID){
		String outputTP="T";
		outputTP+=time+": p"+processID+"\r\n";
		return outputTP;
	}

	
}















