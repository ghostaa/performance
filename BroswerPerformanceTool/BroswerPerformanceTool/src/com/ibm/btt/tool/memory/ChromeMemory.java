package com.ibm.btt.tool.memory;

public class ChromeMemory {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String processName = "Exe.Name.ct=chrome";
		BrowserMemory bm = new BrowserMemory();
		String workingset = bm.getMemory(processName,1);
		System.out.println(workingset);
	}
}
