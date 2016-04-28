package com.lxy.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table
public class Issue {

	private int id;
	private String i_name;//课题名称
	private String i_teacher;//指导老师
	private String tea_num;//指导老师的号码
	private String limit_pro;//限选专业
	private int limit_num;//限选人数
	private boolean select;//是否被选择
	
	private boolean state;//是否同意该生选课
	
	@GenericGenerator(name="hw",strategy="native")
	@GeneratedValue(generator="hw")
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getI_name() {
		return i_name;
	}
	public void setI_name(String i_name) {
		this.i_name = i_name;
	}
	public String getI_teacher() {
		return i_teacher;
	}
	public void setI_teacher(String i_teacher) {
		this.i_teacher = i_teacher;
	}
	public String getLimit_pro() {
		return limit_pro;
	}
	public void setLimit_pro(String limit_pro) {
		this.limit_pro = limit_pro;
	}
	public int getLimit_num() {
		return limit_num;
	}
	public void setLimit_num(int limit_num) {
		this.limit_num = limit_num;
	}
	
	
	public String getTea_num() {
		return tea_num;
	}
	
	public void setTea_num(String tea_num) {
		this.tea_num = tea_num;
	}
	
	
	@Transient
	public boolean isSelect() {
		return select;
	}
	public void setSelect(boolean select) {
		this.select = select;
	}
	
	@Transient
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
}