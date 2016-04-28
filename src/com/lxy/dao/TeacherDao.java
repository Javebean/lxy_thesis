package com.lxy.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lxy.entities.Issue;
import com.lxy.entities.Teacher;


@Repository
@Transactional
public class TeacherDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Teacher getTeacherByPass(String tea_num,String password){
		try{
			String hql = "from Teacher where tea_num= ? and password = ?";
			Teacher t = (Teacher) getSession().createQuery(hql).setString(0, tea_num).setString(1, password).uniqueResult();
			return t;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Teacher getTeacherByTnum(String tea_num){
		try{
			String hql = "from Teacher where tea_num= ?";
			Teacher t = (Teacher) getSession().createQuery(hql).setString(0, tea_num).uniqueResult();
			return t;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	//添加课题
	public boolean addIssue(Issue issue){
		try{
			getSession().save(issue);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean upIssue(Issue issue){
		try{
			getSession().saveOrUpdate(issue);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteIssueById(int id){
		try{
			
			String hql = "delete from Issue where id = ?";
			int e = getSession().createQuery(hql).setInteger(0, id).executeUpdate();
			if(e>0){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Issue> getIssuerByTname(String tea_num){
		try{
			String hql = "from Issue where tea_num = ?";
			List<Issue> list = getSession().createQuery(hql).setString(0, tea_num).list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Issue getIssueById(int id){
		try{
			String hql = "from Issue where id = ?";
			Issue list = (Issue) getSession().createQuery(hql).setInteger(0, id).uniqueResult();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}
	
	//重新修改老师审核。不通过修改为置状态码为0
	
	public boolean rejectStudent_issueById(int id){
		try{
			String hql = "update Student_issue set state=2 where id = ?";
			int ex = getSession().createQuery(hql).setInteger(0, id).executeUpdate();
			if(ex>0){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	//老师审核不通过
	public boolean deleteStudent_issuebyId(int id){
		try{
			String hql = "delete from Student_issue where id = ?";
			int ex = getSession().createQuery(hql).setInteger(0, id).executeUpdate();
			if(ex>0){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean agreeStudent_issue(int id){
		try{
			String hql = "update Student_issue set state = 1 where id=?";
			int ex = getSession().createQuery(hql).setInteger(0, id).executeUpdate();
			if(ex>0){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
}
