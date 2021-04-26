package com.example.demo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean saveProduct(Product product) {
		boolean status = false;
		try {
			Session session = entityManager.unwrap(Session.class);
			SessionFactory sessionFactory = session.getSessionFactory();
			sessionFactory.openSession();
			session.save(product);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<Product> getProducts() {
		try {
			Session session = entityManager.unwrap(Session.class);
			SessionFactory sessionFactory = session.getSessionFactory();
			// SessionFactory sessionFactory =
			// entityManager.unwrap(Session.class).getSessionFactory();
			sessionFactory.openSession();
			// Session session = sessionFactory.getCurrentSession();
			CriteriaQuery<Product> criteriaQuery = session.getCriteriaBuilder().createQuery(Product.class);
			criteriaQuery.from(Product.class);

			// Criteria criteria = session.createCriteria(Product.class);
			List<Product> list = session.createQuery(criteriaQuery).getResultList();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteProduct(Product product) {
		boolean status = false;
		Session session = entityManager.unwrap(Session.class);
		try {
			SessionFactory sessionFactory = session.getSessionFactory();
			sessionFactory.openSession();
			session.beginTransaction();
			session.delete(product);
			status = true;
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Product> getProductByID(Product product) {
		Session session = entityManager.unwrap(Session.class);
		try {
			SessionFactory sessionFactory = session.getSessionFactory();
			sessionFactory.openSession();
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class);
			criteria.add(Restrictions.eq("product_id", product.getProductId()));

			List<Product> list = criteria.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateProduct(Product product) {
		boolean status = false;
		try {
			Session session = entityManager.unwrap(Session.class);
			SessionFactory sessionFactory = session.getSessionFactory();
			sessionFactory.openSession();
			session.update(product);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
