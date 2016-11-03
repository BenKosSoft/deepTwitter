package edu.sabanciuniv.deeplearning.repo;

import javax.persistence.EntityManager;

import edu.sabanciuniv.deeplearning.model.Tweet;
import edu.sabanciuniv.deeplearning.service.PersistenceManager;

public class TweetRepository {
	
	EntityManager em = null;
	
	public TweetRepository() {
		this.em = PersistenceManager.INSTANCE.getEntityManager();	
		em.getTransaction().begin();
		em.createNativeQuery("SET NAMES 'utf8mb4'").executeUpdate();
		em.getTransaction().commit();
	}
	
	public void addTweet(Tweet tweet){
		em.getTransaction()
        .begin();
		em.persist(tweet);
//		em.getTransaction()
//        .commit();
	}
	
	public void closeConnection(){
		em.close();
		PersistenceManager.INSTANCE.close();
	}
}
