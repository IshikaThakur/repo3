package com.assignhibernate.hibernateassess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class HibernateassessApplicationTests {
	//Ans 2.Set up EmployeeRepository with Spring Data JPA.
	@Autowired
	EmployeeRepository employeeRepository;

	//Ans 3. Perform Create Operation on Entity using Spring Data JPA
	@Test
		public void testCreate() {
			Employee employee = new Employee();
			employee.setId(1);
			employee.setName("Ishika Thakur");
			employee.setAge(22);
			employee.setLocation("greater noida");
			Employee employee1 = new Employee();
			employee1.setId(2);
			employee1.setName("Neha Gupta");
			employee1.setAge(21);
			employee1.setLocation("Delhi");
			Employee employee2 = new Employee();
			employee2.setId(3);
			employee2.setName("Ameesha");
			employee2.setAge(24);
			employee2.setLocation("Lucknow");
			employeeRepository.save(employee);
			employeeRepository.save(employee1);
			employeeRepository.save(employee2);
		}

	@Test
	void show(){
		employeeRepository.findAll();
	}
	//Ans 4. Perform Update Operation on Entity using Spring Data JPA
	@Test
	void testUpdate() {
		Employee employee = employeeRepository.findById(1).get();
		employee.setAge(20);
		employeeRepository.save(employee);
	}


//Ans 5 Perform Delete Operation on Entity using Spring Data JPA
	@Test
	public void testDelete() {
		if (employeeRepository.existsById(1)) {
			System.out.println("Deleting the details of an employee");
		}
		employeeRepository.deleteById(1);
	}
//Ans 5.Perform Read Operation on Entity using Spring Data JPA
	@Test
	public void testRead() {
		Employee employee = employeeRepository.findById(2).get();
		Assertions.assertNotNull(employee);
		Assertions.assertEquals("Neha Gupta", employee.getName());
	}
//Ans 6  Get the total count of the number of Employees
	@Test
	public void testCount() {
		System.out.println("total number of records are>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + employeeRepository.count());
	}
//Ans 7 Implement Pagination and Sorting on the bases of Employee Age
	@Test
	public void testpagingAndSorting() {
		Pageable pageable= PageRequest.of(0,2, Sort.Direction.DESC, "age");
		employeeRepository.findAll(pageable).forEach(p-> System.out.println(p.getName()));

	}
	//Ans 8.Create and use finder to find Employee by Name
	@Test
	public void testFind(){
		List<Employee> employees = employeeRepository.findByName("Neha Gupta");
		employees.forEach(employee -> System.out.println(employee.getAge()));
	}
	//Ans 9.Create and use finder to find Employees starting with A character
	@Test
	public void testFindEmp(){
		List<Employee> employees = employeeRepository.findByNameLike("A%");
		employees.forEach(employee -> System.out.println(employee.getAge()));
	}
	//Ans 10.Create and use finder to find Employees Between the age of 28 to 30.
	@Test
	public void testFindBetween() {
		List<Employee> employees = employeeRepository.findByAgeBetween(28, 32);
		employees.forEach(employee -> System.out.println(employee.getName()));
	}
}



