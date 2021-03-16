package com.sahachko.hibernateConsoleProject.repository.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.hibernate.Session;

import com.sahachko.hibernateConsoleProject.model.User;
import com.sahachko.hibernateConsoleProject.repository.ConnectionUtils;
import com.sahachko.hibernateConsoleProject.repository.UserRepository;

public class JavaIOUserRepository implements UserRepository {

	@Override
	public List<User> getAll() {
		List<User> allUsers = new ArrayList<>();
		try(Session session = ConnectionUtils.getSessionFactory().openSession()) {
			session.beginTransaction();
			allUsers = session.createQuery("select distinct u from User u join fetch u.region left join fetch u.posts", User.class).getResultList();
			session.getTransaction().commit();
		}
		return allUsers;
	}

	@Override
	public User getById(Long id) {
		User user = null;
		try(Session session = ConnectionUtils.getSessionFactory().openSession()) {
			session.beginTransaction();
			user = session.get(User.class, id);
			session.getTransaction().commit();
		}
		return user;
	}

	@Override
	public User save(User user) {
		try(Session session = ConnectionUtils.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}
		return user;
	}

	@Override
	public User update(User user) {
		try(Session session = ConnectionUtils.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		} catch(OptimisticLockException exc) {
			return user;
		}
		return user;
	}

	@Override
	public void deleteById(Long id) {
	}

}
