package zero20_barcodeAPI.res;

import java.io.File;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class BarcodeWriting {
	private static final BarcodeFormat DEFAULT_BARCODE_FORMAT = BarcodeFormat.QR_CODE;   
	private static final String DEFAULT_IMAGE_FORMAT = "png";   
	private static final String DEFAULT_OUTPUT_FILE = "out";   
	private static final int DEFAULT_WIDTH = 300;   
	private static final int DEFAULT_HEIGHT = 300; 
	
	/**
	  * 바코드 생성 및 생성된 바코드 설정사항 생성
	  * @param 생성될 바코드 이미지 저장 파일
	  * @param 바코드화될 컨텐츠
	  * @param 바코드 포맷
	  * @param 생성될 바코드 이미지 폭
	  * @param 생성될 바코드 이미지 높이
	  * @param 바코드 생성시 설정사항
	  */
	 public static void encode(File file, String contents, BarcodeFormat barcodeFormat, int width,
	                           int height, Map<EncodeHintType, Object> hints) throws Exception {
		 if (barcodeFormat == null)
	         barcodeFormat = DEFAULT_BARCODE_FORMAT;
	     if (width <= 9)
	         width = DEFAULT_WIDTH;
	     if (height <= 9)
	         height = DEFAULT_HEIGHT;
	     
	     if (file == null || file.getName().trim().isEmpty())
	         throw new IllegalArgumentException("인코딩되어 저장될 파일을 찾을수없습니다.");
	     if (contents == null || contents.trim().isEmpty())
	         throw new IllegalArgumentException("인코딩 정보가 없습니다.");
	     try {
	         MultiFormatWriter barcodeWriter = new MultiFormatWriter();
	         BitMatrix matrix;
	         if (hints != null)
	             matrix = barcodeWriter.encode(contents, barcodeFormat, width, height, hints);
	         else
	             matrix = barcodeWriter.encode(contents, barcodeFormat, width, height);
	         String imageFormat = file.getName().substring(file.getName().indexOf(".") + 1);
	         MatrixToImageWriter.writeToFile(matrix, imageFormat, file);
	     } catch (Exception e) {
	         throw new Exception(e.getMessage());
	     }
	 }
}
