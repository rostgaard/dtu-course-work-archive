package entity.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import enums.Role;

@Entity
public class UserEntity implements Serializable {
	
	@Id
	private String userName;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private Role role;
	
	private String password;
	
	private static final long serialVersionUID = 1L;
	
	public UserEntity(){
		super();
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
	


}
