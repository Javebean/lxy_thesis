package com.lxy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxy.dao.StudentDao;
import com.lxy.dao.TeacherDao;
import com.lxy.entities.CheckIssue;
import com.lxy.entities.Issue;
import com.lxy.entities.Student;
import com.lxy.entities.Student_issue;
import com.lxy.entities.Teacher;

@Service
public class TeacherService {

	@Autowired
	private TeacherDao tdao;
	
	@Autowired
	private StudentDao sdao;
	
	public boolean getTeacherByPass(String tea_num,String password){
		 Teacher t = tdao.getTeacherByPass(tea_num, password);
		 if(t!=null){
			 return true;
		 }else{
			 return false;
		 }
	}
	
	public Teacher getTeacherByTnum(String tea_num){
		return tdao.getTeacherByTnum(tea_num);
	}
	
	
	public boolean addIssue(Issue issue){
		return tdao.addIssue(issue);
	}
	
	public List<Issue> getIssuerByTname(String tea_num){
		return tdao.getIssuerByTname(tea_num);
	}
	
	public Issue getIssueById(int id){
		return tdao.getIssueById(id);
	}
	
	public boolean upIssue(Issue issue){
		return tdao.upIssue(issue);
	}
	
	public boolean deleteIssueById(int id){
		return tdao.deleteIssueById(id);
	}
	
	//获取该老师的课题被选情况
	public List<CheckIssue> getCheckIssue(String tea_num){
		List<Student_issue> si = sdao.getStudent_IssueByTeaNum(tea_num);
		List<CheckIssue> result = new ArrayList<CheckIssue>();
		CheckIssue ci = null;
		
		for(Student_issue s : si){
			Issue issueById = tdao.getIssueById(Integer.parseInt(s.getIssue_id()));
			Student st = sdao.getStuByStuNum(s.getStu_num());
			ci = new CheckIssue();
			ci.setId(s.getId());
			ci.setIss_name(issueById.getI_name());
			ci.setState(s.getState()==0?"未审核":"已通过");
			ci.setStu_name(st.getName());
			ci.setStu_num(st.getStu_num());
			ci.setStu_pro(st.getProfession());
			ci.setStu_sex(st.getSex());
			result.add(ci);
		}
		return result;
		
	}
	
	//审核不通过
	public boolean deleteStudent_issuebyId(int id){
		//return tdao.deleteStudent_issuebyId(id);
		return tdao.rejectStudent_issueById(id);
	}
	
	//审核通过
	public boolean agreeStudent_issue(int id){
		return tdao.agreeStudent_issue(id);
	}
	
	
	public Map<String,Integer> countIssueSelectByStu(String tea_num){
		Map<String,Integer> result = new HashMap<String, Integer>();
		List<Issue> issuerByTname = tdao.getIssuerByTname(tea_num);
		for(Issue i : issuerByTname){
			long c = tdao.getCountIssueSelectByStu(i.getId()+"");
			result.put(i.getI_name(), (int)c);
		}
		return result;
	}
	
}
