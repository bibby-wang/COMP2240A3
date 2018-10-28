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
	public boolean addPage(int page){
		if(frameList.size()<memorySize){
			frameList.add(page);
			return true;
		}
		return false;
	}
	// set page into frameList(index)
	public void setPage(int index,int page){
		frameList.set(index,page);
	}
		
	// get frameList	
	public ArrayList<Integer> getFrameList(){
		return frameList;
	}
	// get memory size
	public int size(){
		return memorySize;
	}
	// get frame list size
	public int getframeListSize(){
		return frameList.size();
	}
		
	
	// get page	by index
	public int getPage(int index){
		return frameList.get(index);
	}
	
	// get page	
	public ArrayList<Integer> getAll(){
		return frameList;
	}
		
	// check the page is in memory	
	public boolean hasPage(int page){
		for(int i=0;i<frameList.size();i++){
			if (frameList.get(i)==page){
				return true;
			}
		}
		return false;
	}
	// check is memory space full 	
	public boolean isFull(){
		if (frameList.size()< memorySize){return false;}
		return true;
	}
		
}
