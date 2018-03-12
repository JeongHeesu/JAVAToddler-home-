package zero16_mybatis.serviceLayer;

import java.sql.SQLException;

import zero16_ibatis.daoLayer.IObjectCreateDao;
import zero16_ibatis.daoLayer.IObjectCreateDaoImpl;

public class IObjectCreateServiceImpl implements IObjectCreateService {
	private static IObjectCreateService service = null;
	private IObjectCreateDao dao = null;
	private IObjectCreateServiceImpl(){
		dao = IObjectCreateDaoImpl.getInstance();
	}
	
	public static IObjectCreateService getInstance(){
		return (service == null) ? service = new IObjectCreateServiceImpl() : service;
	}
	
	@Override
	public void createGradeTable() {
		try{
			dao.createGradeTable();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createGradeSequence() {
		try{
			dao.createGradeSequence();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertGrades() {
		try{
			dao.insertGrades();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}





