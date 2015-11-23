package com.ibm.btt.tool.memory;

import java.util.ArrayList;
import java.util.List;

import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.ptql.ProcessFinder;

import com.ibm.btt.tool.common.ToolProperty;

public class MemoryMap{
	/**
	 * Get memory leak of IE
	 * @throws Exception 
	 */
	Sigar sigar;
	ProcMem bm;
	long[] pids ;
	List<Memory> memorys;
	
	public MemoryMap() {
		sigar = new Sigar();
		bm = new ProcMem();
		memorys = new ArrayList<Memory>();
		 try {
			 pids = ProcessFinder.find(sigar, "Exe.Name.re=(?i)iexplore");
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void putMemoryInList(int currentCount) throws Exception{
		String workingset = null;
		bm.gather(sigar, pids[pids.length-1]);
		workingset = Long.toString(bm.getResident()/1024);
		Memory memory = new Memory();
		memory.setWorkingset(workingset);
		memory.setCurrentCount(currentCount);
		memorys.add(memory);	
	}
	
	public List<Memory> getCurrentAllResults(){
		return memorys;
	}
	
	public Memory getLatestMemory() throws Exception{
		if (memorys.size()==0) {
			return null;
		}else {
			return memorys.get(memorys.size()-1);
		}
	}
	/*public static void main(String[] args){
		ToolProperty.recordInterval=1;
		MemoryMap memoryMap=new MemoryMap();
		try {
			for (int i = 0; i < 5; i++) {
				
				memoryMap.putMemoryInList(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(memoryMap.getCurrentAllResults().size());
	}*/
}