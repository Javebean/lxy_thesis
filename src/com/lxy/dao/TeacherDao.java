package com.lxy.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lxy.entities.Issue;
import com.lxy.entities.Message;
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
	
	//这个是统计老师的课题被学生
	
	public long getCountIssueSelectByStu(String issue_id){
		try{
			String hql = "select count(id) from Student_issue where issue_id = ?";
			long count = (long) getSession().createQuery(hql).setString(0, issue_id).uniqueResult();
			return count;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	
	//查询给该老师的所有留言
	@Deprecated
	@SuppressWarnings("unchecked")
	public List<Message> getAllMessageByTeaNum(String tea_num){
		try{
			String hql = "from Message where tea_id = ?";
			List<Message> list = getSession().createQuery(hql).setString(0, tea_num).list();
			return list;
		}catch(Exception e){
			return null;
		}
	}
	
	
	//查询给该老师的所有留言--新的接口(不包括回复)
	@SuppressWarnings("unchecked")
	public List<Message> getAllMessageByTeaNum2(String tea_num){
		try{
			String hql = "from Message where tea_id = ? and replyId is null";
			List<Message> list = getSession().createQuery(hql).setString(0, tea_num).list();
			return list;
		}catch(Exception e){
			return null;
		}
	}
	
	//查询主题id 的相关回复
	@SuppressWarnings("unchecked")
	public List<Message> getRelativeReplyById(String id){
		try{
			String hql = "from Message where replyId = ? ";
			List<Message> list = getSession().createQuery(hql).setString(0, id).list();
			return list;
		}catch(Exception e){
			return null;
		}
	}
	
	
	
	//更新Message表，插入老师和学生的姓名
	public boolean updateMessage(Message m){
		try{
			getSession().saveOrUpdate(m);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	//老师回复
	@Deprecated
	public boolean updateMessageReply( String id ,String reply){
		try{
			String hql = "update Message set reply_content =? where id =?";
			int ex = getSession().createQuery(hql).setString(0, reply).setInteger(1, Integer.parseInt(id)).executeUpdate();
			if(ex>0){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	
	//老师回复新的接口。上面那个即将废弃
	public boolean updateMessageReply2( String id ,String reply,String replyType){
		try{
			StringBuilder hql = new StringBuilder();
			hql.append("insert into Message (m_time,stu_id,stu_name,tea_id,tea_name,content,replyId,replyType) select ");
			hql.append(":m_time, stu_id, stu_name,tea_id,tea_name, :content, :replyId, :replyType from Message where id= :id");
			
			int ex = getSession().createSQLQuery(hql.toString()).setString("replyType", replyType).setTimestamp("m_time",new Date()).setString("content", reply).setString("replyId", id).setInteger("id", Integer.parseInt(id)).executeUpdate();
			if(ex>0){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	
	
	//删除该留言
	public boolean deleteMessageById(int id){
		try{
			String hql = "delete from Message where id = ?";
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
