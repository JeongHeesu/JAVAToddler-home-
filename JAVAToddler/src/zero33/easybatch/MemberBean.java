package zero33.easybatch;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.sun.istack.internal.Nullable;

// @NotNull : 반드시 not null인 값 설정.
// @Null : 반드시 null값 설정.
// @Nullable : null 및 null외의 값 설정.
// @Size : min 속성값 이상, max 속성값 이하
// @Pattern : 정의된 정규식 패턴과 일치
// @Max : 최대값 (속성 타입 int에 적용)
// @Min : 최소값 (속성 타입 int에 적용)
// @Future : 미래 날짜값(속성 타입 Date에 적용)
// @Past : 과거 날짜값(속성 타입 Date에 적용)
// @
public class MemberBean {
	@NotNull @Size(min=4, max=4)
	private String mem_id;
	@NotNull @Pattern(regexp="[\\w-]+")
	private String mem_pass;
	@NotNull @Pattern(regexp="[가-힣]+")
	private String mem_name;
	@NotNull @Size(min=6, max=6)
	private String mem_regno1;
	@NotNull @Pattern(regexp="\\d{7}")
	private String mem_regno2;
	@Nullable @Pattern(regexp="\\d{2}/\\d{2}/\\d{2}")
	private String mem_bir;
	@NotNull @Pattern(regexp="\\d{3}-\\d{3}", message="invalid zipcode")
	private String mem_zip;
	@NotNull
	private String mem_add1;
	@NotNull
	private String mem_add2;
	@NotNull @Pattern(regexp="\\d{2,3}-\\d{3,4}-\\d{4}", message="invalid hometel")
	private String mem_hometel;
	@NotNull @Pattern(regexp="\\d{2,3}-\\d{3,4}-\\d{4}", message="invalid comtel") 
	private String mem_comtel;
	@Nullable  @Pattern(regexp="\\d{3}-\\d{3,4}-\\d{4}", message="invalid hp")
	private String mem_hp;
	@NotNull @Email
	private String mem_mail;
	@Nullable
	private String mem_job;
	@Nullable
	private String mem_like;
	@Nullable
	private String mem_memorial;
	@Nullable
	private String mem_memorialday;
	@Nullable
	private String mem_mileage;
	@Nullable
	private String mem_delete;
	
	public String getMem_id() {
		return mem_id;
	}
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
	public String getMem_regno1() {
		return mem_regno1;
	}
	public void setMem_regno1(String mem_regno1) {
		this.mem_regno1 = mem_regno1;
	}
	public String getMem_regno2() {
		return mem_regno2;
	}
	public void setMem_regno2(String mem_regno2) {
		this.mem_regno2 = mem_regno2;
	}
	public String getMem_bir() {
		return mem_bir;
	}
	public void setMem_bir(String mem_bir) {
		this.mem_bir = mem_bir;
	}
	public String getMem_zip() {
		return mem_zip;
	}
	public void setMem_zip(String mem_zip) {
		this.mem_zip = mem_zip;
	}
	public String getMem_add1() {
		return mem_add1;
	}
	public void setMem_add1(String mem_add1) {
		this.mem_add1 = mem_add1;
	}
	public String getMem_add2() {
		return mem_add2;
	}
	public void setMem_add2(String mem_add2) {
		this.mem_add2 = mem_add2;
	}
	public String getMem_hometel() {
		return mem_hometel;
	}
	public void setMem_hometel(String mem_hometel) {
		this.mem_hometel = mem_hometel;
	}
	public String getMem_comtel() {
		return mem_comtel;
	}
	public void setMem_comtel(String mem_comtel) {
		this.mem_comtel = mem_comtel;
	}
	public String getMem_hp() {
		return mem_hp;
	}
	public void setMem_hp(String mem_hp) {
		this.mem_hp = mem_hp;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public String getMem_job() {
		return mem_job;
	}
	public void setMem_job(String mem_job) {
		this.mem_job = mem_job;
	}
	public String getMem_like() {
		return mem_like;
	}
	public void setMem_like(String mem_like) {
		this.mem_like = mem_like;
	}
	public String getMem_memorial() {
		return mem_memorial;
	}
	public void setMem_memorial(String mem_memorial) {
		this.mem_memorial = mem_memorial;
	}
	public String getMem_memorialday() {
		return mem_memorialday;
	}
	public void setMem_memorialday(String mem_memorialday) {
		this.mem_memorialday = mem_memorialday;
	}
	public String getMem_mileage() {
		return mem_mileage;
	}
	public void setMem_mileage(String mem_mileage) {
		this.mem_mileage = mem_mileage;
	}
	public String getMem_delete() {
		return mem_delete;
	}
	public void setMem_delete(String mem_delete) {
		this.mem_delete = mem_delete;
	}
}
