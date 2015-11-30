package com.ibm.btt.tool.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;
import com.ibm.btt.tool.common.JSONObjectManager;
import com.ibm.btt.tool.common.ToolProperty;

public class ConfigManager {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ConfigManager configManager = new ConfigManager();
		//configManager.loadConfigFile();
	}
	public JSONObject loadConfigFileAsJSONObject(){
		JSONObject jsonObject=null;
		try {
		File file=new File(ToolProperty.CONFIG_PATH);
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
	
	public void saveConfigFileAsJSONObject(JSONObject jsonObject) throws IOException{
		File file=new File(ToolProperty.CONFIG_PATH);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,false);        
        out.write( jsonObject.toString().getBytes("GB18030"));
        out.close();
    }
}
