package dto.model;

import javax.xml.bind.annotation.XmlRootElement;

import enums.Role;

/**
 * 
 * @author s124259
 *
 */
@XmlRootElement
public class User {
	private String userName;
	private String email;
	private String firstName;
	private String lastName;
	private Role role;
	private String password;
	private long lastLogin;
	
	public User(){
		
	}
	
	public User(String userName, String email, String firstName,
			String lastName, Role role, String password, long lastLogin) {
		super();
		this.userName = userName;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.password = password;
		this.lastLogin = lastLogin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(long lastLogin) {
		this.lastLogin = lastLogin;
	}
}
