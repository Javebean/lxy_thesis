package com.lxy.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lxy.dao.StudentDao;
import com.lxy.dao.TeacherDao;
import com.lxy.entities.Issue;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class TestDao {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Autowired
	private ComboPooledDataSource datasource;
	@Test
	public void testDbsource(){
		System.out.println(datasource);
	}

	@Autowired
	private StudentDao sdao;
	
	@Test
	public void testgetIssueByIdArr(){
		List<String> ids = new ArrayList<String>();
		ids.add("4");
		ids.add("2");
		ids.add("3");
		List<Issue> issueByIdArr = sdao.getIssueByIdArr(ids);
		for(Issue i :issueByIdArr){
			System.out.println(i.getI_name());
		}
	}
	
	@Autowired
	private TeacherDao tdao;
	
}
