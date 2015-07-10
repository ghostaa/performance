import java.net.InetAddress;
import java.net.UnknownHostException;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarNotImplementedException;
import org.hyperic.sigar.Swap;
import org.hyperic.sigar.Who;


public class Test {
	Sigar sigar = new Sigar();
	/**
	 * @param args
	 * @throws SigarException 
	 * @author ghost
	 * Please use GB18030 charset
	 */
	public static void main(String[] args) throws SigarException {
		// TODO Auto-generated method stub
		Test t= new Test();
		t.getCpuInfo();
		t.getMem();
		t.getOSInfo();
		t.getDiskInfo();
		t.getNetInfo();
		
	}

	public void getCpuInfo() throws SigarException{
		print("===========getCpuInfo===================");
		
		// CPU数量（单位：个）
		int cpuLength = sigar.getCpuInfoList().length;
		print(cpuLength);

		// CPU的总量（单位：HZ）及CPU的相关信息
		CpuInfo infos[] = sigar.getCpuInfoList();
		for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
			CpuInfo info = infos[i];
			print("mhz=" + info.getMhz());// CPU的总量MHz
			print("vendor=" + info.getVendor());// 获得CPU的卖主，如：Intel
			print("model=" + info.getModel());// 获得CPU的类别，如：Celeron
			print("cache size=" + info.getCacheSize());// 缓冲存储器数量
		}

		/** CPU的用户使用量、系统使用剩余量、总的剩余量、总的使用占用量等（单位：100%） **/
		

		// 方式二，不管是单块CPU还是多CPU都适用
		CpuPerc cpuList[] = null;
		try {
			cpuList = sigar.getCpuPercList();
		} catch (SigarException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < cpuList.length; i++) {
			// printCpuPerc(cpuList[i]);
		}


	}
	public void getMem() throws SigarException{
		print("===========getMem===================");
		
		// 物理内存信息
		Mem mem = sigar.getMem();
		// 内存总量
		print("Total = " + mem.getTotal() / 1024L / 1024 + "M av");
		// 当前内存使用量
		print("Used = " + mem.getUsed() / 1024L / 1024 + "M used");
		// 当前内存剩余量
		print("Free = " + mem.getFree() / 1024L / 1024 + "M free");

		// 系统页面文件交换区信息
		Swap swap = sigar.getSwap();
		// 交换区总量
		print("Total = " + swap.getTotal() / 1024L + "K av");
		// 当前交换区使用量
		print("Used = " + swap.getUsed() / 1024L + "K used");
		// 当前交换区剩余量
		print("Free = " + swap.getFree() / 1024L + "K free");
	}
	
	public void getOSInfo() throws SigarException{
		print("===========getOSInfo===================");
		String hostname;
		try {
			hostname = InetAddress.getLocalHost().getHostName();
		} catch (Exception exc) {
			try {
				hostname = sigar.getNetInfo().getHostName();
			} catch (SigarException e) {
				hostname = "localhost.unknown";
			} finally {
				sigar.close();
			}
		}
		print(hostname);

		// 取当前操作系统的信息
		OperatingSystem OS = OperatingSystem.getInstance();
		// 操作系统内核类型如： 386、486、586等x86
		print("OS.getArch() = " + OS.getArch());
		print("OS.getCpuEndian() = " + OS.getCpuEndian());//
		print("OS.getDataModel() = " + OS.getDataModel());//
		// 系统描述
		print("OS.getDescription() = " + OS.getDescription());
		print("OS.getMachine() = " + OS.getMachine());//
		// 操作系统类型
		print("OS.getName() = " + OS.getName());
		print("OS.getPatchLevel() = " + OS.getPatchLevel());//
		// 操作系统的卖主
		print("OS.getVendor() = " + OS.getVendor());
		// 卖主名称
		System.out
				.println("OS.getVendorCodeName() = " + OS.getVendorCodeName());
		// 操作系统名称
		print("OS.getVendorName() = " + OS.getVendorName());
		// 操作系统卖主类型
		print("OS.getVendorVersion() = " + OS.getVendorVersion());
		// 操作系统的版本号
		print("OS.getVersion() = " + OS.getVersion());

		// 取当前系统进程表中的用户信息
		Who who[] = sigar.getWhoList();
		if (who != null && who.length > 0) {
			for (int i = 0; i < who.length; i++) {
				print("\n~~~~~~~~~" + String.valueOf(i) + "~~~~~~~~~");
				Who _who = who[i];
				print("getDevice() = " + _who.getDevice());
				print("getHost() = " + _who.getHost());
				print("getTime() = " + _who.getTime());
				// 当前系统进程表中的用户名
				print("getUser() = " + _who.getUser());
			}
		}
	}
	
	public void getDiskInfo() throws SigarException{
		print("===========getDiskInfo===================");
		FileSystem fslist[] = sigar.getFileSystemList();
		String dir = System.getProperty("user.home");// 当前用户文件夹路径
		print(dir + "   " + fslist.length);
		for (int i = 0; i < fslist.length; i++) {
			print("\n~~~~~~~~~~" + i + "~~~~~~~~~~");
		FileSystem fs = fslist[i];
		// 分区的盘符名称
		print("fs.getDevName() = " + fs.getDevName());
		// 分区的盘符名称
		print("fs.getDirName() = " + fs.getDirName());
		print("fs.getFlags() = " + fs.getFlags());//
		// 文件系统类型，比如 FAT32、NTFS
		print("fs.getSysTypeName() = " + fs.getSysTypeName());
		// 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
		print("fs.getTypeName() = " + fs.getTypeName());
		// 文件系统类型
		print("fs.getType() = " + fs.getType());
		FileSystemUsage usage = null;
		try {
			usage = sigar.getFileSystemUsage(fs.getDirName());
		} catch (SigarException e) {
			if (fs.getType() == 2)
				throw e;
			continue;
		}
		switch (fs.getType()) {
		case 0: // TYPE_UNKNOWN ：未知
			break;
		case 1: // TYPE_NONE
			break;
		case 2: // TYPE_LOCAL_DISK : 本地硬盘
			// 文件系统总大小
			print(" Total = " + usage.getTotal() + "KB");
			// 文件系统剩余大小
			print(" Free = " + usage.getFree() + "KB");
			// 文件系统可用大小
			print(" Avail = " + usage.getAvail() + "KB");
			// 文件系统已经使用量
			print(" Used = " + usage.getUsed() + "KB");
			double usePercent = usage.getUsePercent() * 100D;
			// 文件系统资源的利用率
			print(" Usage = " + usePercent + "%");
			break;
		case 3:// TYPE_NETWORK ：网络
			break;
		case 4:// TYPE_RAM_DISK ：闪存
			break;
		case 5:// TYPE_CDROM ：光驱
			break;
		case 6:// TYPE_SWAP ：页面交换
			break;
		}
		print(" DiskReads = " + usage.getDiskReads());
		print(" DiskWrites = " + usage.getDiskWrites());
		}
	}
	
	public void getNetInfo() throws SigarException{
		print("===========getNetInfo===================");
		try {
			print(InetAddress.getLocalHost().getCanonicalHostName());
		} catch (UnknownHostException e) {
			try {
				print(sigar.getFQDN());
			} catch (SigarException ex) {
			} finally {
				sigar.close();
			}
		}

		// 取到当前机器的IP地址
		String address = null;
		try {
			address = InetAddress.getLocalHost().getHostAddress();
			// 没有出现异常而正常当取到的IP时，如果取到的不是网卡循回地址时就返回
			// 否则再通过Sigar工具包中的方法来获取
			print(address);
			if (!NetFlags.LOOPBACK_ADDRESS.equals(address)) {
			}
		} catch (UnknownHostException e) {
			// hostname not in DNS or /etc/hosts
		}
		try {
			address = sigar.getNetInterfaceConfig().getAddress();
		} catch (SigarException e) {
			address = NetFlags.LOOPBACK_ADDRESS;
		} finally {
		}
		print(address);

		// 取到当前机器的MAC地址
		String[] ifaces = sigar.getNetInterfaceList();
		String hwaddr = null;
		for (int i = 0; i < ifaces.length; i++) {
			NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[i]);
			if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress())
					|| (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
					|| NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
				continue;
			}
			hwaddr = cfg.getHwaddr();
			print(hwaddr);
			// break;
		}
		print(hwaddr != null ? hwaddr : null);

		// 获取网络流量等信息
		String ifNames[] = sigar.getNetInterfaceList();
		for (int i = 0; i < ifNames.length; i++) {
			String name = ifNames[i];
			NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
			print("\nname = " + name);// 网络设备名
			print("Address = " + ifconfig.getAddress());// IP地址
			print("Netmask = " + ifconfig.getNetmask());// 子网掩码
			if ((ifconfig.getFlags() & 1L) <= 0L) {
				print("!IFF_UP...skipping getNetInterfaceStat");
				continue;
			}
			try {
				NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
				print("RxPackets = " + ifstat.getRxPackets());// 接收的总包裹数
				print("TxPackets = " + ifstat.getTxPackets());// 发送的总包裹数
				print("RxBytes = " + ifstat.getRxBytes());// 接收到的总字节数
				print("TxBytes = " + ifstat.getTxBytes());// 发送的总字节数
				print("RxErrors = " + ifstat.getRxErrors());// 接收到的错误包数
				print("TxErrors = " + ifstat.getTxErrors());// 发送数据包时的错误数
				print("RxDropped = " + ifstat.getRxDropped());// 接收时丢弃的包数
				print("TxDropped = " + ifstat.getTxDropped());// 发送时丢弃的包数
			} catch (SigarNotImplementedException e) {
			} catch (SigarException e) {
				print(e.getMessage());
			}
		}

		// 一些其他的信息
		for (int i = 0; i < ifaces.length; i++) {
			NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[i]);
			if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress())
					|| (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
					|| NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
				continue;
			}
			print("cfg.getAddress() = " + cfg.getAddress());// IP地址
			print("cfg.getBroadcast() = " + cfg.getBroadcast());// 网关广播地址
			print("cfg.getHwaddr() = " + cfg.getHwaddr());// 网卡MAC地址
			print("cfg.getNetmask() = " + cfg.getNetmask());// 子网掩码
			System.out
					.println("cfg.getDescription() = " + cfg.getDescription());// 网卡描述信息
			print("cfg.getType() = " + cfg.getType());//
			System.out
					.println("cfg.getDestination() = " + cfg.getDestination());
			print("cfg.getFlags() = " + cfg.getFlags());//
			print("cfg.getMetric() = " + cfg.getMetric());
			print("cfg.getMtu() = " + cfg.getMtu());
			print("cfg.getName() = " + cfg.getName());
		}
	}
	
	public void print(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
	}
	public void print(int string) {
		// TODO Auto-generated method stub
		System.out.println(string);
	}
}
