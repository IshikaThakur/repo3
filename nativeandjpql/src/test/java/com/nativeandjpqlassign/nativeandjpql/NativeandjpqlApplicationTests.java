package com.nativeandjpqlassign.nativeandjpql;

import com.nativeandjpqlassign.nativeandjpql.model.*;
import com.nativeandjpqlassign.nativeandjpql.repos.EmbeddedRepository;
import com.nativeandjpqlassign.nativeandjpql.repos.EmployeeRepository;
import com.nativeandjpqlassign.nativeandjpql.repos.PaymentPerClassRepository;
import com.nativeandjpqlassign.nativeandjpql.repos.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class NativeandjpqlApplicationTests
{
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	PaymentPerClassRepository paymentPerClassRepository;
	@Autowired
	EmbeddedRepository embeddedRepository;

	@Test
		public void createEmployee() {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setFirstName("Ishika");
		employee.setLastName("Thakur");
		employee.setSalary(1600.12);
		employee.setAge(52);

		Employee employee1 = new Employee();
		employee1.setId(2);
		employee1.setFirstName("Neha");
		employee1.setLastName("singh");
		employee1.setSalary(1600.12);
		employee1.setAge(49);

		Employee employee2 = new Employee();
		employee2.setId(3);
		employee2.setFirstName("Ameesha");
		employee2.setLastName("Swaroop");
		employee2.setSalary(1600.12);
		employee2.setAge(26);
		Employee employee3 = new Employee();
		employee3.setId(4);
		employee3.setFirstName("Apoorva");
		employee3.setLastName("Swaroop");
		employee3.setSalary(500.12);
		employee3.setAge(26);
		Employee employee4 = new Employee();
		employee4.setId(5);
		employee4.setFirstName("Deepak");
		employee4.setLastName("Kalra");
		employee4.setSalary(2600.12);
		employee4.setAge(26);
		employeeRepository.save(employee);
		employeeRepository.save(employee1);
		employeeRepository.save(employee2);
		employeeRepository.save(employee3);
		employeeRepository.save(employee4);
	}
  //Ques1. Display the first name, last name of all employees having salary greater than average salary ordered in ascending by their age and in descending by their salary
	@Test
	public void testCheckSalary()
	{
		Sort sort= Sort.by(Sort.Order.asc("age"),Sort.Order.desc("salary"));
     List<Object[]> partialData=employeeRepository.findAllEmployeePartialData( PageRequest.of(0,2,sort));
     for (Object[]objects:partialData)
	 {
		 System.out.println(objects[0]+" ");
		 System.out.println(objects[1]+" ");
		 System.out.println();
	 }
	}
	/*
+--------+---------+----------------+---------------+------------+
		| emp_id | emp_age | emp_first_name | emp_last_name | emp_salary |
		+--------+---------+----------------+---------------+------------+
		|      1 |      22 | Ishika         | Thakur        |    1600.12 |
		|      2 |      21 | Neha           | Gupta         |    1600.12 |
		|      3 |      26 | Ameesha        | Swaroop       |    1600.12 |
		|      4 |      26 | Apoorva        | Swaroop       |       1000 |
		|      5 |      26 | Deepak         | Kalra         |    2600.12 |
		+--------+---------+----------------+---------------+------------+
		5 rows in set (0.00 sec)*/

//****************Ques 2 Update salary of all employees by a salary passed as a parameter whose existing salary is less than the average salary.
	@Test
	@Transactional
	@Rollback(value = false)
	public void testUpdateSalary()
	{
		double average=employeeRepository.avgSalary();
		employeeRepository.updateSalary(1000d,average);
	}
/*
	+--------+---------+----------------+---------------+------------+
			| emp_id | emp_age | emp_first_name | emp_last_name | emp_salary |
		+--------+---------+----------------+---------------+------------+
		|      1 |      22 | Ishika         | Thakur        |    1600.12 |
		|      2 |      21 | Neha           | Gupta         |    1600.12 |
		|      3 |      26 | Ameesha        | Swaroop       |    1600.12 |
		|      5 |      26 | Deepak         | Kalra         |    2600.12 |
		+--------+---------+----------------+---------------+------------+
		4 rows in set  */

//===========Ques 3: Delete all employees with minimum salary.==================================

	@Test
	@Transactional
	@Rollback(value = false)
	public void testDeleteAllWithMinSalary()
	{
		double minimum=employeeRepository.minSalary();
		employeeRepository.deleteSalary(minimum);
	}
	//================Native SQL Query:=================================
	//
	//   Ques1: Display the id, first name, age of all employees where last name ends with "singh"
    @Test
	void testfindEmployeesWhoseLastNameEndsWithSingh()
	{
		List<Object[]> objects = employeeRepository.findEmployeesWhoseLastNameEndsWithSingh();
		for (Object[] objects1 : objects) {
			System.out.print("======================================================");
			System.out.print(objects1[0] + " " + objects1[1] + " " + objects1[2]);
			System.out.println();

		}
	}
	/*====================Before=============================
	+--------+---------+----------------+---------------+------------+
			| emp_id | emp_age | emp_first_name | emp_last_name | emp_salary |
		+--------+---------+----------------+---------------+------------+
		|      1 |      52 | Ishika         | Thakur        |    1600.12 |
		|      2 |      49 | Neha           | singh         |    1600.12 |
		|      3 |      26 | Ameesha        | Swaroop       |    1600.12 |
		|      4 |      26 | Apoorva        | Swaroop       |     500.12 |
		|      5 |      26 | Deepak         | Kalra         |    2600.12 |
		+--------+---------+----------------+---------------+------------+
		5 rows in set (0.00 sec)
======================================After deletion==============================
	mysql> select * from employee;
+--------+---------+----------------+---------------+------------+
		| emp_id | emp_age | emp_first_name | emp_last_name | emp_salary |
		+--------+---------+----------------+---------------+------------+
		|      3 |      26 | Ameesha        | Swaroop       |    1600.12 |
		|      4 |      26 | Apoorva        | Swaroop       |     500.12 |
		|      5 |      26 | Deepak         | Kalra         |    2600.12 |
		+--------+---------+----------------+---------------+------------+
		3 rows in set (0.00 sec)*/


  //====================Native Query=========================
	//Ques2. Delete all employees with age greater than 45(Should be passed as a parameter.

	@Transactional
	@Rollback(value=false)
	@Test
	void testDeleteEmployeesWithAgeGraterThan45()
	{
		employeeRepository.deleteEmployeesWithAgeGreaterThan45(45);
	}
	/*

	mysql> select * from payment;
+-----+-------+-----------+------------+-------------+
		| id  | pmode | amount    | cardnumber | checknumber |
		+-----+-------+-----------+------------+-------------+
		| 678 | ch    | 13000.900 | NULL       | 435672      |
		| 981 | cc    | 12000.123 | 123456     | NULL        |
		+-----+-------+-----------+------------+-------------+
		2 rows in set (0.00 sec)*/

	//==========================Inheritance Mapping:========================

	//Ques1 : Implement and demonstrate Single Table strategy.

	@Test
public void testCardPayment()
{
	CreditCard cc=new CreditCard();
	cc.setId(981);
	cc.setCardnumber("123456");
	cc.setAmount(12000.123);
	paymentRepository.save(cc);

}
@Test
public void testCheckPayment() {
	Check ch = new Check();
	ch.setId(678);
	ch.setChecknumber("435672");
	ch.setAmount(13000.90);
	paymentRepository.save(ch);
}
	/*
        select * from cardpay;
    +-----+----------+------------+
            | id  | amount   | cardnumber |
            +-----+----------+------------+
            | 123 | 1200.670 | 23456      |
            +-----+----------+------------+
            1 row in set (0.00 sec) */
	//=====================nheritance Mapping:
	//Ques 2: Implement and demonstrate Table per Class strategy.

@Test
	public void testCardPerClass()
{
	CardPerClass cc=new CardPerClass();
	cc.setId(123);
	cc.setAmount(1200.67);
    cc.setCardnumber("23456");
	paymentPerClassRepository.save(cc);
}
	/*select * from bankcheck;
+-----+----------+-------------+
		| id  | amount   | checknumber |
		+-----+----------+-------------+
		| 432 | 3400.670 | 3678        |
		+-----+----------+-------------+*/

@Test
	public void testCheckPerClass()
{
CheckPerClass ch=new CheckPerClass();
ch.setId(432);
ch.setAmount(3400.67);
ch.setChecknumber("3678");
paymentPerClassRepository.save(ch);
}

//===============================Component Mapping:============================
//
//
//  Ques : Implement and demonstrate Embedded mapping using employee table having following fields: id, firstName, lastName, age, basicSalary, bonusSalary, taxAmount, specialAllowanceSalary.
/* select * from embedded;+-----+-----------+----------+------+-------------+-------------+-----------+------------------------+
| id  | firstname | lastname | age  | basicsalary | bonussalary | taxamount | specialallowancesalary |
+-----+-----------+----------+------+-------------+-------------+-----------+------------------------+
| 123 | Ishika    | Thakur   |   22 |       10000 |        2000 |       500 |                    200 |
+-----+-----------+----------+------+-------------+-------------+-----------+------------------------+
*/


	@Test
	public void testCreateEmbeddedEmployee()
{
	EmployeeEmbedded employeeEmbedded=new EmployeeEmbedded();
	employeeEmbedded.setId(123);
	employeeEmbedded.setFirstname("Ishika");
	employeeEmbedded.setLastname("Thakur");
	employeeEmbedded.setAge(22);
	Salary salary=new Salary();
    salary.setBasicsalary(10000);
    salary.setBonussalary(2000);
    salary.setTaxamount(500);
    salary.setSpecialallowancesalary(200);
    employeeEmbedded.setSalary(salary);

	embeddedRepository.save(employeeEmbedded);
	; }
}

