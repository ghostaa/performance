package com.ibm.btt.tool.common;

public class ToolProperty {
	public static final String CONFIG_PATH="config/config.json";
	public static final String ALL_WIDGETS="config/all_widgets.json";
	public static final String RECORD_SINGLE="recordSingle";
	public static final String RECORD_BATCH="recordBatch";
	
	public static int totalTimes; //�ܴ���(�ܹ���¼���ٴ�����)
	public static int recordInterval;//��¼���(������ٴμ�¼һ��)
	public static int waitTime;//�ȴ�ʱ��(��������һ��)
	public static String url;//��ַ
	public static String filePath;//�ļ����·��
	public static Boolean singleThread;//�Ƿ��ǵ��߳�
	public static String reportName="BTTBrowserPerformanceReport.xlsx";
	public static String recordType;
	public static String widgetIdOnTool;
}
