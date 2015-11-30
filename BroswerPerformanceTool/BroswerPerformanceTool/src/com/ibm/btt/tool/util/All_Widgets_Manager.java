package com.ibm.btt.tool.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;
import com.ibm.btt.tool.common.JSONObjectKey;
import com.ibm.btt.tool.common.ToolProperty;

public class All_Widgets_Manager {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		All_Widgets_Manager all_Widgets_Manager = new All_Widgets_Manager();
//		all_Widgets_Manager.loadConfigFile();
		JSONArray jsonArray =new JSONArray();
		JSONObject jsonObject= new JSONObject();
		jsonObject.put("linktext", "B1_Anchor");
		jsonObject.put("widgetid", "Anchor_B_1_btn_reload");
		jsonArray.put(jsonObject);
		all_Widgets_Manager.saveAllWidgetsFileAsJSONArray(jsonArray);
	}
	public JSONObject loadAllWidgetsFileAsJSONObject(){
		JSONObject jsonObject=null;
		try {
		File file=new File(ToolProperty.ALL_WIDGETS);
        if(!file.exists()||file.isDirectory())
				throw new FileNotFoundException();
			
        BufferedReader br=new BufferedReader(new FileReader(file));
        String temp=null;
        StringBuffer sb=new StringBuffer();
        temp=br.readLine();
        while(temp!=null){
            sb.append(temp+" ");
            temp=br.readLine();
        }
        jsonObject = new JSONObject(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonObject;
	}
	
	public void saveAllWidgetsFileAsJSONArray(JSONArray jsonArray) throws IOException{
		File file=new File(ToolProperty.ALL_WIDGETS);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,false);        
        out.write( jsonArray.toString().getBytes("GB18030"));
        out.close();
    }
}
