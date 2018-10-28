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


//要查看 父类的方法是否还有用
//看上去不太有用了
//
//



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

	//start running algorithm
	public String runningAlgorithm(){
		String outputString="";
		do{
			
			//不用这部分
			//check job arrival
			while(!jobsQueue.isEmpty()){
				int arrTime=jobsQueue.element().getArriveTime();
				int timeQ=0;
				int tempTime=0;
				if (!readyQueue.isEmpty()){
					timeQ=readyQueue.element().getTimeQ()+DISP;// add 4!!!!
					
					//????
					tempTime=readyQueue.element().getSurplusTime()+DISP;
				}
				//get arrival job
				if (tempTime>timeQ){tempTime=timeQ;}
				if (arrTime<=cpuTime+tempTime){
					readyQueue.offer(jobsQueue.poll());//insert to the ready Queue
				}else{
					break;
				}
			}
			//
			//is a job in ready queue
			if (!readyQueue.isEmpty()){
				currentJob=readyQueue.poll();//get the job from ready queue
				int jobID=currentJob.getID();
				int jobArriveTime=currentJob.getArriveTime();
				int jobExecSize=currentJob.getSurplusTime();
				//查所需要的页面 在内存中吗
				//页面在内存中 执行 RR
				//页面不在内存 加载页面到内存 然后再到ready Queue队列 修改下次可执行的时间
				cpuTime+=DISP;//add the dispatcher running time
				//也有加4 注意理解 
				// 之前是直接加时间 这次要修改成循环执行的次数 理论上cpu时间只能++
				if (jobExecSize<=currentJob.getTimeQ() || readyQueue.isEmpty()){
					
					//相当于执行程序
					outputString+=getTPString(cpuTime,jobID);//output String Ti: pj
					
					cpuTime+=jobExecSize;
					currentJob.setTWTime(cpuTime);
					
					
				}else{
					
					//相当于执行程序 
					outputString+=getTPString(cpuTime,jobID);//output String Ti: pj
					
					cpuTime+=currentJob.getTimeQ();
					currentJob.setSurplusTime(jobExecSize-currentJob.getTimeQ());
					currentJob.shortTimeQ(decreaseTimeQ);//short Time Quantum

					readyQueue.offer(currentJob);//insert to the end of ready Queue
				}

				
			}else{
				cpuTime++;
			}
			
		}while(!readyQueue.isEmpty() || !jobsQueue.isEmpty());
		return outputString;
	}

}
