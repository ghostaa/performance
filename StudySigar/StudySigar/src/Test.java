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
		
		// CPU��������λ������
		int cpuLength = sigar.getCpuInfoList().length;
		print(cpuLength);

		// CPU����������λ��HZ����CPU�������Ϣ
		CpuInfo infos[] = sigar.getCpuInfoList();
		for (int i = 0; i < infos.length; i++) {// �����ǵ���CPU���Ƕ�CPU������
			CpuInfo info = infos[i];
			print("mhz=" + info.getMhz());// CPU������MHz
			print("vendor=" + info.getVendor());// ���CPU���������磺Intel
			print("model=" + info.getModel());// ���CPU������磺Celeron
			print("cache size=" + info.getCacheSize());// ����洢������
		}

		/** CPU���û�ʹ������ϵͳʹ��ʣ�������ܵ�ʣ�������ܵ�ʹ��ռ�����ȣ���λ��100%�� **/
		

		// ��ʽ���������ǵ���CPU���Ƕ�CPU������
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
		
		// �����ڴ���Ϣ
		Mem mem = sigar.getMem();
		// �ڴ�����
		print("Total = " + mem.getTotal() / 1024L / 1024 + "M av");
		// ��ǰ�ڴ�ʹ����
		print("Used = " + mem.getUsed() / 1024L / 1024 + "M used");
		// ��ǰ�ڴ�ʣ����
		print("Free = " + mem.getFree() / 1024L / 1024 + "M free");

		// ϵͳҳ���ļ���������Ϣ
		Swap swap = sigar.getSwap();
		// ����������
		print("Total = " + swap.getTotal() / 1024L + "K av");
		// ��ǰ������ʹ����
		print("Used = " + swap.getUsed() / 1024L + "K used");
		// ��ǰ������ʣ����
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

		// ȡ��ǰ����ϵͳ����Ϣ
		OperatingSystem OS = OperatingSystem.getInstance();
		// ����ϵͳ�ں������磺 386��486��586��x86
		print("OS.getArch() = " + OS.getArch());
		print("OS.getCpuEndian() = " + OS.getCpuEndian());//
		print("OS.getDataModel() = " + OS.getDataModel());//
		// ϵͳ����
		print("OS.getDescription() = " + OS.getDescription());
		print("OS.getMachine() = " + OS.getMachine());//
		// ����ϵͳ����
		print("OS.getName() = " + OS.getName());
		print("OS.getPatchLevel() = " + OS.getPatchLevel());//
		// ����ϵͳ������
		print("OS.getVendor() = " + OS.getVendor());
		// ��������
		System.out
				.println("OS.getVendorCodeName() = " + OS.getVendorCodeName());
		// ����ϵͳ����
		print("OS.getVendorName() = " + OS.getVendorName());
		// ����ϵͳ��������
		print("OS.getVendorVersion() = " + OS.getVendorVersion());
		// ����ϵͳ�İ汾��
		print("OS.getVersion() = " + OS.getVersion());

		// ȡ��ǰϵͳ���̱��е��û���Ϣ
		Who who[] = sigar.getWhoList();
		if (who != null && who.length > 0) {
			for (int i = 0; i < who.length; i++) {
				print("\n~~~~~~~~~" + String.valueOf(i) + "~~~~~~~~~");
				Who _who = who[i];
				print("getDevice() = " + _who.getDevice());
				print("getHost() = " + _who.getHost());
				print("getTime() = " + _who.getTime());
				// ��ǰϵͳ���̱��е��û���
				print("getUser() = " + _who.getUser());
			}
		}
	}
	
	public void getDiskInfo() throws SigarException{
		print("===========getDiskInfo===================");
		FileSystem fslist[] = sigar.getFileSystemList();
		String dir = System.getProperty("user.home");// ��ǰ�û��ļ���·��
		print(dir + "   " + fslist.length);
		for (int i = 0; i < fslist.length; i++) {
			print("\n~~~~~~~~~~" + i + "~~~~~~~~~~");
		FileSystem fs = fslist[i];
		// �������̷�����
		print("fs.getDevName() = " + fs.getDevName());
		// �������̷�����
		print("fs.getDirName() = " + fs.getDirName());
		print("fs.getFlags() = " + fs.getFlags());//
		// �ļ�ϵͳ���ͣ����� FAT32��NTFS
		print("fs.getSysTypeName() = " + fs.getSysTypeName());
		// �ļ�ϵͳ�����������籾��Ӳ�̡������������ļ�ϵͳ��
		print("fs.getTypeName() = " + fs.getTypeName());
		// �ļ�ϵͳ����
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
		case 0: // TYPE_UNKNOWN ��δ֪
			break;
		case 1: // TYPE_NONE
			break;
		case 2: // TYPE_LOCAL_DISK : ����Ӳ��
			// �ļ�ϵͳ�ܴ�С
			print(" Total = " + usage.getTotal() + "KB");
			// �ļ�ϵͳʣ���С
			print(" Free = " + usage.getFree() + "KB");
			// �ļ�ϵͳ���ô�С
			print(" Avail = " + usage.getAvail() + "KB");
			// �ļ�ϵͳ�Ѿ�ʹ����
			print(" Used = " + usage.getUsed() + "KB");
			double usePercent = usage.getUsePercent() * 100D;
			// �ļ�ϵͳ��Դ��������
			print(" Usage = " + usePercent + "%");
			break;
		case 3:// TYPE_NETWORK ������
			break;
		case 4:// TYPE_RAM_DISK ������
			break;
		case 5:// TYPE_CDROM ������
			break;
		case 6:// TYPE_SWAP ��ҳ�潻��
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

		// ȡ����ǰ������IP��ַ
		String address = null;
		try {
			address = InetAddress.getLocalHost().getHostAddress();
			// û�г����쳣��������ȡ����IPʱ�����ȡ���Ĳ�������ѭ�ص�ַʱ�ͷ���
			// ������ͨ��Sigar���߰��еķ�������ȡ
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

		// ȡ����ǰ������MAC��ַ
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

		// ��ȡ������������Ϣ
		String ifNames[] = sigar.getNetInterfaceList();
		for (int i = 0; i < ifNames.length; i++) {
			String name = ifNames[i];
			NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
			print("\nname = " + name);// �����豸��
			print("Address = " + ifconfig.getAddress());// IP��ַ
			print("Netmask = " + ifconfig.getNetmask());// ��������
			if ((ifconfig.getFlags() & 1L) <= 0L) {
				print("!IFF_UP...skipping getNetInterfaceStat");
				continue;
			}
			try {
				NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
				print("RxPackets = " + ifstat.getRxPackets());// ���յ��ܰ�����
				print("TxPackets = " + ifstat.getTxPackets());// ���͵��ܰ�����
				print("RxBytes = " + ifstat.getRxBytes());// ���յ������ֽ���
				print("TxBytes = " + ifstat.getTxBytes());// ���͵����ֽ���
				print("RxErrors = " + ifstat.getRxErrors());// ���յ��Ĵ������
				print("TxErrors = " + ifstat.getTxErrors());// �������ݰ�ʱ�Ĵ�����
				print("RxDropped = " + ifstat.getRxDropped());// ����ʱ�����İ���
				print("TxDropped = " + ifstat.getTxDropped());// ����ʱ�����İ���
			} catch (SigarNotImplementedException e) {
			} catch (SigarException e) {
				print(e.getMessage());
			}
		}

		// һЩ��������Ϣ
		for (int i = 0; i < ifaces.length; i++) {
			NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[i]);
			if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress())
					|| (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
					|| NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
				continue;
			}
			print("cfg.getAddress() = " + cfg.getAddress());// IP��ַ
			print("cfg.getBroadcast() = " + cfg.getBroadcast());// ���ع㲥��ַ
			print("cfg.getHwaddr() = " + cfg.getHwaddr());// ����MAC��ַ
			print("cfg.getNetmask() = " + cfg.getNetmask());// ��������
			System.out
					.println("cfg.getDescription() = " + cfg.getDescription());// ����������Ϣ
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
