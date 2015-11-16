package com.ibm.btt.tool.memory;

import java.util.ArrayList;
import java.util.List;

import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.ptql.ProcessFinder;

import com.ibm.btt.tool.common.ToolProperty;

public class MemoryMap{
	/**
	 * Get memory leak of IE
	 * @throws Exception 
	 */
	Sigar sigar = new Sigar();
	List<String> memory = new ArrayList<String>();
		
	public void putMemoryInList(int currentCount) throws Exception{
		String workingset = null;
		long[] pids = ProcessFinder.find(sigar, "Exe.Name.ct=iexplore");
		ProcMem bm = new ProcMem();
		bm.gather(sigar, pids[1]);
		workingset = Long.toString(bm.getResident()/1024);
		
		if(currentCount%ToolProperty.recordInterval == 0){
			memory.add(workingset);	
		}	
	}
	
	public List<String> getCurrentAllResults(){
		return memory;
	}
	
	public String getLatestMemory() throws Exception{
		return memory.get(memory.size());
	}
}