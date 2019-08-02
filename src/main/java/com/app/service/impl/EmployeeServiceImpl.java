package com.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Customer;
import com.app.model.Employee;
import com.app.repo.EmployeeRepository;
import com.app.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository repo;


	@Transactional
	public Integer saveEmployee(Employee e) {

		return repo.save(e).getEmpId();
	}

	@Transactional(readOnly=true)
	public List<Employee> getAllEmployees() {
		List<Employee> elist=repo.findAll();

		if (elist!=null && !elist.isEmpty()) {
			elist.stream()
			.sorted((s1,s2)->s1.getEmpName()
					.compareTo(s2.getEmpName()));

		}

		return elist;
	}

	@Transactional(readOnly=true)
	public Employee getOneEmployee(Integer id) {

		Optional<Employee> opt=repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();

		}

		return null;
	}

	@Transactional
	public void deleteEmployee(Integer id) {
		repo.deleteById(id);
	}
	
	@Transactional
	public Employee updateEmployee(Employee e) {
		return repo.save(e);
	}

}
