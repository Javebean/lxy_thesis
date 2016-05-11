package com.lxy.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lxy.entities.Issue;
import com.lxy.entities.Message;
import com.lxy.entities.Student;
import com.lxy.entities.Student_issue;

@Repository
@Transactional
public class StudentDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//根据学号进行登陆
	public Student getStuByUsername(String stu_num,String password){
		Student stu = null;
		try{
			String hql = "from Student where stu_num =? and password = ?";
			stu = (Student) getSession().createQuery(hql).setString(0, stu_num).setString(1, password).uniqueResult();
			return stu;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Student getStuByStuNum(String stu_num){
		Student stu = null;
		try{
			String hql = "from Student where stu_num =?";
			stu = (Student) getSession().createQuery(hql).setString(0, stu_num).uniqueResult();
			return stu;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Issue> getIssuerList(int start,int items){
		try{
			String hql = "from Issue";
			List<Issue> list = getSession().createQuery(hql).setFirstResult(start).setMaxResults(items).list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public long getIssueCount(){
		try{
			String hql = "select count(id) from Issue";
			long c = (long) getSession().createQuery(hql).uniqueResult();
			return c;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public boolean stuSelectIssue(Student_issue si){
		try{
			getSession().save(si);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Student_issue> getStudent_Issue(String stu_num){
		try{
			String hql = "from Student_issue where stu_num = ?";
			List<Student_issue>  list = getSession().createQuery(hql).setString(0, stu_num).list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Student_issue> getStudent_IssueByTeaNum(String tea_num){
		try{
			String hql = "from Student_issue where tea_num = ? and state != 2";
			List<Student_issue>  list = getSession().createQuery(hql).setString(0, tea_num).list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//是课题人数少1
	public boolean updateIssue(int id){
		try{
			String hql = "update Issue set limit_num = limit_num-1 where id=?";
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
	
	//是课题人数少1
	public boolean updateIssueAddone(int id){
		try{
			String hql = "update Issue set limit_num = limit_num+1 where id=?";
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
	
	//根据课题id去查课题‘
	public List<Issue> getIssueByIdArr(List<String> ids){
		try{
			StringBuilder  hql = new StringBuilder("select * from Issue where id in (");
			int len = ids.size();
			for(int i=0;i<len;i++){
				if(i==len-1){
					hql.append(ids.get(i));
					hql.append(")");
				}else{
					hql.append(ids.get(i));
					hql.append(",");
				}
			}
			
			hql.append(" order by field ( id, ");
			
			for(int i=0;i<len;i++){
				if(i==len-1){
					hql.append(ids.get(i));
					hql.append(")");
				}else{
					hql.append(ids.get(i));
					hql.append(",");
				}
			}
			
			
			
			
			
			List<Issue> list = getSession().createSQLQuery(hql.toString()).addEntity(Issue.class).list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	//删除该学生的课题
	public boolean deleteIssueByStu(String stu_num,String issue_id){
		try{
			String hql = "delete from Student_issue where stu_num = ? and issue_id = ? ";
			int ex = getSession().createQuery(hql).setString(0, stu_num).setString(1, issue_id).executeUpdate();
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
	
	

	//留言的接口i
	public boolean addMessage2Tea(Message m){
		try{
			getSession().save(m);
			return true;
		}catch(Exception e){
			return false;
		}
		
	}
	
	
	//查询给该学生的所有留言
		@SuppressWarnings("unchecked")
		public List<Message> getAllMessageByStuNum(String stu_num){
			try{
				String hql = "from Message where stu_id = ?";
				List<Message> list = getSession().createQuery(hql).setString(0, stu_num).list();
				return list;
			}catch(Exception e){
				return null;
			}
		}
		
	//新的接口。不包括回复的留言
	@SuppressWarnings("unchecked")
	public List<Message> getAllMessageByStuNum2(String stu_num){
		try{
			String hql = "from Message where stu_id = ? and replyId is null";
			List<Message> list = getSession().createQuery(hql).setString(0, stu_num).list();
			return list;
		}catch(Exception e){
			return null;
		}
	}
	
}
