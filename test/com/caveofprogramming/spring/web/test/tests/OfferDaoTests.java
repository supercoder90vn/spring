package com.caveofprogramming.spring.web.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.dao.User;

@ActiveProfiles("dev")
@ContextConfiguration(locations={
		"classpath:com/caveofprogramming/spring/web/config/dao-context.xml",
		"classpath:com/caveofprogramming/spring/web/config/security-context.xml",
		"classpath:com/caveofprogramming/spring/web/test/config/datasource.xml"		
})
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTests extends AbstractUserOfferTest {
	@Test
	public void testDelete(){
		insertToDatabase_users_offers_messages();
		
		
		Offer retrieved1= offersDao.getOffer(offer2.getId());
		assertNotNull("OFfer with ID "+offer2.getId()+" should be null (deleted, actual)", retrieved1);
		
		offersDao.delete(offer2.getId());
		Offer retrieved2= offersDao.getOffer(offer2.getId());
		assertNull("OFfer with ID "+offer2.getId()+" should be null (deleted, actual)", retrieved2);
	}
	
	@Test
	public void testGetById(){
		insertToDatabase_users_offers_messages();
		
		Offer retrieved1 = offersDao.getOffer(offer1.getId());
		assertEquals("Offers should match",offer1,retrieved1);
		
		Offer retrieved2 = offersDao.getOffer(offer7.getId());
		assertNull("Should not retrieve offer for disabled user",retrieved2);
	}
	@Test
	public void testCreateRetrieved(){
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		
		offersDao.create(offer1);
		List<Offer> offers1 = offersDao.getOffers();
		assertEquals("Should be 6 offers for enabled users.",1, offers1.size());
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
		
		List<Offer> offers2 = offersDao.getOffers();
		assertEquals("Should be 6 offers for enabled users.",6, offers2.size());
	}
	@Test
	public void testUpdate(){
		insertToDatabase_users_offers_messages();
		
		offer3.setText("This offer has updated text.");
		offersDao.saveOrUpdate(offer3);
		
		Offer retrieved = offersDao.getOffer(offer3.getId());
		assertEquals("Retrieved Offer should be updated", retrieved,offer3);
		System.out.println(retrieved.getText());
	}
	@Test
	public void testGetUsername(){
		insertToDatabase_users_offers_messages();
		
		List<Offer> offers1= offersDao.getOffers(user3.getUsername());
		assertEquals("should be 3 offers for this user",3,offers1.size());
		
		List<Offer> offers2= offersDao.getOffers("fdfdfdf");
		assertEquals("should be 0 offers for this user",0,offers2.size());
		
		List<Offer> offers3= offersDao.getOffers(user2.getUsername());
		assertEquals("should be 0 offers for this user",1,offers3.size());
	}
	@Test
	public void testOffers(){
		User user = new User("johnwpdl aaa","John Purcell","hellothere",
				"john@caveofprogramming.com",true,"user");
		usersDao.create(user);
		
		Offer offer = new Offer(user,"this is a test offers 1.");		
		offersDao.create(offer);
		List<Offer> offers= offersDao.getOffers();
		assertEquals("Should be one offer in database.", 1,offers.size());
		assertEquals("Retrieved offer should be identical to retrieved user.", offer, offers.get(0));
		
		//Get the offer with ID filled in.
		offer = offers.get(0);
		
		offer.setText("Updated offer text 2.");
		offersDao.saveOrUpdate(offer);
		
		Offer updated = offersDao.getOffer(offer.getId());
		
		assertEquals("Updated offer should match retrieved updated offer", offer, updated);
		
		
		
		//Test get by ID//
		Offer offer2 = new Offer(user,"This is a test offer 3");
		offersDao.create(offer2);
		
		List<Offer> userOffers = offersDao.getOffers(user.getUsername());
		System.out.println("__size: "+userOffers.size());
		for(Offer uo: userOffers){
			System.out.println("__userOffer: "+ uo);
		}
		assertEquals("Should be tw offers for users.",2,userOffers.size());
		
		
		
		List<Offer> secondList = offersDao.getOffers();
		
		for(Offer current:secondList){
			Offer retrieved = offersDao.getOffer(current.getId());
			assertEquals("Offer b ID should match offer from list.",current, retrieved);
		}
		//Test deletion
		offersDao.delete(offer.getId());
		
		List<Offer> empty = offersDao.getOffers();
		
		assertEquals("Offers lists should be empty",1, empty.size());
	}
}
