package com.dxc.MyDigitalHub.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dxc.MyDigitalHub.entity.User;
import com.dxc.MyDigitalHub.exception.UserNotFoundException;
import com.dxc.MyDigitalHub.repository.UserRepository;

@Component
public class UserServices {
	
	@Autowired
	private UserRepository userRepo;
	
	public ArrayList<User> findAllUsers(){
		ArrayList<User> list = (ArrayList<User>)userRepo.findAll();
		return list;
	}

	public User findUserById(int id) {
		return userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("User " + id + " is not found"));
	}
	
	public User findUserByUsername(String username) {
		return userRepo.findByUsername(username).orElseThrow(()-> new UserNotFoundException("User " + username + " is not found"));
	}
	
	public User findUserByName(String name) {
		return userRepo.findByName(name).orElseThrow(()-> new UserNotFoundException("User " + name + " is not found"));
	}

	public User addUser(User user) {
		User usr = userRepo.save(user);
		return usr;
	}

	public void deleteUser(int id) {
		userRepo.deleteById(id);
	}

	public void updateUserData(User usr, int id) {
		usr.setUserId(id);
		userRepo.save(usr);
	}
}
