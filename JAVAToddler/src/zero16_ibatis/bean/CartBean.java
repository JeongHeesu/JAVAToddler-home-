package zero16_ibatis.bean;

public class CartBean {
	private String cart_member;
	private String cart_no;
	private String cart_prod;
	private String cart_qty;
	public String getCart_member() {
		return cart_member;
	}
	public void setCart_member(String cart_member) {
		this.cart_member = cart_member;
	}
	public String getCart_no() {
		return cart_no;
	}
	public void setCart_no(String cart_no) {
		this.cart_no = cart_no;
	}
	public String getCart_prod() {
		return cart_prod;
	}
	public void setCart_prod(String cart_prod) {
		this.cart_prod = cart_prod;
	}
	public String getCart_qty() {
		return cart_qty;
	}
	public void setCart_qty(String cart_qty) {
		this.cart_qty = cart_qty;
	}
}
