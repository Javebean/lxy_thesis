package com.lxy.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class Message {

	
	private int id;
	private String stu_id;
	private String stu_name;
	private String tea_id;
	private String tea_name;
	private Date m_time;
	private String content;//内容
	private String reply_content;//老师回复的内容
	
	@GenericGenerator(name="a",strategy="native")
	@GeneratedValue(generator="a")
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReply_content() {
		return reply_content;
	}
	
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	
	
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getTea_id() {
		return tea_id;
	}
	public void setTea_id(String tea_id) {
		this.tea_id = tea_id;
	}
	public String getTea_name() {
		return tea_name;
	}
	public void setTea_name(String tea_name) {
		this.tea_name = tea_name;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getM_time() {
		return m_time;
	}
	public void setM_time(Date m_time) {
		this.m_time = m_time;
	}
	
	
}
