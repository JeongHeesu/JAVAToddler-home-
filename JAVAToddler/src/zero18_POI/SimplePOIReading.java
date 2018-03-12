package zero18_POI;

import org.apache.poi.hssf.usermodel.HSSFRow;

import zero18_POI.util.ExcelFileImporter;

public class SimplePOIReading {

	public static void main(String[] args) {
		String fileName = "D:\\temp\\memberInfo.xls";
		// 워크북 취득
		ExcelFileImporter excelImporter = new ExcelFileImporter(fileName);
			
		for(int sheetIndex=0; sheetIndex<excelImporter.getTotalSheetNum(); sheetIndex++){
			for(int rowIndex=0; rowIndex<excelImporter.getTotalRowNum(sheetIndex); rowIndex++){
				HSSFRow row = excelImporter.getRow(sheetIndex, rowIndex);
				try {
					excelImporter.processRows(row);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
