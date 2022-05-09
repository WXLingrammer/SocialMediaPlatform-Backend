package com.dxc.MyDigitalHub.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.MyDigitalHub.entity.Post;
import com.dxc.MyDigitalHub.services.PostServices;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class PostController {
//	//Injection for PostServices
	@Autowired
	private final PostServices postService;
	public PostController(PostServices postService) {
		this.postService = postService;
	}
	//this mapping is going to fetch us all the Posts from the database
	@GetMapping("/getPosts")
	public ArrayList<Post> getPosts(){
		ArrayList<Post> posts = postService.findAllPosts();
		System.out.println("Posts: " + posts);
		return posts;
	}
	//only fetches specific Post mentioned by the post using ID
	@GetMapping("/getPost/{id}")
	public Post getPostById(@PathVariable("id")int id){
		Post post = postService.findPostById(id);
		System.out.println("Post: " + post);
		return post;
	}

	//Insert specific Post in JSON format to the database
	@PostMapping("/addPost")
	public Post addPost(@RequestBody Post post){
		Post newPost = postService.addPost(post);
		return newPost;
	}

	//used to delete Post from the database usually specific to ID
	@DeleteMapping("/deletePost/{id}")
	public void deletePost(@PathVariable("id") int id) {
		postService.deletePost(id);
	}

	//update the existing data in the database
	@PutMapping("/updatePost/{id}")
	public Post updatePostById(@RequestBody Post post, @PathVariable("id")int id) {
		postService.updatePostData(post, id);
		return post;
	}
}
