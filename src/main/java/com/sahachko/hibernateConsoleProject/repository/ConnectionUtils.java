package com.sahachko.hibernateConsoleProject.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.sahachko.hibernateConsoleProject.model.Post;
import com.sahachko.hibernateConsoleProject.model.Region;
import com.sahachko.hibernateConsoleProject.model.User;

public class ConnectionUtils {
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
			Metadata metadata = new MetadataSources(serviceRegistry)
					.addAnnotatedClass(Region.class)
					.addAnnotatedClass(Post.class)
					.addAnnotatedClass(User.class)
					.buildMetadata();
			sessionFactory = metadata.buildSessionFactory();
		}
		return sessionFactory;
	}
	
	public static void closeSessionFactory() {
		if(sessionFactory != null) {
			sessionFactory.close();
		}
	}
}
