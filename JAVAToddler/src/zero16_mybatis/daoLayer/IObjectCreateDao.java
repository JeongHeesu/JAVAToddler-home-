package zero16_mybatis.daoLayer;

import java.sql.SQLException;

public interface IObjectCreateDao {
	public void createGradeTable() throws SQLException;
	public void createGradeSequence() throws SQLException;
	public void insertGrades() throws SQLException;
}
