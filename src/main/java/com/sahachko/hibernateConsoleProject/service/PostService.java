package com.sahachko.hibernateConsoleProject.service;

import java.util.List;

import com.sahachko.hibernateConsoleProject.model.Post;

public interface PostService {
	
	Post savePost(Post post);
	
	Post updatePost(Post post);
	
	Post getPostById(long id);
	
	List<Post> getAllPosts();
	
	void deletePostById(long id);
}
