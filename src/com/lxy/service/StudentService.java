package com.lxy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxy.dao.StudentDao;
import com.lxy.entities.Issue;
import com.lxy.entities.Message;
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
	
	public Map<String,Integer> getStudent_Issue2(String stu_num){
		List<Student_issue> student_Issue = sdao.getStudent_Issue(stu_num);
		
		Map<String,Integer> map = new HashMap<String, Integer>();
		
		for(Student_issue s : student_Issue){
			map.put(s.getIssue_id(), s.getState());
		}
		return map;
		
	}
	
	
	public List<String> getStudent_Issue(String stu_num){
		List<Student_issue> student_Issue = sdao.getStudent_Issue(stu_num);
		List<String> list = new ArrayList<String>();
		
		for(Student_issue s : student_Issue){
			list.add(s.getIssue_id());
		}
		return list;
		
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
		Map<String, Integer> student_Issue = getStudent_Issue2(stu_num);
		List<String> issId = new ArrayList<String>();
		for(Map.Entry<String, Integer> s : student_Issue.entrySet()){
			issId.add(s.getKey());
		}
		
		List<Issue> issueByIdArr = sdao.getIssueByIdArr(issId);
		int index = 0;
		for(Map.Entry<String, Integer> s : student_Issue.entrySet()){
			issueByIdArr.get(index++).setState(s.getValue());
		}
		
		return issueByIdArr;
	}
	
	//删除该学生的课题
	public boolean deleteIssueByStu(String stu_num,String issue_id){
		sdao.deleteIssueByStu(stu_num, issue_id);
		return sdao.updateIssueAddone(Integer.parseInt(issue_id));
	}
	
	public Student getStuByStuNum(String stu_num){
		return sdao.getStuByStuNum(stu_num);
	}
	
	
	//留言的接口i
	public boolean addMessage2Tea(Message m){
		m.setM_time(new Date());
		return sdao.addMessage2Tea(m);
	}

	//查询给该学生的所有留言
	public List<Message> getAllMessageByStuNum(String stu_num){
		return sdao.getAllMessageByStuNum2(stu_num);
	}
	
}
