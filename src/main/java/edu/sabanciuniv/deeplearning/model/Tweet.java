package edu.sabanciuniv.deeplearning.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tweets")
public class Tweet {
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "lang")
	private String lang;
	
	@Column(name = "favcount")
	private Integer favCount;
	
	@Column(name = "rtcount")
	private Integer rtCount;
	
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

	public Integer getFavCount() {
		return favCount;
	}

	public void setFavCount(Integer favCount) {
		this.favCount = favCount;
	}

	public Integer getRtCount() {
		return rtCount;
	}

	public void setRtCount(Integer rtCount) {
		this.rtCount = rtCount;
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



