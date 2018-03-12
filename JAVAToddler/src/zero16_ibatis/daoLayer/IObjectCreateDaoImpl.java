package zero16_ibatis.daoLayer;

import java.sql.Connection;
import java.sql.SQLException;

import zero16_ibatis.build.BuildedSqlMapClient;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapSession;

public class IObjectCreateDaoImpl implements IObjectCreateDao {
	private static IObjectCreateDao dao = null;
	private SqlMapClient client = null;
	
	private IObjectCreateDaoImpl(){
		client = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static IObjectCreateDao getInstance(){
		return (dao == null) ? dao = new IObjectCreateDaoImpl() : dao;
	}
	
	@Override
	public void createGradeTable() throws SQLException {
		Connection conn = client.getDataSource().getConnection();
		conn.setAutoCommit(false);
		SqlMapSession session = client.openSession(conn);
		try{
			session.update("state.dropGradeTable");
			session.update("state.createGradeTable");
			conn.commit();
		}catch (SQLException e) {
			conn.rollback();
		}finally{
			session.close();
			conn.close();
		}
	}

	@Override
	public void createGradeSequence() throws SQLException {
		try{
			client.startTransaction();
			
			client.update("state.dropGradeSequence");
			client.update("state.createGradeSequence");
			
			client.commitTransaction();
		}finally{
			client.endTransaction();
		}
	}

	@Override
	public void insertGrades() throws SQLException {
		try{
			client.startTransaction();
			
			client.delete("state.deleteGrades");
			client.insert("state.insertGrades");
			client.commitTransaction();
		}finally{
			client.endTransaction();
		}
	}
}








