package com.lxy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lxy.entities.Announcement;
import com.lxy.entities.Student;
import com.lxy.entities.Teacher;
import com.lxy.service.ManagerService;

@RestController
public class ManagerController {
	
	@Autowired
	private ManagerService ms;
	
	@RequestMapping(value="addstu",method=RequestMethod.POST)
	public boolean addStudent(Student stu){
		return ms.addStudent(stu);
	}
	
	
	@RequestMapping(value="getstubypage/{page}/{items}")
	public List<Student> getStudentByPage(@PathVariable int page,@PathVariable int items){
		return ms.getStudentByPage(page, items);
	}
	
	
	@RequestMapping(value="getstucount")
	public int getStudentPages(@RequestParam int items){
		return ms.getStudentPages(items);
	}
	
	
	@RequestMapping(value="addtea",method=RequestMethod.POST)
	public boolean addTeacher(Teacher tea){
		return ms.addTeacher(tea);
	}
	
	
	@RequestMapping(value="getteabypage/{page}/{items}")
	public List<Teacher> getTeacherByPage(@PathVariable int page,@PathVariable int items){
		return ms.getTeacherByPage(page, items);
	}
	
	
	@RequestMapping(value="getteacount")
	public int getTeacherPages(@RequestParam int items){
		return ms.getTeacherPages(items);
	}
	
	
	
	
	@RequestMapping(value="addanno",method=RequestMethod.POST)
	public boolean addAnno(Announcement tea){
		return ms.addAnnouncement(tea);
	}
	
	
	@RequestMapping(value="getannobypage/{page}/{items}")
	public List<Announcement> getAnnouncementByPage(@PathVariable int page,@PathVariable int items){
		return ms.getAnnouncementByPage(page, items);
	}
	
	
	@RequestMapping(value="getannocount")
	public int getAnnouncementPages(@RequestParam int items){
		return ms.getAnnouncementPages(items);
	}
	
	
	@RequestMapping(value="delestu/{id}")
	public boolean deleStu(@PathVariable int id){
		return ms.deleStu(id);
	}
	
	@RequestMapping(value="deletea/{id}")
	public boolean deleTea(@PathVariable int id){
		return ms.deleTea(id);
	}
	
	@RequestMapping(value="deleanno/{id}")
	public boolean deleAnno(@PathVariable int id){
		return ms.deleAnno(id);
	}
	
	//get by id

	@RequestMapping(value="getstubyid/{id}")
	public Student getStuById(@PathVariable int id){
		return ms.getStuById(id);
	}
	
	@RequestMapping(value="getteabyid/{id}")
	public Teacher getTeaById(@PathVariable int id){
		return ms.getTeaById(id);
	}
	
	@RequestMapping(value="getannobyid/{id}")
	public Announcement getAnnoById(@PathVariable int id){
		return ms.getAnnoById(id);
	}
	
	//update by id
	@RequestMapping(value="upstubyid/{id}")
	public boolean updateStu(Student s,@PathVariable int id){
		s.setId(id);
		return ms.updateStu(s);
	}
	
	@RequestMapping(value="upteabyid/{id}")
	public boolean updateTea(Teacher s,@PathVariable int id){
		s.setId(id);
		return ms.updateTea(s);
	}
	
	@RequestMapping(value="upannobyid/{id}")
	public boolean updateAnno(Announcement s,@PathVariable int id){
		s.setId(id);
		return ms.updateAnno(s);
	}

	
}
