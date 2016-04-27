package com.lxy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxy.dao.StudentDao;
import com.lxy.entities.Issue;
import com.lxy.entities.Student;
import com.lxy.entities.Student_issue;

@Service
public class StudentService {

	@Autowired
	private StudentDao sdao;
	
	public boolean getStuByUsername(String stu_num,String password){
		Student s =  sdao.getStuByUsername(stu_num, password);
		if(s!=null){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	public List<Issue> getIssuerList(int page,int items,String stu_num){
		int start = (page-1)*items;
		List<Issue> issues  = sdao.getIssuerList(start, items);
		List<String> student_Issue = getStudent_Issue(stu_num);
		for(Issue i : issues){
			boolean contains = student_Issue.contains(i.getId()+"");
			if(contains){
				i.setSelect(true);
			}else{
				i.setSelect(false);
			}
		}
		return issues;
		
	}
	
	public int  getIssuePages(int items){
		double count = sdao.getIssueCount();
		int page = (int) Math.ceil(count/items);
		return page;
		
	}
	
	public List<String> getStudent_Issue(String stu_num){
		List<Student_issue> student_Issue = sdao.getStudent_Issue(stu_num);
		List<String> result = new ArrayList<String>( );
		for(Student_issue s : student_Issue){
			result.add(s.getIssue_id());
		}
		return result;
		
	}
	
	//学生选择一个课题，并减少一个数量
	public boolean stuSelectIssue(Student_issue si){
		boolean result = false;
		result = sdao.stuSelectIssue(si);
		if(!result){
			return false;
		}
		result = sdao.updateIssue(Integer.parseInt(si.getIssue_id()));
		if(!result){
			return false;
		}
		return true;
	}
	
	public List<Issue> getIssueByIdArr(String stu_num){
		List<String> student_Issue = getStudent_Issue(stu_num);
		List<Issue> issueByIdArr = sdao.getIssueByIdArr(student_Issue);
		return issueByIdArr;
	}
	
	//删除该学生的课题
	public boolean deleteIssueByStu(String stu_num,String issue_id){
		return sdao.deleteIssueByStu(stu_num, issue_id);
	}
	
	public Student getStuByStuNum(String stu_num){
		return sdao.getStuByStuNum(stu_num);
	}

	
}
