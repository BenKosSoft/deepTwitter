package maven.mert.maven_first;

import maven.mert.maven_first.model.Tweet;
import maven.mert.maven_first.model.User;
import maven.mert.maven_first.service.PersistenceManager;

import java.util.List;

import javax.persistence.EntityManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	User user = new User();
    	Tweet tweet = new Tweet();
    	user.setId(null);
        user.setUsername("seren");
        tweet.setId(null);
        tweet.setTweet("Heeeeey!zaa");
        tweet.setUserId(14);
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        em.getTransaction()
            .begin();
       //add
        em.persist(user);
        em.persist(tweet);
        em.getTransaction()
            .commit();
        
        //find
        List<User> users = em.createNamedQuery(User.FIND_ALL_USERS, User.class).getResultList();
        for(User u :users){
        	System.out.println(u.getUsername());
        }
        
        em.close();
        PersistenceManager.INSTANCE.close();
    }
}
