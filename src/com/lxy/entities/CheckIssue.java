package com.lxy.entities;

public class CheckIssue {

	private int id;//记录student_issue中的id
	private String iss_name;
	private String stu_num;
	private String stu_name;//学生姓名
	private String stu_sex;//学生性别
	private String stu_pro;//学生专业
	private String state;//状态  把0转为未审核   1转为已审核
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getIss_name() {
		return iss_name;
	}
	public void setIss_name(String iss_name) {
		this.iss_name = iss_name;
	}
	public String getStu_num() {
		return stu_num;
	}
	public void setStu_num(String stu_num) {
		this.stu_num = stu_num;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getStu_sex() {
		return stu_sex;
	}
	public void setStu_sex(String stu_sex) {
		this.stu_sex = stu_sex;
	}
	public String getStu_pro() {
		return stu_pro;
	}
	public void setStu_pro(String stu_pro) {
		this.stu_pro = stu_pro;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
