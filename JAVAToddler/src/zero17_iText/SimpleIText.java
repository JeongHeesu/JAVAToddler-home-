package zero17_iText;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import zero16_ibatis.bean.MemberBean;
import zero16_ibatis.serviceLayer.IMemberServcie;
import zero16_ibatis.serviceLayer.IMemberServiceImpl;

import com.lowagie.text.Cell;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class SimpleIText {

	public static void main(String[] args) {
		// 문서 생성. 1 페이지 사이즈, 2 왼쪽 여백, 3 오른쪽 여백, 4 상단 여백, 5 하단여백
		// 생성된 문서에 PdfWriter, HtmlWriter, XmlWriter 등의 Writer를 이용해
		// 해당 문서에 작성될 type을 설정한다.
		Document doc = new Document(PageSize.A4, 50, 50,50,50);
	
		String savePath = "d://temp";
		
		// pdf 형식의 내용을 Document에 포함하기위한 PdfWriter 생성. 저장 위치 및 이름.
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(savePath+"/SimpleCreateTest.PDF"));

			// 내용 추가를위해 Document 오픈
			doc.open();
			
			// 작성될 PDF 파일 정보
			doc.addTitle("타이틀");
			doc.addSubject("제목");
			doc.addAuthor("바그란트");
			doc.addCreationDate();
			
			// 한글 폰트 설정(iTextAsian.jar 활용). 
			BaseFont bfKorean = BaseFont.createFont(
					"c:\\windows\\fonts\\batang.ttc,0", BaseFont.IDENTITY_H,
					BaseFont.EMBEDDED);
			
			// BaseFont에 스타일 적용
			Font black_15_font = new Font(bfKorean, 15, Font.BOLD);
			// Font.COURIER 배달원 폰트(무료)
			Font pink_10_font = new Font(bfKorean, 10, Font.COURIER, new Color(255,155, 200));
			
			// Paragraph를 이용한 내용 추가
			doc.add(new Paragraph("첫번째 페이지의 첫번째 내용", black_15_font));
			doc.add(new Paragraph());
			doc.add(new Paragraph("첫번째 페이지의 두번째 내용", pink_10_font));
			
			
			// 5열 2행 테이블 생성
			Table table = new Table(5, 2);
			table.setBorderColor(new Color(220, 255, 100));
			table.setPadding(5);
			table.setSpacing(5);
			
			// 헤더 셀 정의
			Cell cell = new Cell(new Paragraph("mem_id", pink_10_font));
			cell.setHeader(true);
			table.addCell(cell);
			
			cell = new Cell(new Paragraph("mem_pass", pink_10_font));
			table.addCell(cell);
			
			cell = new Cell(new Paragraph("mem_name", pink_10_font));
			table.addCell(cell);
			
			cell = new Cell(new Paragraph("mem_regno1", pink_10_font));
			table.addCell(cell);
			
			cell = new Cell(new Paragraph("mem_regno2", pink_10_font));
			table.addCell(cell);
			
			// 헤더 셀 작성 종료
			table.endHeaders();
			
			IMemberServcie service = IMemberServiceImpl.getInstance();
			List<MemberBean> memberList = service.getMemberList();

			for(MemberBean member : memberList) {
				table.addCell(new Paragraph(member.getMem_id(), pink_10_font));
				table.addCell(new Paragraph(member.getMem_pass(), pink_10_font));
				table.addCell(new Paragraph(member.getMem_name(), pink_10_font));
				table.addCell(new Paragraph(member.getMem_regno1(), pink_10_font));
				table.addCell(new Paragraph(member.getMem_regno2(), pink_10_font));
			}
			
			Paragraph title1 = new Paragraph("회원 리스트", black_15_font);
			Chapter chapter1 = new Chapter(title1, 1);
			// 챕터 번호 설정 및 출력. 0 설정시 챕터 번호가 않붙음
			chapter1.setNumberDepth(1);
			
			// 챕터 document에 추가
			doc.add(chapter1);
			
			// 작성된 테이블의 Document 추가
			doc.add(table);
				
			// Paragraph 객체를 통해 내용 추가
			doc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
