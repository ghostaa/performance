package com.ibm.btt.tool.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.ibm.btt.tool.memory.Memory;

public class WriteIntoExcel {

	/**
	 * @param args
	 */
//	if(fis.equals(null)){
	/*	}else{
			out = new FileOutputStream(("E:/IEperformance.xlsx"));
			workbook = (XSSFWorkbook)WorkbookFactory.create(fis);
			spreadsheet = workbook.getSheetAt(0);
		}*/
		/*FileOutputStream out = new FileOutputStream(("E:/IEperformance.xlsx"));
		if(out.equals(null)){
			out = new FileOutputStream(new File("E:/IEperformance.xlsx"));
		}*/
	
	XSSFWorkbook workbook = new XSSFWorkbook();
	public void createExcel(List<Memory> empinfo){
		try {
			FileOutputStream out = new FileOutputStream(new File("E:/IEperformance.xlsx"));
			XSSFSheet spreadsheet = workbook.createSheet("Test Results");
	
			//The first title
			XSSFRow row1 = spreadsheet.createRow(1);
			XSSFCell cell1 = row1.createCell(0);
			cell1.setCellValue("8200 (IE11-Reload) Test Results");
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
			
			//set the backgroundcolor of row1
			XSSFCellStyle bgstyle = workbook.createCellStyle();
			//bgstyle.setFillBackgroundColor(HSSFColor.LEMON_CHIFFON.index);
			bgstyle.setFillBackgroundColor(HSSFColor.LEMON_CHIFFON.index);
			bgstyle.setFillPattern( XSSFCellStyle.ALIGN_CENTER);
			cell1.setCellStyle(bgstyle);
			for(int i = 1; i< empinfo.size()+1; i++){
				Cell cell = row1.createCell(i);
				cell.setCellStyle(bgstyle);
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
			workbook.write(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("IE performance record successfully!");
	}
	
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
}
