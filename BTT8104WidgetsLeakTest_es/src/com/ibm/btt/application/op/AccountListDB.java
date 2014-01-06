package com.ibm.btt.application.op;


import com.ibm.btt.base.DSEInvalidArgumentException;
import com.ibm.btt.base.DSEObjectNotFoundException;
import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.base.KeyedCollection;

public class AccountListDB
{
	private static IndexedCollection allAccountList = null;
	private final static int rowsPerPage = 10;
	private static int totalNum = 0;
	private static int totalPage = 0;
	
	private AccountListDB(IndexedCollection accountInfoList)
	{
		allAccountList = accountInfoList;
	}
	
	public synchronized static void update(IndexedCollection accountInfoList, int pageNum) throws Exception
	{
		int num = rowsPerPage;
		
		if(pageNum == totalPage)
			num = totalNum - totalPage*rowsPerPage;
		
		// init a new DB
		for(int i = 0; i < num; i ++)
		{
			allAccountList.setValueAt(((pageNum-1)*rowsPerPage + i) + ".d1", accountInfoList.getValueAt(i + ".d1"));
			allAccountList.setValueAt(((pageNum-1)*rowsPerPage + i) + ".d2", accountInfoList.getValueAt(i + ".d2"));
			allAccountList.setValueAt(((pageNum-1)*rowsPerPage + i) + ".d3", accountInfoList.getValueAt(i + ".d3"));
			
		}
	}
	
	public synchronized static void getAllAccountList(int pageNum, IndexedCollection accountInfoList) throws DSEObjectNotFoundException, DSEInvalidArgumentException
	{		
		int num = rowsPerPage;
		
		if(pageNum == totalPage)
			num = totalNum - (totalPage-1)*rowsPerPage;

		accountInfoList.removeAll();
		//---------------------------------------------------for change
		for(int i = 0; i < num; i ++)
		{
			accountInfoList.addElement(allAccountList.getElementAt((pageNum-1)*rowsPerPage + i));
		}
	}
	
	public synchronized static IndexedCollection init(IndexedCollection accountInfoList)
	{
		if(allAccountList == null)
		{
			totalNum = accountInfoList.size();
			totalPage = accountInfoList.size()/rowsPerPage;
			if(accountInfoList.size()%rowsPerPage > 0)
				totalPage ++;
			
			allAccountList = accountInfoList;
		}
		
		return allAccountList;
	}

	public synchronized static void getAllAccountList(String new_accountType, String new_tradeTime, IndexedCollection searchAccountData) throws DSEObjectNotFoundException, DSEInvalidArgumentException
	{
		searchAccountData.removeAll();
		
		// TODO Auto-generated method stub
		for(int i = 0; i < allAccountList.size(); i ++)
		{			
			String d1 = (String) allAccountList.getValueAt(i + ".d1");
			String d2 = (String) allAccountList.getValueAt(i + ".d2");
			
			if(d1 == new_accountType && d2 == new_tradeTime)
			{
				searchAccountData.addElement(allAccountList.getElementAt(i));
			}
		}
	}

	public synchronized static void update(KeyedCollection accountInfoRecord) throws DSEObjectNotFoundException, DSEInvalidArgumentException
	{
		// TODO Auto-generated method stub
		for(int i = 0; i < allAccountList.size(); i++)
		{
			
				allAccountList.setValueAt(i + ".d1", accountInfoRecord.getValueAt("d1"));
				allAccountList.setValueAt(i + ".d2", accountInfoRecord.getValueAt("d2"));
				allAccountList.setValueAt(i + ".d3", accountInfoRecord.getValueAt("d3"));
				
				
				break;
			}
		}
	

	public static Integer Size() {
		return allAccountList.size();
	}
}