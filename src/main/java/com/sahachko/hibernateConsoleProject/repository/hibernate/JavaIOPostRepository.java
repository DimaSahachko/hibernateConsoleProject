package com.sahachko.hibernateConsoleProject.repository.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.hibernate.Session;

import com.sahachko.hibernateConsoleProject.model.Post;
import com.sahachko.hibernateConsoleProject.repository.ConnectionUtils;
import com.sahachko.hibernateConsoleProject.repository.PostRepository;

public class JavaIOPostRepository implements PostRepository {

	@Override
	public List<Post> getAll() {
		List<Post> allPosts = new ArrayList<>();
		try(Session session = ConnectionUtils.getSessionFactory().openSession()) {
			session.beginTransaction();
			allPosts = session.createQuery("from Post", Post.class).getResultList();
			session.getTransaction().commit();
		}
		return allPosts;
	}

	@Override
	public Post getById(Long id) {
		Post post = null;
		try(Session session = ConnectionUtils.getSessionFactory().openSession()) {
			session.beginTransaction();
			post = session.get(Post.class, id);
			session.getTransaction().commit();
		}
		return post;
	}

	@Override
	public Post save(Post post) {
		try(Session session = ConnectionUtils.getSessionFactory().openSession()) {
			session.beginTransaction();
			post.setCreated(new Date());
			session.save(post);
			session.getTransaction().commit();
		}
		return post;
	}

	@Override
	public Post update(Post post) {
		try(Session session = ConnectionUtils.getSessionFactory().openSession()) {
			session.beginTransaction();
			post.setUpdated(new Date());
			session.update(post);
			session.getTransaction().commit();
		} catch(OptimisticLockException exc) {
			return null;
		}
		return post;
	}

	@Override
	public void deleteById(Long id) {
		try(Session session = ConnectionUtils.getSessionFactory().openSession()) {
			Post post = getById(id);
			if (post == null) {
				System.out.println("There is no region with such id");
			} else {
				session.beginTransaction();
				session.delete(post);
				session.getTransaction().commit();
			}
		}
	}

}
