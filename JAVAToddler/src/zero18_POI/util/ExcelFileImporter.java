package zero18_POI.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelFileImporter {
	// import reading될 excel 파일 명
	private String fileName;
	// 엑셀 워크 북 객체
	private HSSFWorkbook workBook;
	// 총 시트 넘버
	private int totalSheetNum;
	
	public ExcelFileImporter(String fileName){
		this.fileName = fileName;
		try {
			System.out.println("엑셀 파일명 : " + fileName);

			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
					new File(fileName)));

			this.workBook = new HSSFWorkbook(fs);

			this.totalSheetNum = workBook.getNumberOfSheets();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 해당 워크북의 첫번재 시트의 데이터를 가지 총 로우 수 취득
	public int getTotalRowNum(int sheetIndex){
		return workBook.getSheetAt(sheetIndex).getPhysicalNumberOfRows();
	}
	
	// 특정 Row의 내용을 콘솔로 출력
	public void processRows(HSSFRow row) throws Exception {
		HSSFCell cell = null;
		
		for (int i = 0; i < row.getLastCellNum(); i++) {
			cell = row.getCell(i);
			if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
				System.out.print(row.getCell(i).getStringCellValue());
			}else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
				System.out.print(row.getCell(i).getNumericCellValue());
			}else if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){
				System.out.print(row.getCell(i).getBooleanCellValue());
			}else if(cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
				System.out.print("공백 셀");
			}else{
				System.out.println(row.getCell(i).getDateCellValue());
			}
			System.out.print("\t");
		}
		System.out.println();
		
		// 셀 정보
		System.out.print("Row 인덱스 : " + cell.getRowIndex());
		System.out.print("셀 인덱스 : " + cell.getColumnIndex());
		System.out.print("셀 타입 : " + cell.getCellType());
		
		// 셀로부터 Row 정보 취득
		HSSFRow getRow = cell.getRow();
		System.out.println("해당 Row의 넘버 : " + getRow.getRowNum());
		System.out.println("해당 Row의 높이 : " + getRow.getHeight());
		System.out.println("해당 Row의 첫 셀 넘버 : " + getRow.getFirstCellNum());
		System.out.println("해당 Row의 마지막 셀 넘버 : " + getRow.getLastCellNum());

		// 셀로부터 Sheet 정보 취득
		HSSFSheet getSheet = cell.getSheet();
		System.out.println("해당 시트의 이름 : " + getSheet.getSheetName());
		System.out.println("해당 시트의 첫 Row 넘버 : " + getSheet.getFirstRowNum());
		System.out.println("해당 시트의 마지막 Row 넘버 : " + getSheet.getLastRowNum());
		
		// 시트로부터 WorkBook 취득
		HSSFWorkbook workBook = getSheet.getWorkbook();
		System.out.println("웍크북내에서의 시트 인덱스 : "
				+ workBook.getSheetIndex(getSheet.getSheetName()));
	}

	// 시트 정보 또는 시트 명 출력
	public HSSFSheet getSheet(int sheetIndex) {
		return workBook.getSheetAt(sheetIndex);
	}
	public HSSFSheet getSheet(String sheetName) {
		return workBook.getSheet(sheetName);
	}
	
	// 해당 인덱스의 시트 명 취득
	public String getSheetName(int sheetIndex) {
		return workBook.getSheetName(sheetIndex);
	}

	// Excel내 해당 Row의 Cell 번호로 number type value 취득
	public HSSFRow getRow(int sheetIndex, int rowIndex) {
		return this.workBook.getSheetAt(sheetIndex).getRow(rowIndex);
	}
	// 해당 시트의 해당 Row의 Cell 갯수
	public int getTotalCellNum(int sheetIndex, int rowIndex){
		return this.workBook.getSheetAt(sheetIndex).getRow(rowIndex).getPhysicalNumberOfCells();
	}

	public String getFileName() {
		return fileName;
	}

	public HSSFWorkbook getWorkBook() {
		return workBook;
	}

	public int getTotalSheetNum() {
		return totalSheetNum;
	}
}
