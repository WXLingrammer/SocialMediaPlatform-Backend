package com.dxc.MyDigitalHub.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.MyDigitalHub.entity.Post;
import com.dxc.MyDigitalHub.entity.User;
import com.dxc.MyDigitalHub.exception.PasswordMismatchException;
import com.dxc.MyDigitalHub.exception.UserNotFoundException;
import com.dxc.MyDigitalHub.services.PostServices;
import com.dxc.MyDigitalHub.services.UserServices;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class UserController {
	
	//Injection for UserServices
	@Autowired
	private final UserServices userService;
	
	@Autowired
	private PostServices postService;
	
	public UserController(UserServices userService) {
		this.userService = userService;
	}
	
	//this mapping is going to fetch us all the Users from the database
	@GetMapping("/getUsers/{role}")
	public ArrayList<User> getUsers(@PathVariable("role")String role){
		ArrayList<User> users = new ArrayList<User>();
		if(role.equals("admin")) {
			users = userService.findAllUsers();
			ArrayList<User> newList = new ArrayList<User>();
			if(users.size() > 0) {
				for(User user : users) {
					if(user.getRole().equals("user")) {
						newList.add(user);
					}
				}
				users = newList;
			}
		}else {
			users = new ArrayList<User>();
		}
		return users;
	}
	//only fetches specific User mentioned by the user using ID
	@GetMapping("/getUser/{id}")
	public User getUserById(@PathVariable("id")int id){
		User user = userService.findUserById(id);
		return user;
	}
	
	//only fetches specific User mentioned by the user using name
	@GetMapping("/getName/{name}")
	public User getUserByName(@PathVariable("name")String name){
		User user = userService.findUserByName(name);
		return user;
	}
	
	//only fetches specific User mentioned by the user using username
	@GetMapping("/getUsername/{username}@{password}")
	public User getUserByUsername(@PathVariable("username")String username, @PathVariable("password")String password) throws PasswordMismatchException{
		User user = userService.findUserByUsername(username);
		System.out.println("Password matches: " + this.passwordEncoder().matches(password, user.getPassword()));
		if(this.passwordEncoder().matches(password, user.getPassword())) {
			return user;
		}else {
			throw new PasswordMismatchException("User entered password: " + password + " is not matching with database password: " + user.getPassword());
		}
		
	}
	
	//Insert specific User in JSON format to the database
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody User user) {
		try {
			User userExists = this.getUserByUsername(user.getUsername(), user.getPassword());
			if(userExists==null) {
				String encryptedPwd = this.passwordEncoder().encode(user.getPassword());
				user.setPassword(encryptedPwd);
				userService.addUser(user);
				return new ResponseEntity<String>("User Registration Successful", HttpStatus.OK);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username: " + user.getUsername() + " already exists");
			}
		} catch(UserNotFoundException userNotFoundException) {
			String encryptedPwd = this.passwordEncoder().encode(user.getPassword());
			user.setPassword(encryptedPwd);
			ArrayList<User> list = userService.findAllUsers();
			if(list.size() == 0) {
				user.setRole("admin");
			}else {
				user.setRole("user");
			}
			userService.addUser(user);
			return new ResponseEntity<String>("User Registration Successful", HttpStatus.OK);
		} catch(PasswordMismatchException passwordMismatchException) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(passwordMismatchException.getMessage());
		}
	}
	
	//used to delete User from the database usually specific to ID
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		List<Post> p = postService.findPostByPosterId(id);
		System.out.println(p);
		for(Post x : p){
			postService.deletePost(x.getPostid());
		}
		userService.deleteUser(id);
	}
	
	//update the existing data in the database
	@PutMapping("/updateUser/{id}")
	public User updateUserById(@RequestBody User user, @PathVariable("id")int id) {
		userService.updateUserData(user, id);
		return user;
	}
	
	public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
