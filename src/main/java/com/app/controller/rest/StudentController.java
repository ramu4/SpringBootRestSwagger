package com.app.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Customer;
import com.app.model.Student;
import com.app.service.IStudentService;

@RestController
@RequestMapping("/rest/student")
public class StudentController {
	@Autowired
	private IStudentService service;

	@PostMapping("/insert")
	public ResponseEntity<String> saveStudent(@RequestBody Student  student) {
		ResponseEntity<String> resp=null;
		try {
			Integer id=service.saveStudent(student);
			resp=new ResponseEntity<String>("Student '"+id+"' created", HttpStatus.OK);
		}catch (Exception e) {
			resp=new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}


	@GetMapping("/all")
	public ResponseEntity<?> getAllStudents() {
		ResponseEntity<?> resp=null;
		try {
			List<Student> list=service.getAllStudents();
			if(list!=null && !list.isEmpty())
				resp=new ResponseEntity<List<Student>>(list, HttpStatus.OK);
			else
				resp=new ResponseEntity<String>("No Data Found",HttpStatus.OK);

		} catch (Exception e) {
			resp=new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resp;

	}

	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOneStudent(@PathVariable("id")Integer sid) {
		ResponseEntity<?> resp=null;

		try {
			Student s=service.getOneStudent(sid);
			if(s!=null)
				resp=new ResponseEntity<Student>(s, HttpStatus.OK);
			else
				resp=new ResponseEntity<String>("No Data Found", HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			resp=new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOneStudent(
			@PathVariable("id")Integer sid
			) 
	{
		ResponseEntity<String> resp=null;
		try {
			service.deleteStudent(sid);
			resp=new ResponseEntity<String>("Deleted '"+sid+"' Successfully", HttpStatus.OK);
		} catch (Exception e) {
			resp=new ResponseEntity<String>("Unable to Delete/Id Not Found", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resp;
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<?> updateStudent(@RequestBody Student sid) {
		ResponseEntity<?> resp=null;
		try {
			service.updateStudent(sid);
			
			
			resp=new ResponseEntity<String>("Student '"+sid+"' Updated", HttpStatus.OK);
		} catch (Exception e) {
			resp=new ResponseEntity<String>("Id Not Found", HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
}
