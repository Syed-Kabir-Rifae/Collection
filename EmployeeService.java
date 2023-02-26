package com.ibm.AdditionDao;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//
//
//
////@Service
////@EntityScan("com.example.entities")
////@EnableJpaRepositories("com.example.repositories")
//@Service
//@Transactional
//public class EmployeeService {
//	
//	private final EmploRepo emplorepo;
//	@Autowired
//	public EmployeeService(EmploRepo emplorepo) {
//		
//		this.emplorepo = emplorepo;
//	}
//
//    public Employee addEmployee(Employee emp) {
//		emp.setEmployee(UUID.randomUUID().toString());
//		return emplorepo.save(emp);
//}
//	public Employee updateEmployee(Employee emp) {
//		return emplorepo.save(emp);
//	}
//	public List<Employee> findAllEmployee(){
//		return emplorepo.findAll();
//	}
//	public Optional<Employee> FindEmployeeByid(Long id) {
//		return emplorepo.FindById(id);
//		
//	}
//	public void DeleteEmployee(Long id) {
//		emplorepo.DeleteEmployeeById(id);
//	}
//	
//}