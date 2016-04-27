package com.lxy.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lxy.entities.Announcement;
import com.lxy.entities.Manager;
import com.lxy.entities.Student;
import com.lxy.entities.Teacher;

@Repository
@Transactional
public class ManagerDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//根据学号进行登陆
	public Manager getManager(String username,String password){
		Manager man = null;
		try{
			String hql = "from Manager where username =? and password = ?";
			man = (Manager) getSession().createQuery(hql)
					.setString(0, username).setString(1, password).uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return man;
	}
	
	
	//添加学生
		public boolean addStudent(Student stu){
			try{
				getSession().save(stu);
				return true;
			}catch(Exception e){
				return false;
			}
		}
		
		public boolean deleteStu(int id){
			try{
				String hql = "delete from Student where id = ?";
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
		
		
		public Student getStuById(int id){
			try{
				String hql = "from Student where id = ?";
				Student s = (Student) getSession().createQuery(hql).setInteger(0, id).uniqueResult();
				return s;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		
		
		
		@SuppressWarnings("unchecked")
		public List<Student> getStudentByPage(int start,int items){
			try{
				String hql = "from Student";
				return getSession().createQuery(hql).setFirstResult(start).setMaxResults(items).list();
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		
		public long getStudentCount(){
			try{
				String hql = "select count(id) from Student";
				long count = (long) getSession().createQuery(hql).uniqueResult();
				return count;
			}catch(Exception e){
				return 0;
			}
		}
		
		
		//添加教师
				public boolean addTeacher(Teacher tea){
					try{
						getSession().save(tea);
						return true;
					}catch(Exception e){
						return false;
					}
				}
				
				
				public Teacher getTeaById(int id){
					try{
						String hql = "from Teacher where id = ?";
						Teacher s = (Teacher) getSession().createQuery(hql).setInteger(0, id).uniqueResult();
						return s;
					}catch(Exception e){
						e.printStackTrace();
						return null;
					}
				}
				
				
				public boolean deleteTea(int id){
					try{
						String hql = "delete from Teacher where id = ?";
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
				
				@SuppressWarnings("unchecked")
				public List<Teacher> getTeacherByPage(int start,int items){
					try{
						String hql = "from Teacher";
						return getSession().createQuery(hql).setFirstResult(start).setMaxResults(items).list();
					}catch(Exception e){
						e.printStackTrace();
						return null;
					}
				}
				
				public long getTeacherCount(){
					try{
						String hql = "select count(id) from Teacher";
						long count = (long) getSession().createQuery(hql).uniqueResult();
						return count;
					}catch(Exception e){
						return 0;
					}
				}
		
				
				
				//添加公告
				public boolean addAnno(Announcement tea){
					try{
						getSession().save(tea);
						return true;
					}catch(Exception e){
						return false;
					}
				}
				
				
				public Announcement getAnnoById(int id){
					try{
						String hql = "from Announcement where id = ?";
						Announcement s = (Announcement) getSession().createQuery(hql).setInteger(0, id).uniqueResult();
						return s;
					}catch(Exception e){
						e.printStackTrace();
						return null;
					}
				}
				
				
				public boolean deleteAnno(int id){
					try{
						String hql = "delete from Announcement where id = ?";
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
				
				@SuppressWarnings("unchecked")
				public List<Announcement> getAnnoByPage(int start,int items){
					try{
						String hql = "from Announcement";
						return getSession().createQuery(hql).setFirstResult(start).setMaxResults(items).list();
					}catch(Exception e){
						e.printStackTrace();
						return null;
					}
				}
				
				public long getAnnouncementCount(){
					try{
						String hql = "select count(id) from Announcement";
						long count = (long) getSession().createQuery(hql).uniqueResult();
						return count;
					}catch(Exception e){
						return 0;
					}
				}
				
		
				//update by id
				
				public boolean updateStu(Student s){
					try{
						getSession().saveOrUpdate(s);
						return true;
					}catch(Exception e){
						e.printStackTrace();
						return false;
					}
				}
				public boolean updateTea(Teacher s){
					try{
						getSession().saveOrUpdate(s);
						return true;
					}catch(Exception e){
						e.printStackTrace();
						return false;
					}
				}
				public boolean updateAnno(Announcement s){
					try{
						getSession().saveOrUpdate(s);
						return true;
					}catch(Exception e){
						e.printStackTrace();
						return false;
					}
				}
				
}
