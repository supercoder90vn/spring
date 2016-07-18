package com.caveofprogramming.spring.web.test.tests;

import javax.sql.DataSource;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.caveofprogramming.spring.web.dao.Message;
import com.caveofprogramming.spring.web.dao.MessagesDao;
import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.dao.OffersDao;
import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.dao.UsersDao;

public abstract class AbstractUserOfferTest {
	@Autowired
	protected UsersDao usersDao;
	@Autowired
	protected OffersDao offersDao;
	@Autowired
	protected MessagesDao messagesDao;
	@Autowired
	private DataSource datasource;
	
	protected User user1 = new User("johnwpurcell","John Purcell","hellothere",
			"john@caveofprogramming.com",true,"ROLE_USER");
	protected User user2 = new User("richardhanny","Richar Hanny","the339step",
			"richard@caveofprogramming.com",true,"ROLE_ADMIN");
	protected User user3 = new User("suetheviloinish","Sue Black","iloveviolins",
			"sue@caveofprogramming.com",true,"ROLE_USER");
	protected User user4 = new User("johnwpurcell2s","John Purcell","hellothere",
			"rog@caveofprogramming.com",false,"user");
	
	protected Offer offer1 = new Offer(user1,"This is a test offer 1.");
	protected Offer offer2 = new Offer(user1,"This is a test offer 2.");
	protected Offer offer3 = new Offer(user2,"This is a test offer 3.");
	protected Offer offer4 = new Offer(user3,"This is a test offer 4.");
	protected Offer offer5 = new Offer(user3,"This is a test offer 5.");
	protected Offer offer6 = new Offer(user3,"This is a test offer 6.");
	protected Offer offer7 = new Offer(user4,"This is a test offer for a user that is not enabled.");
	
	Message message1 = new Message("Test Subject 1", "Test content 1","Issac Newton","isacc@caveofprogramming.com",user1.getUsername());
	Message message2 = new Message("Test Subject 2", "Test content 2","Issac Newton","isacc@caveofprogramming.com",user1.getUsername());
	Message message3 = new Message("Test Subject 3", "Test content 3","Issac Newton","isacc@caveofprogramming.com",user2.getUsername());
	@Before	
	public void init(){
		JdbcTemplate jdbc = new JdbcTemplate(datasource);
		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}
	public void insertToDatabase_users_offers_messages(){
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		
		messagesDao.saveOrUpdate(message1);
		messagesDao.saveOrUpdate(message2);
		messagesDao.saveOrUpdate(message3);
	} 
}
