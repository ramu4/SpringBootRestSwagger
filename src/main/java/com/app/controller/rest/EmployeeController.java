package com.app.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Customer;
import com.app.model.Employee;
import com.app.service.IEmployeeService;

@RestController
@RequestMapping("/rest/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService service;

	@PostMapping("/insert")
	public ResponseEntity<String> saveEmployee(
			@RequestBody Employee employee
			){
		ResponseEntity<String> resp=null;
		try {
			Integer id=service.saveEmployee(employee);
			resp=new ResponseEntity<String>("employee '"+id+ "' created",HttpStatus.OK);

		} catch (Exception e) {
			resp=new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}


		return resp;

	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllEMployees(){
		ResponseEntity<?> resp=null;
		try {
			List<Employee> list=service.getAllEmployees();

			if (list!=null && !list.isEmpty()) 
				resp=new ResponseEntity<List<Employee>>(list, HttpStatus.OK);

				else
					resp=new ResponseEntity<String>("No data found", HttpStatus.OK);

			

		} catch (Exception e) {

			resp=new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return resp;


	}

	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOneEmployee(
			@PathVariable("id")Integer eid
			){
		ResponseEntity<?> resp=null;
		try {
			Employee e=service.getOneEmployee(eid);
			if (e!=null) 
				resp=new ResponseEntity<Employee>(e,HttpStatus.OK);
			else
				resp=new ResponseEntity<String>("Data not found", HttpStatus.BAD_REQUEST);
			
			
		}catch (Exception e) {
		
			resp=new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
			e.printStackTrace();
		}
		
		return resp;
		
	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<String> deleteOneEmployee(
			@PathVariable("id")Integer eid
			){
		ResponseEntity<String> resp=null;
		try {
			service.deleteEmployee(eid);
			resp= new ResponseEntity<String>("Employee '"+eid+"' deleted", HttpStatus.OK);
			
			
		} catch (Exception e) {
			resp=new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		
		
		return resp;
		
	}



	@PutMapping("/update")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee eid) {
		ResponseEntity<?> resp=null;
		try {
			service.updateEmployee(eid);
			
			
			resp=new ResponseEntity<String>("Employee '"+eid+"' Updated", HttpStatus.OK);
		} catch (Exception e) {
			resp=new ResponseEntity<String>("Id Not Found", HttpStatus.BAD_REQUEST);
		}
		return resp;
	}




}
