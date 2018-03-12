package zero19_compress.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Stack;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

public class ZIppingUtil {
	public void zip(File src, boolean includeSrc, String charsetName) throws IOException{
	    zip(src, charsetName, includeSrc);
	}

	public void zip(File src, String charSetName, boolean includeSrc) throws IOException{
	    zip(src, src.getParentFile(), charSetName, includeSrc);
	}
	
	public void zip(File src, File destDir, String charSetName, boolean includeSrc) throws IOException{
	    String fileName = src.getName();
	    if(src.isDirectory() == false){
	        final int pos = fileName.lastIndexOf(".");
	        if(pos > 0){
	            fileName = fileName.substring( 0 , pos );
	        }
	    }
	    fileName += ".zip";
	    File zippedFile = new File(destDir, fileName);
	    if(zippedFile.exists() != false){
	        zippedFile.createNewFile();
	    }
	    zip(src, new FileOutputStream(zippedFile), charSetName, includeSrc);
	}
	
	public void zip(File src, OutputStream os, String charsetName, boolean includeSrc) throws IOException{
	    ZipArchiveOutputStream zos = new ZipArchiveOutputStream(os);
	    zos.setEncoding( charsetName );
	    FileInputStream fis = null;
	    int length;
	    ZipArchiveEntry ze = null;
	    byte[] buf = new byte[1024*8];
	    String name = null;
	    Stack<File> stack = new Stack<File>();
	    File root = null;
	    if(src.isDirectory()){
	        if(includeSrc){
	            stack.push(src);
	            root = src.getParentFile();
	        }else{
	            File[] fs = src.listFiles();
	            for(int i=0; i<fs.length ; i++){
	                stack.push(fs[i]);
	            }
	            root = src;
	        }
	    }else{
	        stack.push(src);
	        root = src.getParentFile();
	    }
	    try{
	        while(stack.isEmpty() == false){
	            File f = stack.pop();
	            name = toPath(root, f);
	            if(f.isDirectory()){
	                File[] fs = f.listFiles();
	                for(int i=0; i<fs.length; i++){
	                    if(fs[i].isDirectory()){
	                        stack.push(fs[i]);
	                    }else{
	                        stack.add(0, fs[i]);
	                    }
	                }
	            }else{
	                ze = new ZipArchiveEntry(name);
	                zos.putArchiveEntry(ze);
	                fis = new FileInputStream(f);
	                try{
	                    while((length = fis.read(buf, 0, buf.length)) >= 0){
	                        zos.write(buf, 0, length);
	                    }
	                }finally{
	                    if(fis != null){
	                        fis.close();
	                    }
	                    zos.closeArchiveEntry();
	                }
	            }
	        }
	    }finally{
	        if(zos != null){
	        	
	            zos.close();
	        }
	    }
	}
	
	private String toPath(File root, File dir){
	    String path = dir.getAbsolutePath();
	    path = path.substring(root.getAbsolutePath().length()).replace(File.separatorChar , '/');
	    if(path.startsWith("/")){
	        path = path.substring(1);
	    }
	    if(dir.isDirectory() && path.endsWith("/") == false ){
	        path += "/";
	    }
	    return path;
	}
}
