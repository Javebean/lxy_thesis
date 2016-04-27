package com.lxy.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Teacher")
public class Teacher {

	private int id;
	private String tea_num;//教师标号
	private String password;//密码
	private String name;//姓名
	private String sex;//0：女   1:男
	private String phone;
	private String email;
	private String position;//职称
	private String tea_intro;
	
	
	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTea_num() {
		return tea_num;
	}
	public void setTea_num(String tea_num) {
		this.tea_num = tea_num;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getTea_intro() {
		return tea_intro;
	}
	public void setTea_intro(String tea_intro) {
		this.tea_intro = tea_intro;
	}
	
	
}
