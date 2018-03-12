package zero20_barcodeAPI;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import zero20_barcodeAPI.res.BarcodeReading;
import zero20_barcodeAPI.res.BarcodeWriting;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;

public class TestMain {

	private Collection<BarcodeFormat> decodeFormats;
	
	public void barCodeDecoding(String fileName){
		Map<DecodeHintType,Object> hints = new EnumMap<DecodeHintType,Object>(DecodeHintType.class);

		decodeFormats = EnumSet.noneOf(BarcodeFormat.class);
        decodeFormats.addAll(EnumSet.of(BarcodeFormat.QR_CODE));
        decodeFormats.addAll(EnumSet.of(
                BarcodeFormat.UPC_A,
                BarcodeFormat.UPC_E, 
                BarcodeFormat.EAN_13, 
                BarcodeFormat.EAN_8,
                BarcodeFormat.RSS_14));
        decodeFormats.addAll(EnumSet.of(
                BarcodeFormat.CODE_39,
                BarcodeFormat.CODE_93,
                BarcodeFormat.CODE_128,
                BarcodeFormat.ITF,
                BarcodeFormat.DATA_MATRIX,
                BarcodeFormat.PDF_417,
                BarcodeFormat.CODABAR));
		
        hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
		
		String result = "";
		File file = new File(fileName);
		try {
			result = BarcodeReading.decode(file, hints);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("바코드 디코딩 결과 : " + result);
	}

	public void barCodeEncoding(String fileName, BarcodeFormat barcodeFormat){
		Map<EncodeHintType,Object> hints = new EnumMap<EncodeHintType,Object>(EncodeHintType.class);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		
        File file = new File(fileName);
        
        try {
        	if(barcodeFormat == BarcodeFormat.UPC_A){
	        	// 바코드 컨텐츠는 11 또는 12로 작성.
				BarcodeWriting.encode(file, "123456789012", barcodeFormat, 518, 191, hints);
        	}
        	if(barcodeFormat == BarcodeFormat.QR_CODE){
	        	// 바코드 컨텐츠는 11 또는 12로 작성.
				BarcodeWriting.encode(file, "모두들열심이합시다.", barcodeFormat, 200, 200, hints);
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// QR Code 디코딩
		new TestMain().barCodeDecoding("D:\\workspace(java)\\JAVAToddler\\bin\\zero20_barcodeAPI\\sample\\qrcode.png");
		new TestMain().barCodeDecoding("D:\\workspace(java)\\JAVAToddler\\bin\\zero20_barcodeAPI\\sample\\iloveyou.png");
		// 바코드 디코딩
		new TestMain().barCodeDecoding("D:\\workspace(java)\\JAVAToddler\\bin\\zero20_barcodeAPI\\sample\\radB6DF1.png");
		
		// 신규 바코드 인코딩
		new TestMain().barCodeEncoding("D:\\workspace(java)\\JAVAToddler\\bin\\zero20_barcodeAPI\\sample\\barcodeEncode1.png",
				BarcodeFormat.UPC_A);
		// 신규 생성된 바코드 디코딩
		new TestMain().barCodeDecoding("D:\\workspace(java)\\JAVAToddler\\bin\\zero20_barcodeAPI\\sample\\barcodeEncode1.png");

		// 신규 QRCODE 인코딩
		new TestMain().barCodeEncoding("D:\\workspace(java)\\JAVAToddler\\bin\\zero20_barcodeAPI\\sample\\barcodeEncode1.png",
				BarcodeFormat.QR_CODE);
		// 신규 QRCODE 디코딩
		new TestMain().barCodeDecoding("D:\\workspace(java)\\JAVAToddler\\bin\\zero20_barcodeAPI\\sample\\barcodeEncode1.png");
	}

}
