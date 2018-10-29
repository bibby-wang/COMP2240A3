// - University of Newcastle
// - School of Electrical Engineering and Computer Science
// - COMP2240 Operating Systems
// - Assignment 3
// - simulate a system that uses paging with virtual memoryLRU
// - Name: Binbin Wang
// - Student No: 3214157
// - Date: 31-10-2018

import java.io.*;
import java.util.*;

public class c3214157A3{
	
	public static void main(String[] args){
		//for different type output
		for(int k=0;k<2;k++){
			// get the frames Number
			int framesNumber = Integer.parseInt(args[0]);
			
			// get the quantum Size
			int quantumSize = Integer.parseInt(args[1]);
			// get the Process name and loading it
			int numberOfProcess=args.length-2;
			
			
			ArrayList<Process> processList = new ArrayList<>();//make a new process list		
			for (int i=0;i<numberOfProcess;i++){
				//  the Process file name
				//fileName[i]=args[i+2];
				
				
			
				// for (int i=0;i<numberOfProcess;i++){
					// System.out.println(fileName[i]);
				// }
				
				

				FileReader fileReader = null;
				BufferedReader bufferedReader = null;
				Queue<Integer> pageQueue = new LinkedList<Integer>();
				//start loading data from file
				try{
					
					//loading Process data 
					FileReader dataFlie = new FileReader(args[i+2]); //get data from file xx.txt
						 
					//FileReader dataFlie = new FileReader("p2.txt"); //get data from file
		 
					Scanner dataIn= new Scanner(dataFlie);		
					String dataLine = dataIn.nextLine();


					
					while(dataIn.hasNextInt()){


						int pageNumber = Integer.parseInt(dataIn.next());

						// save data into pages list
						pageQueue.offer(pageNumber);

					}

				}catch (FileNotFoundException e){  
				   e.printStackTrace();  
				} 
				Process process = new Process(i+1,args[i+2],pageQueue);				
				processList.add(process);
				

			}
			//end of loading process data
			////////
		/* 		
			for (int i=0;i<processList.size();i++){
					System.out.println("= "+processList.get(i).getName());
				for (int j=0;j<processList.get(i).getPagesList().size();j++){
					System.out.println("== "+processList.get(i).getPage(j));
				}
			} 
		*/
			//equally divided number of frames allocated to each process
			framesNumber /= processList.size();
			//System.out.println("framesNumber="+framesNumber);
			LRUMemory[] memoryLRU = new LRUMemory[processList.size()]; 
			ClockMemory[] memoryClock = new ClockMemory[processList.size()]; 
			
			for (int i=0;i<processList.size();i++){
				memoryLRU[i]= new LRUMemory(framesNumber);
				memoryClock[i]= new ClockMemory(framesNumber);
			}
			
			//	RRScheduling(ArrayList<Process> jobsStack,int timeQ){
			
			if (k==0){
				RRSchedulingLRU runLRU=new RRSchedulingLRU(processList,quantumSize,memoryLRU);
				runLRU.run();
				
			}else{
				System.out.println("------------------------------------------------------------");
				System.out.println("");


				//runClock.run(true);
			}
		
	
		}

	}

}
