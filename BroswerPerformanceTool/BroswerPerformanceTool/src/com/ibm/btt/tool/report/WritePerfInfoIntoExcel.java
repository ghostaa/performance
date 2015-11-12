package com.ibm.btt.tool.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
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

public class WritePerfInfoIntoExcel {

	/**
	 * @param args
	 * @throws Exception 
	 * sheet index is a parameter of the function - writePerformanceIntoExcel
	 * get the results and save in a map, when write in excel ,iterator the map
	 */
	public static void main(String[] args) throws Exception {
		
		Map <String, Object[]> empinfo = new TreeMap<String,Object[]>();
		empinfo.put( "1", new Object[] {"Count","20", "40", "60","80","100"});
		empinfo.put( "2", new Object[] {"WorkingSet","123.56", "23.54", "456.65","655.76","435.999"});
		String[] color = {"BLUE","BRIGHT_GREEN","CORAL","CORNFLOWER_BLUE","GOLD","GREEN","LEMON_CHIFFON"
				,"LIGHT_BLUE","LIGHT_GREEN","LIGHT_ORANGE","LIGHT_TURQUOISE","LIGHT_YELLOW","LIME","MAROON",
				"ORANGE","ORCHID","PALE_BLUE","PINK","PLUM","RED","ROSE","ROYAL_BLUE",
				"SEA_GREEN","SKY_BLUE","TAN","TEAL","TURQUOISE","VIOLET","YELLOW"};
		
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
		font.setColor(HSSFColor.BLACK.index);
		style.setFont(font);
		style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		cell1.setCellValue("8200 (IE11-Reload) Test Results");
		cell1.setCellStyle(style);
		
		//The second is count,The third is results
		style = workbook.createCellStyle();
		style.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		XSSFRow row = null;
		Set < String > keyid = empinfo.keySet();
		int rowid = 2;
		int cellcount = 0;
		for(String key : keyid){
			row = spreadsheet.createRow(rowid++);
			Object [] objectArr = empinfo.get(key);
			int cellid=0;
			for(Object obj:objectArr){
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String)obj);
				cell.setCellStyle(style);
			}
			cellcount = cellid;
		}
		//set the backgroundcolor of row1
		XSSFCellStyle bgstyle = workbook.createCellStyle();
		//bgstyle.setFillBackgroundColor(HSSFColor.LEMON_CHIFFON.index);
		bgstyle.setFillBackgroundColor(HSSFColor.LEMON_CHIFFON.index);
		bgstyle.setFillPattern( XSSFCellStyle.ALIGN_CENTER);
		cell1.setCellStyle(bgstyle);
		for(int i = 1; i< cellcount; i++){
			Cell cell = row1.createCell(i);
			cell.setCellStyle(bgstyle);
		}
		//Get the first value and last value of the workingSet row
		String firstvalue = "";
		String lastvalue = "";
		firstvalue = row.getCell(1).getStringCellValue();
		short a = row.getLastCellNum();
		lastvalue = row.getCell(a-1).getStringCellValue();
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

/* Iterator<Cell> cellIterator = row.cellIterator();
 * 循环判断cell中是否有数据
 * while(cellIterator.hasNext()){
   Cell cellinrow3 = cellIterator.next();
   switch(cellinrow3.getCellType()){
  	case Cell.CELL_TYPE_NUMERIC:
	  lastvalue = ""+cellinrow3.getNumericCellValue();
	  break;
  	case Cell.CELL_TYPE_STRING:
	  lastvalue = cellinrow3.getStringCellValue();
	  break;
	}
}*/
