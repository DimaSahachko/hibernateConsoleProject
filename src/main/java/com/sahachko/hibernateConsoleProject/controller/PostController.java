package com.sahachko.hibernateConsoleProject.controller;

import java.util.List;

import com.sahachko.hibernateConsoleProject.model.Post;
import com.sahachko.hibernateConsoleProject.service.PostService;


public class PostController {
private PostService service;
	
	public PostController(PostService service) {
		this.service = service;
	}
	
	public Post savePost(String content) {
		Post post = new Post(content);
		return service.savePost(post);
	}
	
	public Post updatePost(long id, String content) {
		Post post = service.getPostById(id);
		if(post == null) {
			return post;
		}
		post.setContent(content);
		return service.updatePost(post);
	}
	
	public List<Post> getAllPosts() {
		return service.getAllPosts();
	}
	
	public Post getPostById(long id) {
		return service.getPostById(id);
	}
	
	public void deletePostById(long id) {
		service.deletePostById(id);
	}
}
