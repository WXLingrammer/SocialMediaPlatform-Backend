package com.dxc.MyDigitalHub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dxc.MyDigitalHub.entity.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {
	public List<Post> findByPosterId(int userid);
	Optional<Post> findById(int id);
}
