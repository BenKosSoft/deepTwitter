package maven.mert.maven_first.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NamedQuery(
	    name=User.FIND_ALL_USERS,
	    query="SELECT u FROM User u"
	)
public class User {
	
	public static final String FIND_ALL_USERS = "findAllUsers";
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	private String username;

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
	public User setId(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public User setUsername(String username) {
		this.username = username;
		return this;
	}
}
