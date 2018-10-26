// - University of Newcastle
// - School of Electrical Engineering and Computer Science
// - COMP2240 Operating Systems
// - Assignment 3
// - Scheduling Algorithms
// - FCFS RR FB NRR
// - Name: Binbin Wang
// - Student No: 3214157
// - Date: 07-09-2018

import java.io.*;
import java.util.*;

public class c3214157A1{
	
	public static void main(String args []){
		
		
		int DISP=0;
		int processID=0;
		int processArriveTime=0;
		int processExecSize=0;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		ArrayList<Process> processList = new ArrayList<>();//make a new process list
		//start loading data from file
		try{

			fileReader = new FileReader("."+File.separator+args[0]);//take the data file
			bufferedReader = new BufferedReader(fileReader);
			String lineData= bufferedReader.readLine();//take the line string
			String processData= null;
			
			//loading Process data 
			while(!lineData.equals("EOF")){


				if (lineData.length() > 5) {
					processData= lineData.substring(0,4);

				} 
				//get data
				if (processData != null){
					
					
					//get DISP
					if (processData.equals("DISP")){
						DISP = Integer.parseInt(lineData.substring(6));
					}						
					//get process number
					if (processData.equals("ID: ")){
						processID = Integer.parseInt(lineData.substring(5));
					}
					//get process arrive time
					if (processData.equals("Arri")){
						processArriveTime =  Integer.parseInt(lineData.substring(8));
					}
					//get process exec size
					if (processData.equals("Exec")){
						processExecSize =  Integer.parseInt(lineData.substring(10));
						// saving data to process list
						Process process= new Process(processID,processArriveTime,processExecSize);
						processList.add(process);

					}

					processData= null;
				}
			lineData= bufferedReader.readLine();
			
			}
		}catch(Exception e){
			System.err.println();//print loading file error
		}finally{
			try{
				assert bufferedReader != null;
				bufferedReader.close();
				fileReader.close();
			}
			catch(Exception e){
				System.err.println();
			}
		}
		//end of loading process data


		//start Algorithms
		
		int processQuantity=processList.size();	//process Quantity
		
		//make jobs stack for each Algorithm
		Process[] jobsStackFCFS=new Process[processQuantity];
		Process[] jobsStackRR=new Process[processQuantity];
		Process[] jobsStackFB=new Process[processQuantity];
		Process[] jobsStackNRR=new Process[processQuantity];
		//make process and jobs Queue for each algorithms 
		for (int i=0; i < processList.size(); i++){

			
			processID=processList.get(i).getID();
			processArriveTime=processList.get(i).getArriveTime();
			processExecSize=processList.get(i).getExecSize();


			
			//FCFS
			Process processFCFS= new Process(processID,processArriveTime,processExecSize);
			//Insert to jobsStackFCFS
			jobsStackFCFS[i]=processFCFS;
			
			//RR
			Process processRR= new Process(processID,processArriveTime,processExecSize);
			//Insert to jobsStackRR
			jobsStackRR[i]=processRR;
			
			//FB
			Process processFB= new Process(processID,processArriveTime,processExecSize);
			//Insert to jobsStackFB
			jobsStackFB[i]=processFB;
			
			//NRR
			Process processNRR= new Process(processID,processArriveTime,processExecSize);
			//Insert to jobsStackNRR
			jobsStackNRR[i]=processNRR;
			


		}

		//////////////////////
		// output FCFS
		//////////////////////
		AlgFCFS jobsOnFCFS= new AlgFCFS(jobsStackFCFS,DISP);
		System.out.println("FCFS:");
		System.out.println(jobsOnFCFS.runningAlgorithm());
		System.out.println();
		System.out.println("Process Turnaround Time  Waiting Time");
		System.out.println(jobsOnFCFS.getJobsInformation());
		
		//////////////////////
		// output RR 
		//////////////////////
		AlgRR jobsOnRR= new AlgRR(jobsStackRR,DISP,0);// decrease TimeQ by 0
		System.out.println("RR:");
		System.out.println(jobsOnRR.runningAlgorithm());
		System.out.println();
		System.out.println("Process Turnaround Time  Waiting Time");
		System.out.println(jobsOnRR.getJobsInformation());

		//////////////////////
		// SUMMARY
		//////////////////////

		System.out.println("SUMMARY");
		System.out.println("Algorithm     Average Turnaround Time   Average Waiting Time");
		System.out.println("FCFS          "+jobsOnFCFS.getSummary());
		System.out.println("RR            "+jobsOnRR.getSummary());
		System.out.println("FB(constant)  "+jobsOnFB.getSummary());
		System.out.println("NRR           "+jobsOnNRR.getSummary());

		
	}

	
}
