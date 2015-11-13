package com.ibm.btt.tool.memory;

public class IEMemory {

	/**
	 * Get workingSet of IE
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String processName = "Exe.Name.ct=iexplore";
		BrowserMemory bm = new BrowserMemory();
		String workingset = bm.getMemory(processName,1);
		System.out.println(workingset);
	}

}
