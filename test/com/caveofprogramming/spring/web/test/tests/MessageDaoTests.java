package com.caveofprogramming.spring.web.test.tests;



import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caveofprogramming.spring.web.dao.Message;

@ActiveProfiles("dev")
@ContextConfiguration(locations={
		"classpath:com/caveofprogramming/spring/web/config/dao-context.xml",
		"classpath:com/caveofprogramming/spring/web/config/security-context.xml",
		"classpath:com/caveofprogramming/spring/web/test/config/datasource.xml"		
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDaoTests extends AbstractUserOfferTest {
	@Test
	public void testSave(){
		insertToDatabase_users_offers_messages();	
		
		List<Message> messages = messagesDao.getMessages(user1.getUsername());
		assertEquals(2,messages.size());
		
		messages = messagesDao.getMessages(user2.getUsername());
		assertEquals(1,messages.size());
	}
	
	@Test
	public void testRetrieveById(){
		insertToDatabase_users_offers_messages();
		
		List<Message> messages = messagesDao.getMessages(user1.getUsername());
		
		for(Message message:messages){
			Message retrieved = messagesDao.getMessage(message.getId());
			assertEquals(message, retrieved);
		}
	}
	@Test
	public void testDelete(){
		insertToDatabase_users_offers_messages();
		
		List<Message> messages = messagesDao.getMessages(user1.getUsername());
		
		for(Message message:messages){
			Message retrieved = messagesDao.getMessage(message.getId());
			assertEquals(message, retrieved);
		}
		
		Message toDelete = messages.get(1);
		
		assertNotNull("This message not deleted yet.",messagesDao.getMessage(toDelete.getId()));
		
		messagesDao.delete(toDelete.getId());
		
		assertNull("This message was deleted",messagesDao.getMessage(toDelete.getId()));
	}
}
