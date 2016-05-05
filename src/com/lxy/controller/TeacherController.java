package com.lxy.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lxy.entities.CheckIssue;
import com.lxy.entities.Issue;
import com.lxy.entities.Teacher;
import com.lxy.service.TeacherService;

@RestController
public class TeacherController {
	
	@Autowired
	private TeacherService ts;
	
	@RequestMapping(value="getteabynum/{tea_num}")
	public Teacher getTeacherByTnum(@PathVariable String tea_num){
		return ts.getTeacherByTnum(tea_num);
	}
	
	
	@RequestMapping(value="addissue/{tea_num}")
	public boolean addIssue(Issue issue,@PathVariable String tea_num){
		issue.setTea_num(tea_num);
		return ts.addIssue(issue);
	}
	
	@RequestMapping(value="getissuebytea/{tea_num}")
	public List<Issue> getIssuerByTname(@PathVariable String tea_num){
		return ts.getIssuerByTname(tea_num);
	}
	
	@RequestMapping(value="getissuebyid/{id}")
	public Issue getIssueById(@PathVariable int id){
		return ts.getIssueById(id);
	}
	
	@RequestMapping(value="upissue/{id}")
	public boolean upIssue(Issue issue,@PathVariable int id){
		issue.setId(id);
		return ts.upIssue(issue);
	}
	
	@RequestMapping(value="deleissue/{id}")
	public boolean deleteIssueById(@PathVariable int id){
		return ts.deleteIssueById(id);
	}
	
	//获取该老师的课题被选情况
	@RequestMapping(value="getcheckissue/{tea_num}")
	public List<CheckIssue> getCheckIssue(@PathVariable String tea_num){
		return ts.getCheckIssue(tea_num);
	}
	
	//审核不通过
	@RequestMapping(value="deleteStudent_issuebyId/{id}")
	public boolean deleteStudent_issuebyId(@PathVariable int id){
		return ts.deleteStudent_issuebyId(id);
	}
	
	//审核通过
	@RequestMapping(value="agreestudent_issue/{id}")
	public boolean agreeStudent_issue(@PathVariable int id){
		return ts.agreeStudent_issue(id);
	}
	
	//统计老师课题被选的情况
	@RequestMapping(value="countissueselectbystu/{tea_num}")
	public Map<String,Integer> countIssueSelectByStu(@PathVariable String tea_num){
		return ts.countIssueSelectByStu(tea_num);
	}
}
