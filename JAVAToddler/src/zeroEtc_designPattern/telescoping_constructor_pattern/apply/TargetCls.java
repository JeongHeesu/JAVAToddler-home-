package zeroEtc_designPattern.telescoping_constructor_pattern.apply;

public class TargetCls {
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_regno1;
	private String mem_regno2;
	private String mem_bir;
	private String mem_add1;
	private String mem_add2;
	private String mem_job;
	private String mem_like;
	
	public TargetCls(String mem_id, String mem_pass) {
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
	}

	public TargetCls(String mem_id, String mem_pass, String mem_name) {
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
		this.mem_name = mem_name;
	}

	public TargetCls(String mem_id, String mem_pass, String mem_name, 
			String mem_regno1, String mem_regno2) {
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
		this.mem_name = mem_name;
		this.mem_regno1 = mem_regno1;
		this.mem_regno2 = mem_regno2;
	}

	public TargetCls(String mem_id, String mem_pass, String mem_name, 
			String mem_regno1, String mem_regno2, String mem_bir) {
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
		this.mem_name = mem_name;
		this.mem_regno1 = mem_regno1;
		this.mem_regno2 = mem_regno2;
		this.mem_bir = mem_bir;
	}
	
}
