package com.lxy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lxy.entities.Issue;
import com.lxy.entities.Student;
import com.lxy.entities.Student_issue;
import com.lxy.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService ss;
	
	@RequestMapping(value="getissuebypage/{page}/{items}")
	public List<Issue> getIssuerList(@PathVariable int page,@PathVariable int items,String stu_num){
		return ss.getIssuerList(page, items,stu_num);
	}
	
	@RequestMapping(value="getissuepages")
	public int  getIssuePages(@RequestParam int items){
		return ss.getIssuePages(items);
		
	}
	
	@RequestMapping(value="stuselectissue")
	public boolean stuSelectIssue(Student_issue si){
		return ss.stuSelectIssue(si);
	}
	
	@RequestMapping(value="getstuissue")
	public List<String> getStudent_Issue(String stu_num){
		return ss.getStudent_Issue(stu_num);
	}
	
	
	//获取该学生的选题
	@RequestMapping(value="getstuissues/{stu_num}")
	public List<Issue> getIssueByIdArr(@PathVariable String stu_num){
		 List<Issue> issueByIdArr = ss.getIssueByIdArr(stu_num);
		 return issueByIdArr;
	}
	
	
	//删除该学生的课题
	@RequestMapping(value="delestuissue")
	public boolean deleteIssueByStu(String stu_num,String issue_id){
		return ss.deleteIssueByStu(stu_num, issue_id);
	}
	
	@RequestMapping(value="getdetailstu")
	public Student getStuByStuNum(String stu_num){
		return ss.getStuByStuNum(stu_num);
	}
}
