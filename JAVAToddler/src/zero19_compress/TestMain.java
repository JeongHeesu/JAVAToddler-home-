package zero19_compress;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import zero19_compress.util.UNZppingUtil;

public class TestMain {

	public static void main(String[] args) {
		String path = "D:\\교재\\Oracle\\새우편번호데이터";
		List<String> unZipFiles = null;
		
		UNZppingUtil unzipUtil = new UNZppingUtil();
		try{
			unZipFiles = unzipUtil.unzip(new File(path +"\\Gangwon.zip") , new File(path),"euc-kr");
		}catch(IOException e){
		    e.printStackTrace( );
		}
		
		if(unZipFiles != null){

			Connection conn = null;
			PreparedStatement pstmt = null;
			Statement stmt = null;
			ResultSet  rs = null;
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");	
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@127.0.0.1:1521:xe","sem","java");

				stmt = conn.createStatement();
				String newzipcodeTable = "create table newzipcode("+
							"zipcode varchar2(100)," +
						    "sido varchar2(100)," +
							"gugun varchar2(100)," +
						    "doro varcahr2(100))";
				
				stmt.executeUpdate(newzipcodeTable);
				
				conn.setAutoCommit(false);
				
				
				pstmt = conn.prepareStatement("insert into newzipcode(zipcode, sido, gugun, doro)" +
									  "values(?,?,?,?)");
				
				Iterator itr = unZipFiles.iterator();
				while(itr.hasNext()){
					BufferedReader br = new BufferedReader(new FileReader(new File(path+"\\"+itr.next())));
					String temp = "";
					String[] values = null;
					br.readLine();
					for(int i=1; (temp = br.readLine()) !=null; i++) {
						values = temp.split("\\|");
						int cnt=1;
						pstmt.setString(cnt++, values[0]);
						pstmt.setString(cnt++, values[2]);
						pstmt.setString(cnt++, values[4]);
						pstmt.setString(cnt++, values[10]+" "+values[18]+"-"+values[19]);
						pstmt.executeUpdate();
					}
				}
				conn.commit();
			}catch(Exception e){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
