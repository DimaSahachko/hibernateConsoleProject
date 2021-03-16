package com.sahachko.hibernateConsoleProject.repository.hibernate;

import java.util.*;

import javax.persistence.OptimisticLockException;

import org.hibernate.Session;

import com.sahachko.hibernateConsoleProject.model.Region;
import com.sahachko.hibernateConsoleProject.repository.ConnectionUtils;
import com.sahachko.hibernateConsoleProject.repository.RegionRepository;

public class JavaIORegionRepository implements RegionRepository {
	
	@Override
	public List<Region> getAll() {
		List<Region> allRegions = new ArrayList<>();
		try(Session session = ConnectionUtils.getSessionFactory().openSession()) {
			session.beginTransaction();
			allRegions = session.createQuery("from Region", Region.class).getResultList();
			session.getTransaction().commit();
		}
		return allRegions;
	}

	@Override
	public Region getById(Long id) {
		Region region = null;
		try(Session session = ConnectionUtils.getSessionFactory().openSession()) {
			session.beginTransaction();
			region = session.get(Region.class, id);
			session.getTransaction().commit();
		}
		return region;
	}

	@Override
	public Region save(Region region) {
		try(Session session = ConnectionUtils.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(region);
			session.getTransaction().commit();
		}
		return region;
	}

	@Override
	public Region update(Region region) {
		try(Session session = ConnectionUtils.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.update(region);
			session.getTransaction().commit();
		} catch(OptimisticLockException exc) {
			return null;
		}
		return region;
	}

	@Override
	public void deleteById(Long id) {
		try(Session session = ConnectionUtils.getSessionFactory().openSession()) {
			Region region = getById(id);
			if (region == null) {
				System.out.println("There is no region with such id");
			} else {
				session.beginTransaction();
				session.delete(region);
				session.getTransaction().commit();
			}
		}
	}
}
