package com.dxc.MyDigitalHub.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dxc.MyDigitalHub.entity.Post;
import com.dxc.MyDigitalHub.exception.PostNotFoundException;
import com.dxc.MyDigitalHub.repository.PostRepository;

@Component
public class PostServices {
	@Autowired
	private PostRepository postRepo;
	
	public ArrayList<Post> findAllPosts(){
		ArrayList<Post> list = (ArrayList<Post>)postRepo.findAll();
		list.sort((p1, p2) -> p2.getCreatedDate().compareTo(p1.getCreatedDate()));
		return list;
	}
	
	public Post findPostById(int id) {
		return postRepo.findById(id).orElseThrow(()-> new PostNotFoundException("Post " + id + " is not found"));
	}
	
	public List<Post> findPostByPosterId(int id) {
		return postRepo.findByPosterId(id);
	}
	
	public Post addPost(Post post) {
		Post p = postRepo.save(post);
		return p;
	}

	public void deletePost(int id) {
		postRepo.deleteById(id);
	}

	public void updatePostData(Post post, int id) {
		post.setPostid(id);
		postRepo.save(post);
	}
}
 