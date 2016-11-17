package edu.sabanciuniv.deeplearning.repo;

import java.util.List;
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
//		scheduler.scheduleAtFixedRate(new Runnable() {
//			@Override
//			public void run() {
//				if (!em.isOpen()) {
//					scheduler.shutdown();
//					return;
//				}
//
//				if (em.getTransaction().isActive()) {
//					synchronized (em) {
//						try {
//							// System.out.println("Commited!");
//							em.getTransaction().commit();
//						} catch (Exception e) {
//							System.err.println("Scheduler gives an error, during commit.");
//							System.err.println(e.getMessage());
//						} finally {
//							em.clear();
//							em.getTransaction().begin();
//						}
//					}
//				}
//			}
//		}, 1, 1, TimeUnit.MINUTES);
	}

	public void addTweet(Tweet tweet) {
		synchronized (em) {
			try {
				if (em.getTransaction().isActive())
					em.merge(tweet); // persist gives an error, when there is
										// duplicate... It cannot be done
										// actually !
				else {
					System.err.println("[WARNING]: Transaction is inactive. Reinitilizing the transaction.");
					em.getTransaction().begin();
				}
			} catch (Exception e) {
				System.err.println("em.merge(tweet) gives an error!");
				System.err.println(e.getMessage());
			}
		}
	}

	public List<Object[]> getAllTweetTexts() {
		List<Object[]> results = em.createNamedQuery(Tweet.GET_ALL_TWEETS_TEXT, Object[].class).getResultList();
		return results;
	}

	public List<Object[]> getTweetsBatch(int offset) {
		List<Object[]> results = em.createNamedQuery(Tweet.GET_ALL_TWEETS_TEXT, Object[].class)
				.setFirstResult(offset).setMaxResults(Tweet.BATCH_SIZE).getResultList();
		return results;
	}
	
	public long getTweetCount(){
		Long result = em.createNamedQuery(Tweet.GET_ENTRY_COUNT, Long.class)
						.getSingleResult();
		return result;
	}

	public void closeConnection() {
		em.close();
		PersistenceManager.INSTANCE.close();

		// close scheduler
		scheduler.shutdown();
	}
}
