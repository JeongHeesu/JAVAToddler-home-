package zero16_mybatis.daoLayer;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import zero16_mybatis.build.BuildedSqlSessionClient;

public class IObjectCreateDaoImpl implements IObjectCreateDao {
	private static IObjectCreateDao dao = null;
	private SqlSessionFactory sqlSessionFactory = null;

	private IObjectCreateDaoImpl(){
		sqlSessionFactory = BuildedSqlSessionClient.getSqlSessionFactory();
	}
	
	public static IObjectCreateDao getInstance(){
		return (dao == null) ? dao = new IObjectCreateDaoImpl() : dao;
	}
	
	@Override
	public void createGradeTable() throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try{
			session.update("state.dropGradeTable");
			session.update("state.createGradeTable");
			session.commit();
		}catch(RuntimeException e){
			session.rollback();
		}finally{
			session.close();
		}
	}

	@Override
	public void createGradeSequence() throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try{
			session.update("state.dropGradeSequence");
			session.update("state.createGradeSequence");
			session.commit();
		}catch(RuntimeException e){
			session.rollback();
		}finally{
			session.close();
		}
	}

	@Override
	public void insertGrades() throws SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try{
			session.delete("state.deleteGrades");
			session.insert("state.insertGrades");
			session.commit();
		}catch(RuntimeException e){
			session.rollback();
		}finally{
			session.close();
		}
	}
}








