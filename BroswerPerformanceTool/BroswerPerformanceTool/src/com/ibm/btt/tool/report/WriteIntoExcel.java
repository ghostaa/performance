package com.ibm.btt.tool.report;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.ibm.btt.tool.common.ToolProperty;
import com.ibm.btt.tool.memory.Memory;

public class WriteIntoExcel {

	/**
	 * This is the method of write memory leak into excel
	 */
	String filePath = "";
	//public static String[] buttonId = {"A1_LinkId","B1_linkid","C1_linkid"};
	String recordName = "8203 (IE11-Reload) Test Results";
	XSSFWorkbook workbook = new XSSFWorkbook();
	private boolean flag=true;
	//The main method of write into excel
	public void intoExcel(List<Memory> empinfo, String button){
		writeOut(setCellValue(empinfo,createSheet(workbook,button)));
		System.out.println("IE performance record successfully!");
	}
	//create sheet as the button id of performance project
	public XSSFSheet createSheet(XSSFWorkbook workbook,String button){
		XSSFSheet spreadsheet = workbook.createSheet(button);
		return spreadsheet;
	}
	//put workingset into sheet
	public XSSFWorkbook setCellValue(List<Memory> empinfo,XSSFSheet spreadsheet){
		XSSFRow row1 = spreadsheet.createRow(1);
		XSSFCell cell1 = row1.createCell(0);
		cell1.setCellValue(recordName);
		cell1.setCellStyle(setStyle());
		//The second is count,The third is results
		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		XSSFRow row2 = spreadsheet.createRow(2);
		XSSFRow row3 = spreadsheet.createRow(3);
		Cell cell2 = row2.createCell(0);
		cell2.setCellValue("Count");
		Cell cell3 = row3.createCell(0);
		cell3.setCellValue("WorkingSet");
		for(int i=0 ; i< empinfo.size(); i++){
			cell2 = row2.createCell(i+1);
			cell2.setCellValue(empinfo.get(i).getCurrentCount());
			cell2.setCellStyle(style);
			cell3 = row3.createCell(i+1);
			cell3.setCellValue(empinfo.get(i).getWorkingset());
			cell3.setCellStyle(style);
		}
		//Get the first value and last value of the workingSet row
		String firstvalue = "";
		String lastvalue = "";
		firstvalue = row3.getCell(1).getStringCellValue();
		short a = row3.getLastCellNum();
		lastvalue = row3.getCell(a-1).getStringCellValue();
		System.out.println("================"+firstvalue);
		System.out.println("================"+lastvalue);
		//The forth is the subtract between the first result and the last result
		XSSFRow row4 = spreadsheet.createRow(4);
		XSSFCell cell = row4.createCell(0);
		cell.setCellValue("Substract");
		cell = row4.createCell(1);
		cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);		
		cell.setCellFormula(lastvalue+"-"+firstvalue);
		workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
		return workbook;
	}
	//output the workbook with workingset value
	public void writeOut(XSSFWorkbook workbook){
		if(ToolProperty.filePath == null || "".equals(ToolProperty.filePath)){
			filePath = "C:/"+ToolProperty.reportName;
		}else{
			filePath = ToolProperty.filePath+ "\"" +ToolProperty.reportName;
		}
		try {
			FileOutputStream out = null;
			if(flag){
				out = new FileOutputStream(new File(filePath));
				flag=false;
			}else{
				out = new FileOutputStream(filePath);
			}
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//set the cell value
	public XSSFCellStyle setStyle(){
		XSSFCellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(10);
		font.setColor(HSSFColor.BLACK.index);
		style.setFont(font);
		style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		return style;
	}
	/*public static void main(String[] args) {
		List<Memory> empinfo = new ArrayList<Memory>();
		Memory m = new Memory();
		m.setCurrentCount(0);
		m.setWorkingset("20");
		empinfo.add(m);
		Memory m1 = new Memory();
		m1.setCurrentCount(10);
		m1.setWorkingset("40");
		empinfo.add(m1);
		Memory m2 = new Memory();
		m2.setCurrentCount(20);
		m2.setWorkingset("60");
		empinfo.add(m2);
		WriteIntoExcel we = new WriteIntoExcel();
		for(int i = 0; i< 3;i++){
			we.intoExcel(empinfo,buttonId[i]);
		}
	}*/
}
