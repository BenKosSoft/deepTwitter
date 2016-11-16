package edu.sabanciuniv.deeplearning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(name=Tweet.GET_ALL_TWEETS_TEXT,
                query="SELECT t.text FROM Tweet t"),
    @NamedQuery(name=Tweet.GET_ENTRY_COUNT,
    			query="SELECT COUNT(*) FROM Tweet")
})
@Table(name = "tweets")
public class Tweet {
	
	public static final String GET_ALL_TWEETS_TEXT = "GET_ALL_TWEETS_TEXT";
	public static final String GET_ENTRY_COUNT = "GET_ENTRY_COUNT";
	public static final Integer BATCH_SIZE = 1000;
	
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "lang")
	private String lang;
	
	@Column(name = "followers")
	private Integer followers;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "createdat")
	private Long createdat;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Integer getFollowers() {
		return followers;
	}

	public void setFollowers(Integer followers) {
		this.followers = followers;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getCreatedat() {
		return createdat;
	}

	public void setCreatedat(Long createdat) {
		this.createdat = createdat;
	}
}