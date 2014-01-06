package com.ibm.btt.application.op;

import com.ibm.btt.base.BTTServerOperation;
import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.base.KeyedCollection;

public class AddTableData extends BTTServerOperation {

	public void execute() throws Exception {
		System.out.println("====== AddTable data Start =====");
		try {
			IndexedCollection itest=(IndexedCollection)getContext().getElementAt("TableList");
			KeyedCollection kColl = null;
            for(int i=0;i<=3;i++){
            	kColl = (KeyedCollection) itest.createElement(true);
				
				kColl.setValueAt("d1", "dd1_value"+i);
				kColl.setValueAt("d2", "bd2_value"+i);
				kColl.setValueAt("d3", "cd3_value"+i);
				itest.addElement(kColl);
            	if(i==1){
            		kColl = (KeyedCollection) itest.createElement(true);
    				
    				kColl.setValueAt("d1", "cd1_value"+i);
    				kColl.setValueAt("d2", "ad2_value"+i);
    				kColl.setValueAt("d3", "bd3_value"+i);
    				itest.addElement(kColl);
            		
            	}
            	if(i==2){
            		kColl = (KeyedCollection) itest.createElement(true);
    				kColl.setValueAt("d1", "ad1_value"+i);
    				kColl.setValueAt("d2", "bd2_value"+i);
    				kColl.setValueAt("d3", "ad3_value"+i);
    				itest.addElement(kColl);
            		
            	}
				
				
				
            }
            
            
            
			super.execute();
		} catch (Exception ex) {
			
		}
		System.out.println("====== End =====");
	}

}