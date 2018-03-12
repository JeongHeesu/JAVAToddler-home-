package zero19_compress.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;

public class UNZppingUtil {
	public List<String> unzip(File zippedFile, File destDir) throws IOException{
	    if(destDir.exists() == false){
	        destDir.mkdir();
	    }
	    return unzip(new FileInputStream(zippedFile), destDir , Charset.defaultCharset().name());
	}
	public List<String> unzip(File zippedFile, File destDir, String charsetName) throws IOException{
	    if(destDir.exists() == false){
	        destDir.mkdir();
	    }
	    return unzip(new FileInputStream(zippedFile), destDir, charsetName);
	}
	public List<String> unzip(InputStream is, File destDir, String charsetName) throws IOException{
		List<String> unZipFiles = new ArrayList<String>();
        ZipArchiveInputStream zis = null;
        ZipArchiveEntry entry = null;
        int nWritten = 0;
        byte[] buf = new byte[1024*8];
        zis = new ZipArchiveInputStream(is, charsetName, false);
        try{
            while((entry = zis.getNextZipEntry()) != null ){
                File target = null;
                unZipFiles.add(entry.getName());
                target = new File(destDir, entry.getName());
                if(entry.isDirectory()){
                    target.mkdirs( ); 
                }else{
                    File f = new File( target.getParent());
                    if(f.exists() == false){
                        f.mkdir();
                    }
                    target.createNewFile();
                    BufferedOutputStream bos = null;
                    try{
                        bos = new BufferedOutputStream(new FileOutputStream(target));
                        while((nWritten = zis.read(buf)) >= 0){
                            bos.write(buf , 0, nWritten);
                        }
                    }finally{
                        if(bos != null){
                            bos.close();
                        }
                    }
                }
            }
        }finally{
            if(zis != null){
                zis.close();
            }
        }
        return unZipFiles;
    }
}
