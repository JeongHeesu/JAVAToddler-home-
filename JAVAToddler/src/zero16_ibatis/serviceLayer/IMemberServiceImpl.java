package zero16_ibatis.serviceLayer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;

import zero16_ibatis.bean.CartBean;
import zero16_ibatis.bean.MemberBean;
import zero16_ibatis.daoLayer.IMemberDao;
import zero16_ibatis.daoLayer.IMemberDaoImpl;

public class IMemberServiceImpl implements IMemberServcie {
	private static IMemberServcie service = null;
	private IMemberDao dao = null;
	private IMemberServiceImpl(){
		dao = IMemberDaoImpl.getInstance();
	}
	
	public static IMemberServcie getInstance(){
		if(service == null)
			service = new IMemberServiceImpl();
		return service;
	}
	
	@Override
	public MemberBean getMemberInfo(Map<String, String> params) {
		MemberBean member = null;
		try{
			member = dao.getMemberInfo(params);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public Document getMemberInfoXML(Map<String, String> params) {
		Document document = null;
		try{
			document = dao.getMemberInfoXML(params);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return document;
	}

	@Override
	public List<MemberBean> getMemberList() {
		List<MemberBean> memberList = null;
		try{
			memberList = dao.getMemberList();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return memberList;
	}

	@Override
	public List<MemberBean> getDynamicMemberList(Map<String, Object> dynamicParams) {
		List<MemberBean> memberList = null;
		try{
			memberList = dao.getDynamicMemberList(dynamicParams);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return memberList;
	}

	@Override
	public List<MemberBean> getMemberListUseList(List<String> params) {
		List<MemberBean> memberList = null;
		try{
			memberList = dao.getMemberListUseList(params);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return memberList;
	}

	@Override
	public String getMemberJob(MemberBean memberParams) {
		String mem_job = null;
		try{
			mem_job = dao.getMemberJob(memberParams);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mem_job;
	}

	@Override
	public String insertMemberInfo(MemberBean insertParams) {
		// TestMain =========> service layer =============> dao layer
		// 요청                         원격서버에서 값취득                       쿼리질의
		// 결과활용                     dao 익셉션 처리 
		//                     dao 전달값 조작
		//                     TestMain전달값 조작
		String mem_id = "";
		try{
			mem_id = dao.insertMemberInfo(insertParams);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mem_id;
	}

	@Override
	public int deleteMemberInfo(Map<String, String> deleteParam) {
		int deleteCnt = 0;
		try{
			deleteCnt = dao.deleteMemberInfo(deleteParam);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public MemberBean getTableJoinResult1(String param) {
		MemberBean tableJoinResult = null;
		try{
			tableJoinResult = dao.getTableJoinResult1(param);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return tableJoinResult;
	}

	@Override
	public MemberBean getTableJoinResult2(String param) {
		MemberBean tableJoinResult = null;
		try{
			tableJoinResult = dao.getTableJoinResult2(param);
			
			List<CartBean> cartBeanList = tableJoinResult.getCartInfos();
			for(CartBean cartBean : cartBeanList){
				System.out.println("cart_member : " + cartBean.getCart_member());
				System.out.println("cart_no : " + cartBean.getCart_no());
				System.out.println("cart_prod : " + cartBean.getCart_prod());
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return tableJoinResult;
	}

	@Override
	public void getProcedureCall1(MemberBean procedureParam1) {
		try{
			dao.getProcedureCall1(procedureParam1);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void getProcedureCall2(MemberBean params) {
		try{
			dao.getProcedureCall2(params);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void getFunctionCall1(Map<String, String> params) {
		try{
			dao.getFunctionCall1(params);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void getFunctionCall2(Map<String, String> params) {
		try{
			dao.getFunctionCall2(params);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void getpkgCall1(Map<String, Object> params) {
		try{
			dao.getpkgCall1(params);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}




