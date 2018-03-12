package zero16_mybatis.build;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.ibatis.common.resources.Resources;

public class BuildedSqlSessionClient{
	
	private static SqlSessionFactory sqlSessionFactory = null;
	
	static{
		try{
		  Charset ch = Charset.forName("utf-8");
		  Resources.setCharset(ch); 
		  Reader reader = Resources.getResourceAsReader("zero16_mybatis/conf/SqlSessionConfig.xml");
		  sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		  reader.close(); 
		}catch(IOException e){
			throw new RuntimeException("SqlSession 생성 실패" + e, e);
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory(){
		return sqlSessionFactory;
	}

}
