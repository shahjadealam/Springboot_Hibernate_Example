package com.example.demo.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Customer;

@Repository
@Transactional
public class CustomerDao {

	@Autowired
	SessionFactory sessionFactory;
	
	private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
	
	public void saveCustomer(Customer customer) {
	
		this.getSession().save(customer);
		
	}

	}

