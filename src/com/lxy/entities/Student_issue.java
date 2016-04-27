package com.lxy.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class Student_issue {

	private int id;
	private String stu_num;//学号
	private String issue_id;//课题id
	private String tea_num;//指导老师编号
	private int state;//0:未审核   1：已审核
	
	
	
	
	@GenericGenerator(name="hw",strategy="native")
	@GeneratedValue(generator="hw")
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStu_num() {
		return stu_num;
	}
	public void setStu_num(String stu_num) {
		this.stu_num = stu_num;
	}
	public String getIssue_id() {
		return issue_id;
	}
	public void setIssue_id(String issue_id) {
		this.issue_id = issue_id;
	}
	
	
	public String getTea_num() {
		return tea_num;
	}
	public void setTea_num(String tea_num) {
		this.tea_num = tea_num;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
}
