package com.ibm.btt.tool.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ibm.btt.tool.common.ToolProperty;
import com.ibm.btt.tool.memory.MemoryMap;

public class WritePerfInfoIntoExcel {

	/**
	 * @param args
	 * @throws Exception 
	 * sheet index is a parameter of the function - writePerformanceIntoExcel
	 * get the results and save in a map, when write in excel ,iterator the map
	 */
	public static void main(String[] args) throws Exception {
		
		List <String> empinfo = new ArrayList<String>();
		MemoryMap memory = new MemoryMap();
		empinfo = memory.getCurrentAllResults();
		/*String[] color = {"BLUE","BRIGHT_GREEN","CORAL","CORNFLOWER_BLUE","GOLD","GREEN",
				"LIGHT_GREEN","LIGHT_ORANGE","LIGHT_YELLOW","ORANGE","PINK","RED","ROSE","ROYAL_BLUE","SEA_GREEN","SKY_BLUE","YELLOW"};*/	
		FileInputStream fis = new FileInputStream(
				new File("E:/IEperformance.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet spreadsheet = workbook.getSheetAt(0);
		
		//The first title
		XSSFRow row1 = spreadsheet.createRow(1);
		XSSFCell cell1 = row1.createCell(0);
		XSSFCellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(10);
		font.setColor(HSSFColor.BLUE.index);
		style.setFont(font);
		style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		cell1.setCellValue("8200 (IE11-Reload) Test Results");
		cell1.setCellStyle(style);
		
		//The second is count,The third is results
		style = workbook.createCellStyle();
		style.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		XSSFRow rowCount = null;
		XSSFRow rowWs = null;
		int rowid = 2;
		int cellcount = 0;
		for(int i=0; i<empinfo.size();i++){
			rowCount = spreadsheet.createRow(3);
			rowWs = spreadsheet.createRow(4);
			int cellid=0;
			Cell cellCount = rowCount.createCell(cellid++);
			cellcount = cellid;
			Cell cellWs = rowWs.createCell(cellid++);
			cellCount.setCellValue(empinfo.get(i));
			cellWs.setCellValue(i*ToolProperty.recordInterval);
			cellCount.setCellStyle(style);
			cellWs.setCellStyle(style);
			
		}
		//set the backgroundcolor of row1
		XSSFCellStyle bgstyle = workbook.createCellStyle();
		//bgstyle.setFillBackgroundColor(HSSFColor.LEMON_CHIFFON.index);
		bgstyle.setFillBackgroundColor(HSSFColor.BLUE.index);
		bgstyle.setFillPattern( XSSFCellStyle.THIN_HORZ_BANDS);
		cell1.setCellStyle(bgstyle);
		for(int i = 1; i< cellcount; i++){
			Cell cell = row1.createCell(i);
			cell.setCellStyle(bgstyle);
		}
		//Get the first value and last value of the workingSet row
		String firstvalue = "";
		String lastvalue = "";
		firstvalue = rowWs.getCell(1).getStringCellValue();
		short a = rowWs.getLastCellNum();
		lastvalue = rowWs.getCell(a-1).getStringCellValue();
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
		FileOutputStream out = new FileOutputStream(("E:/IEperformance.xlsx"));
		workbook.write(out);
		out.close();
		System.out.println("IE performance record successfully!");
	}

}
