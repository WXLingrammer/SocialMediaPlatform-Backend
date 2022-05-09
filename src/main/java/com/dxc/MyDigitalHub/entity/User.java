package com.dxc.MyDigitalHub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

//mapping entity to table called as 'User'
@Entity
@Table(name="User")
public class User extends UserObj<String> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
    private int userId;
	
	@Column(name="username", unique=true)
    @NotBlank(message = "Username is required")
    private String username;
    
	@Column(name="password")
    @NotBlank(message = "Password is required")
    private String password;
    
	@Column(name="email")
	@Email
    @NotBlank(message = "Email is required")
    private String email;
    
    @Column(name="name")
    private String name;
    
    @Column(name="role")
    private String role;

	public User() {
		
	}

	public User(@NotBlank(message = "Username is required") String username,
			@NotBlank(message = "Password is required") String password,
			@Email @NotBlank(message = "Email is required") String email, String name, String role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.role = role;
	}

	//setters and getter
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", name=" + name + ", role=" + role + "]";
	}

	
	
}
