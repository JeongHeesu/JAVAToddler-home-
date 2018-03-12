package zero17_iText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import zero16_ibatis.bean.CartBean;
import zero16_ibatis.bean.MemberBean;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.WriterException;

// iText-5.5.4.jar 용 
public class ConvertPDF {

	private List<MemberBean> memberList;
	private List<CartBean> cartList;

	public PdfPTable createTableMember(List<MemberBean> memberList)
			throws FileNotFoundException, DocumentException, IOException,
			WriterException {

		this.memberList = memberList;
		BaseFont bfKorean = BaseFont.createFont(
				"c:\\windows\\fonts\\batang.ttc,0", BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED);
		PdfPTable table = new PdfPTable(7);
		table.setSpacingBefore(10f);
		table.setSpacingAfter(10f);
		table.setWidthPercentage(380 / 4f);
		// table.setWidths(new int[]{2, 2, 2, 2, 2, 2, 3});
		PdfPCell cell;
		Font tableFont = new Font(bfKorean, 10, Font.ITALIC);
		// header 부분

		cell = new PdfPCell(new Paragraph("아이디", tableFont));
		table.addCell(cell);
		cell = new PdfPCell(new Paragraph("패스워드", tableFont));
		table.addCell(cell);
		cell = new PdfPCell(new Paragraph("이름", tableFont));
		table.addCell(cell);
		cell = new PdfPCell(new Paragraph("주민번호1", tableFont));
		table.addCell(cell);
		cell = new PdfPCell(new Paragraph("주민번호2", tableFont));
		table.addCell(cell);
		cell = new PdfPCell(new Paragraph("직업", tableFont));
		table.addCell(cell);
		cell = new PdfPCell(new Paragraph("취미", tableFont));
		table.addCell(cell);

		// content 부분

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = sdf.format(d);

		for (MemberBean memberInfo : memberList) {
			String memId = memberInfo.getMem_id();
			String memPass = memberInfo.getMem_pass();
			String memName = memberInfo.getMem_name();
			String memRegno1 = memberInfo.getMem_regno1();
			String memRegno2 = memberInfo.getMem_regno2();
			String memJob = memberInfo.getMem_job();
			String memLike = memberInfo.getMem_like();

			cell = new PdfPCell(new Phrase(memId, tableFont));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(memPass, tableFont));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(memName, tableFont));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(memRegno1, tableFont));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(memRegno2, tableFont));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(memJob, tableFont));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(memLike, tableFont));
			table.addCell(cell);
		}

		return table;
	}

	public Document getDocument() throws FileNotFoundException,
			DocumentException, IOException, WriterException {

		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		String savePath = "d://temp";
		// 1(경로설정과 저장)
		PdfWriter.getInstance(document, new FileOutputStream(savePath
				+ "/PDFconvertFile.pdf"));
		// 2(열기)
		document.open();
		// 한글처리
		BaseFont bfKorean = BaseFont.createFont(
				"c:\\windows\\fonts\\batang.ttc,0", BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED);
		// 폰트설정(속성부여)
		Font font1 = new Font(bfKorean);
		// Font font2 = FontFactory.getFont(String.valueOf(Font.BOLD), 20,
		// BaseColor.BLUE);
		font1.setSize(20f);
		font1.setColor(BaseColor.BLUE);
		font1.setStyle(Font.BOLD);
		// 3(param생성)
		// Paragraph param = new Paragraph("PDF convert test1 첫번째 시도", font1);
		// 4(param추가)
		// table 추가시키기

			
		Paragraph paramIssue = new Paragraph("내용", font1);
		document.add(paramIssue);
		document.add(createTableMember(this.memberList));
		document.close();
		
		document.close();
		return document;
	}

}
