package com.dxc.MyDigitalHub.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dxc.MyDigitalHub.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	Optional<User> findByUsername(String username);
	Optional<User> findById(int id);
	Optional<User> findByName(String name);
}
