package com.lxy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxy.dao.ManagerDao;
import com.lxy.entities.Announcement;
import com.lxy.entities.Manager;
import com.lxy.entities.Student;
import com.lxy.entities.Teacher;

@Service
public class ManagerService {

	@Autowired
	private ManagerDao md;
	
	public boolean getManager(String username,String password){
		 Manager manager = md.getManager(username, password);
		
		 if(manager!=null){
			 return true;
		 }else{
			 return false;
		 }
	}
	
	
	public boolean addStudent(Student stu){
		return md.addStudent(stu);
	}
	
	public boolean deleStu(int id){
		return md.deleteStu(id);
	}
	
	public boolean deleTea(int id){
		return md.deleteTea(id);
	}
	
	public boolean deleAnno(int id){
		return md.deleteAnno(id);
	}
	
	
	public List<Student> getStudentByPage(int page,int items){
		int start = (page-1)*items;
		List<Student> list = md.getStudentByPage(start, items);
		return list;
	}
	
	public int getStudentPages(int items){
		double count = md.getStudentCount();
		int pages = (int) Math.ceil(count/items);
		return pages;
	}
	
	
	public boolean addTeacher(Teacher tea){
		return md.addTeacher(tea);
	}
	
	public List<Teacher> getTeacherByPage(int page,int items){
		int start = (page-1)*items;
		List<Teacher> list = md.getTeacherByPage(start, items);
		return list;
	}
	
	public int getTeacherPages(int items){
		double count = md.getTeacherCount();
		int pages = (int) Math.ceil(count/items);
		return pages;
	}
	
	//公告
	public boolean addAnnouncement(Announcement tea){
		return md.addAnno(tea);
	}
	
	public List<Announcement> getAnnouncementByPage(int page,int items){
		int start = (page-1)*items;
		List<Announcement> list = md.getAnnoByPage(start, items);
		return list;
	}
	
	public int getAnnouncementPages(int items){
		double count = md.getAnnouncementCount();
		int pages = (int) Math.ceil(count/items);
		return pages;
	}
	
	
	//get by id
	
	public Student getStuById(int id){
		return md.getStuById(id);
	}
	
	public Teacher getTeaById(int id){
		return md.getTeaById(id);
	}
	
	public Announcement getAnnoById(int id){
		return md.getAnnoById(id);
	}
	
	
	//update by id
	public boolean updateStu(Student s){
		return md.updateStu(s);
	}
	public boolean updateTea(Teacher s){
		return md.updateTea(s);
	}
	public boolean updateAnno(Announcement s){
		return md.updateAnno(s);
	}
	
	
	
	
}
