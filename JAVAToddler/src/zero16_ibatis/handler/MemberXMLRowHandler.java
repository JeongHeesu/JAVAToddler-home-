package zero16_ibatis.handler;

import zero16_ibatis.bean.MemberBean;

import com.ibatis.sqlmap.client.event.RowHandler;

public class MemberXMLRowHandler implements RowHandler {
	private StringBuffer xmlDocument = new StringBuffer("<members>");
	private String rtnValue = "";

	// select에의해 조회되는 각각의 레코드별 handleRow가 각각 호출됨.
	@Override
	public void handleRow(Object valueObject) {
		MemberBean member = (MemberBean)valueObject;
		System.out.println("valueObject" + valueObject);
		System.out.println("mem_id : " + member.getMem_id() +
				" / mem_pass : " + member.getMem_pass());
	}

	public String getMemberListXML() {
		if(rtnValue == null) {
			xmlDocument.append("</members>");
			rtnValue = xmlDocument.toString();
		}
		return rtnValue;
	}
}
