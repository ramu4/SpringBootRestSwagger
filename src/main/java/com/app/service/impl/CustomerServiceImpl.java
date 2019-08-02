package com.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Customer;
import com.app.repo.CustomerRepository;
import com.app.service.ICustomerService;
@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository repo;
	
	@Transactional
	public Integer saveCustomer(Customer c) {
		
		return repo.save(c).getcId();
	}

	@Transactional(readOnly=true)
	public List<Customer> getAllCustomers() {
		List<Customer> clist=repo.findAll();
		if(clist!=null && !clist.isEmpty()) {
			clist.stream()
			.sorted((s1,s2)->s1.getcName()
					.compareTo(s2.getcName()));
		}

		
		return clist;
	}

	@Transactional(readOnly=true)
	public Customer getOneCustomer(Integer id) {
		Optional<Customer> opt=repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	@Transactional
	public void deleteCustomer(Integer id) {
	repo.deleteById(id);
	}
	
	@Transactional
	public Customer updateCustomer(Customer c) {
		return repo.save(c);
	}

}
