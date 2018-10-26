// - University of Newcastle
// - School of Electrical Engineering and Computer Science
// - COMP2240 Operating Systems
// - Assignment 3
// - simulate a system that uses paging with virtual memory
// - Name: Binbin Wang
// - Student No: 3214157
// - Date: 31-10-2018

import java.io.*;
import java.util.*;

public class c3214157A3{
	
	public static void main(String args []){
		
		// get the frames Number
		int framesNumber = Integer.parseInt(args[0]);
		
		// get the quatum Size
		int quatumSize = Integer.parseInt(args[1]);
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
		ArrayList<Integer> pagesList = new ArrayList<Integer>();
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
					pagesList.add(pageNumber);

				}

			}catch (FileNotFoundException e){  
               e.printStackTrace();  
			} 
			Process process = new Process(i+1,args[i+2],pagesList);				
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
	framesNumber /= processList.size();
 	System.out.println("framesNumber="+framesNumber);
		Memory[] memory = new Memory[processList.size()]; 
		
		for (int i=0;i<processList.size();i++){
			memory[i]= new Memory(framesNumber);
		}
		memory[0].addPage(3);
		System.out.println("04=yes="+memory[0].getframeListSize());
		System.out.println("04=yes="+memory[0].getSize());
/*		memory[0].addPage(4);
		memory[1].addPage(3);


		if(memory[0].isPageIn(3)){
			System.out.println("04=yes="+memory[0]getframeListSize());
		}
		if(memory[0].isPageIn(3)){
			System.out.println("03=yes=");
		}
		if(memory[1].isPageIn(4)){
			System.out.println("14=yes=");
		}
		if(memory[1].isPageIn(3)){
			System.out.println("13=yes=");
		}
	 */
	
	}

}