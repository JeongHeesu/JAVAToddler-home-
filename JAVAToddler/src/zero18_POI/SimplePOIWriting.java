package zero18_POI;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import zero16_ibatis.bean.MemberBean;
import zero16_ibatis.serviceLayer.IMemberServcie;
import zero16_ibatis.serviceLayer.IMemberServiceImpl;
import zero18_POI.util.ExcelFileImporter;

public class SimplePOIWriting {

	public static void main(String[] args) {
		String savePath = "";

		// xlsx 워크북 생성(excel2007용)
	 	// Workbook workBook = WorkbookFactory.create(new POIFSFileSystem());

		// xls 워크북 생성(excel97 ~ excel2003용)
		HSSFWorkbook wb = new HSSFWorkbook();
		// 시트 생성
		HSSFSheet sheet1 = wb.createSheet("memberList");
		// 해당 시트의 첫번째 Row 작성을위한 레퍼런스 취득
		HSSFRow rows = sheet1.createRow(0);

		// 셀의 헤더 작성
		rows.createCell(0).setCellValue("mem_id");
		rows.createCell(1).setCellValue("mem_pass");
		rows.createCell(2).setCellValue("mem_name");
		rows.createCell(3).setCellValue("mem_regno1");
		rows.createCell(4).setCellValue("mem_regno2");
		rows.createCell(5).setCellValue("mem_bir");
		rows.createCell(6).setCellValue("mem_zip");
		rows.createCell(7).setCellValue("mem_add1");
		rows.createCell(8).setCellValue("mem_add2");

		IMemberServcie service = IMemberServiceImpl.getInstance();
		List<MemberBean> memberList = service.getMemberList();

		for(int i=0; i< memberList.size(); i++){
			HSSFRow dataRows = sheet1.createRow(i + 1);
			dataRows.createCell(0).setCellValue(
					memberList.get(i).getMem_id());
			dataRows.createCell(1).setCellValue(
					memberList.get(i).getMem_pass());
			dataRows.createCell(2).setCellValue(
					memberList.get(i).getMem_name());
			dataRows.createCell(3).setCellValue(
					memberList.get(i).getMem_regno1());
			dataRows.createCell(4).setCellValue(
					memberList.get(i).getMem_regno2());
			dataRows.createCell(5).setCellValue(
					memberList.get(i).getMem_bir());
			dataRows.createCell(6).setCellValue(
					memberList.get(i).getMem_zip());
			dataRows.createCell(7).setCellValue(
					memberList.get(i).getMem_add1());
			dataRows.createCell(8).setCellValue(
					memberList.get(i).getMem_add2());
		}

		savePath = "D:\\temp";

		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(savePath
					+ "\\memberList.xls");
			
			wb.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { fileOut.close(); } catch (IOException e) { e.printStackTrace(); }
		}
	}

}
