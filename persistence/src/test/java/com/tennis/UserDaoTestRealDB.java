package com.tennis;

import com.tennis.configuration.Config;
import com.tennis.domain.User;
import com.tennis.persistence.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class UserDaoTestRealDB {

	int userID = 1;
	@Autowired
	private UserDAO userDAO;

	@Test
	public void testGetByIdExpectedNoUserFound() {
		User user = userDAO.getById(userID);
		System.out.println(user);
	}

	@Test
	public void testInsertUser() {
		User user = new User("solyap", "some");
		user.setBirthDate("768976");
		user.setEmail("ghjk");
		user.setSex("M");
		userDAO.create(user);
		user.setUserName("solya");
		user.setPassword("password");
		userDAO.create(user);
	}

	@Test
	public void testGetById() {
		User user = userDAO.getById(userID);
		System.out.println(user);
	}

	@Test
	public void testGetAll() {
		List<User> users = new ArrayList<User>();
		users = userDAO.getAll();
	}

	@Test
	public void testDeleteUser() {
		userDAO.delete(userID);
	}
}