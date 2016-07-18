package com.caveofprogramming.spring.web.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caveofprogramming.spring.web.dao.User;

@ActiveProfiles("dev")
@ContextConfiguration(locations={
		"classpath:com/caveofprogramming/spring/web/config/dao-context.xml",
		"classpath:com/caveofprogramming/spring/web/config/security-context.xml",
		"classpath:com/caveofprogramming/spring/web/test/config/datasource.xml"		
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests extends AbstractUserOfferTest {	
	@Test
	public void testCreateRetrieve(){
		usersDao.create(user1);
		List<User> users1 =usersDao.getAllUsers();
		assertEquals("One user should have been created and retrieved",1, users1.size());
		assertEquals("Inserted user should match retrieved",user1, users1.get(0));
		
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		List<User> users2 = usersDao.getAllUsers();
		assertEquals("Should be four retrieved users",4,users2.size());
	}
	@Test
	public void testExists(){
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		
		
		assertTrue("User should exist.",usersDao.exists(user2.getUsername()));
		assertFalse("User should not exist.",usersDao.exists("sdfsfdeeef"));
	}
	// TODO - Reimplement this
	@Test
	public void testUsers(){
		//assertEquals("Dumy test", 1,1);
		User user = new User("johnwpurcell","John Purcell","hellothere",
				"john@caveofprogramming.com",true,"user");
		
		usersDao.create(user);
		
		List<User> users= usersDao.getAllUsers();
		
		assertEquals("Number of users should be 1.", 1,users.size());
		
		assertTrue("User should exist.",usersDao.exists(user.getUsername()));
		assertFalse("User should not exist.",usersDao.exists("sdfsfdeeef"));
		
		assertEquals("Created user should be identical to retrieved user",
				user, users.get(0));
	}
}
