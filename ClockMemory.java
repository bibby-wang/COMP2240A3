// - University of Newcastle
// - School of Electrical Engineering and Computer Science
// - COMP2240 Operating Systems
// - Assignment 3
// - simulate a system that uses paging with virtual memory
// - Name: Binbin Wang
// - Student No: 3214157
// - Date: 31-10-2018
import java.util.*;
public class ClockMemory extends Memory{


	//Construction
	ClockMemory(int size){
		super(size);

	}
	public void sink(int i){
		int page=frameList.get(i);
		int end=frameList.size()-1;
		for(int j=i;j<end;j++){
			int next=j+1;
			frameList.set(j,frameList.get(next));
		}
		frameList.set(end,page);
	}
	//LRU
	public void addByClock(int page){
		

		super.setPage(0,page);
		this.sink(0);
		//printMemory();
	}

	// check the page is in memory	
	public boolean hasPage(int page){
		for(int i=0;i<frameList.size();i++){
			if (frameList.get(i)==page){
				this.sink(i);
				return true;
			}
		}
		return false;
	}

}