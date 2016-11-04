package edu.sabanciuniv.deeplearning.repo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import edu.sabanciuniv.deeplearning.model.Tweet;
import edu.sabanciuniv.deeplearning.service.PersistenceManager;

public class TweetRepository {

	private EntityManager em = null;

	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

	public TweetRepository() {
		this.em = PersistenceManager.INSTANCE.getEntityManager();

		// Send SET NAMES query for emoji support
		em.getTransaction().begin();
		em.createNativeQuery("SET NAMES 'utf8mb4'").executeUpdate();
		em.getTransaction().commit();
		em.getTransaction().begin();

		// start the scheduler
		scheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				if (!em.isOpen()) {
					scheduler.shutdown();
					return;
				}

				if (em.getTransaction().isActive()){
					synchronized (em) {
						em.getTransaction().commit();
						em.clear();
						em.getTransaction().begin();
					}
				}
			}
		}, 1, 1, TimeUnit.MINUTES);
	}

	public void addTweet(Tweet tweet) {
		synchronized (em) {
			em.persist(tweet);
		}
	}

	public void closeConnection() {
		em.close();
		PersistenceManager.INSTANCE.close();

		// close scheduler
		scheduler.shutdown();
	}
}
