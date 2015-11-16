package com.ibm.btt.tool.memory;

import java.util.ArrayList;
import java.util.List;
import com.ibm.btt.tool.common.ToolProperty;

public class MemoryMap{
	/**
	 * Get workingSet of IE
	 * @throws Exception 
	 */
	String processName;
	String workingset;
	//records is the total counts of data in excel row
	//items is the number of the object array to record the data of one cell of excel row
	int records = 0;
	int items = 0;
	int currenttime = 20;
	List<String> memory = new ArrayList<String>();

	public void setList() throws Exception{
		if(processName == "Exe.Name.ct=chrome" ||processName == "Exe.Name.ct=iexplore" ||processName == "Exe.Name.ct=firefox"){
			BrowserMemory bm = new BrowserMemory();
			workingset = bm.getMemory(processName,1);
		}
		if(currenttime%ToolProperty.getRecordInterval() == 0){
			memory.add(workingset);	
		}	
	}
	
	public List<String> getWorkingSet() throws Exception{
		if(ToolProperty.totalTimes == currenttime){
			System.out.println(memory);
			return memory;
		}else{
			return null;
		}
	}
}