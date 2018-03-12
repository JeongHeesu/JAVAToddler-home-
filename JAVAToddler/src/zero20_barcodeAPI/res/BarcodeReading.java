package zero20_barcodeAPI.res;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class BarcodeReading {
	/**
	  * 바코드 이미지 정보 해석 
	  * @param 바코드 이미지 파일
	  * @param 바코드 디코딩 설정
	  * @return 디코딩 결과
	  */
	public static String decode(File file, Map<DecodeHintType, Object> hints) throws Exception {
		// check the required parameters 
		if (file == null || file.getName().trim().isEmpty())
			throw new IllegalArgumentException("파일을 찾을수없습니다.");
		BufferedImage image;
		try {
			image = ImageIO.read(file);
		}catch (IOException ioe) {
			throw new Exception(ioe.getMessage());
		}
		if (image == null)
			throw new IllegalArgumentException("디코딩할수 없습니다.");
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		MultiFormatReader barcodeReader = new MultiFormatReader();
		Result result;
		String finalResult = "";
		try {
			if (hints != null && ! hints.isEmpty())
				result = barcodeReader.decode(bitmap, hints);
			else
				result = barcodeReader.decode(bitmap);
			finalResult = String.valueOf(result.getText());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return finalResult;
	}
}
