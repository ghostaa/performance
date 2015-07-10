import java.util.ArrayList;
import java.util.List;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.cmd.Ps;


public class IEPerformance {

	private Sigar sigar = new Sigar();
	
	private StringBuilder builder;
	
	//获取进程的相关信息
	private  List<ProcessInfo> getProcessInfo() throws InterruptedException, SigarException{
		long iePid = 16756;
		Ps ps = new Ps();
		List<ProcessInfo> processInfos = new ArrayList<ProcessInfo>();
		try {
			long[] pids = sigar.getProcList();
			for(long pid : pids){
				List<String> list = ps.getInfo(sigar, pid);
				ProcessInfo info = new ProcessInfo();
				for(int i = 0; i <= list.size(); i++){
					switch(i){
						case 0 : info.setPid(list.get(0)); break;
						case 1 : info.setUser(list.get(1)); break;
						case 2 : info.setStartTime(list.get(2)); break;
						case 3 : info.setMemSize(list.get(3)); break;
						case 4 : info.setMemUse(list.get(4)); break;
						case 5 : info.setMemhare(list.get(5)); break;
						case 6 : info.setState(list.get(6)); break;
						case 7 : info.setCpuTime(list.get(7)); break;
						case 8 : info.setName(list.get(8)); 
						System.out.println(pid);
						System.out.println(list.get(8));
						if(list.get(8).contains("IEXPLORE.EXE")){
							//iePid=pid;
						}break;
					}
				}
				processInfos.add(info);
			}
		} catch (SigarException e) {
			e.printStackTrace();
		}
		for(int j=0;j<20;j++){
			Thread.sleep(2000);
			List<String> list= ps.getInfo(sigar, iePid);
			System.out.println("==========");
			System.out.println(list.get(3));
			System.out.println(list.get(4));
		}
		
		
		
		return processInfos;
	}
	
		
	public static void main(String[] args) throws InterruptedException, SigarException {
		IEPerformance info = new IEPerformance(); 
		List<ProcessInfo> processInfos = info.getProcessInfo();
		System.out.println();
	}

}



