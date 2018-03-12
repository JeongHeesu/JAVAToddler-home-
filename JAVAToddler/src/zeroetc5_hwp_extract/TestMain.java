package zeroetc5_hwp_extract;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import zeroetc5_hwp_extract.utils.HwpTextExtractorV5;

public class TestMain {

	public static void main(String[] args) {
		File hwp = new File("D:\\temp\\프로젝트수행간핵.hwp");
		Writer writer = new StringWriter();
		StringBuffer buffer = null;
		try {
			HwpTextExtractorV5.extractText(hwp, writer);
			buffer = new StringBuffer(writer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(buffer.toString());
	}

}
