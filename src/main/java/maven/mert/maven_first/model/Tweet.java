package maven.mert.maven_first.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tweets")
public class Tweet {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	private String tweet;
	
	@Column(name = "user_id")
	private Integer userId;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public Tweet setId(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * @return the tweet
	 */
	public String getTweet() {
		return tweet;
	}

	/**
	 * @param tweet
	 *            the tweet to set
	 */
	public Tweet setTweet(String tweet) {
		this.tweet = tweet;
		return this;
	}

	public Integer getUserId() {
		return userId;
	}

	public Tweet setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
}

