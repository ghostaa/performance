package com.ibm.btt.tool.memory;

import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.ptql.ProcessFinder;

public class BrowserMemory {

	/**
	 * Get the WorkingSet of browser
	 */
	public String getMemory(String processName,int i) throws Exception{
		Sigar sigar = new Sigar();
		long[] pids = ProcessFinder.find(sigar, processName);
		ProcMem memory = new ProcMem();
		memory.gather(sigar, pids[i]);
		String workingset = Long.toString(memory.getResident()/1024);//working set
		return workingset;
	}
}
