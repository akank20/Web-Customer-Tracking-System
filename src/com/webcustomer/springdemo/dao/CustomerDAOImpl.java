package com.webcustomer.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.webcustomer.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {

		//get curr hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		//create a query
		Query<Customer> thequery = session.createQuery("from Customer order by lastName", Customer.class);
		
		//execute query and get result set
		List<Customer> resultList = thequery.getResultList();
		
		return resultList;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/update the customer 
		currentSession.saveOrUpdate(theCustomer);
		//currentSession.save(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		Session session = sessionFactory.getCurrentSession();
		
		
		//execute query and get result
		Customer theCustomer=session.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query=session.createQuery("delete from Customer where id=:customerId");
		
		query.setParameter("customerId", theId);
		
		query.executeUpdate();
	}
		


}
