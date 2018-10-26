// - University of Newcastle
// - School of Electrical Engineering and Computer Science
// - COMP2240 Operating Systems
// - Assignment 3
// - simulate a system that uses paging with virtual memory
// - Name: Binbin Wang
// - Student No: 3214157
// - Date: 31-10-2018
import java.util.*;
public class Memory{

	private int memorySize;
	private ArrayList<Integer> frameList;//store pages from process 

	//Construction
	Memory(int size){
		this.memorySize=size;
		this.frameList = new ArrayList<Integer>(size);
	}
	// add page into frameList
	public void addPage(int page){
		frameList.add(page);
	}
		
	// get frameList	
	public ArrayList<Integer> getFrameList(){
		return frameList;
	}
	// get momery size
	public int size(){
		return memorySize;
	}
	// get frame list size
	public int getframeListSize(){
		return frameList.size();
	}
		
	
	// get page	
	public int getPage(int index){
		return frameList.get(index);
	}
	
	// check the page is in memory	
	public boolean isPageIn(int page){
		for(int i=0;i<frameList.size();i++){
			if (frameList.get(i)==page){
				return true;
			}
		}
		return false;
	}
		
}
