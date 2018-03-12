package zero11_zacksB;

import javax.xml.bind.annotation.XmlAttribute;

public class MemberVO {
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	public String getMem_id() {
		return mem_id;
	}
	@XmlAttribute(name="mem_id")
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
}
