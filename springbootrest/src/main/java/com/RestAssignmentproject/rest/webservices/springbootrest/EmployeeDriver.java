package com.RestAssignmentproject.rest.webservices.springbootrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
public class EmployeeDriver{

    @Autowired
    private EmployeeDaoServices services;
    private Employee Emp;

    //Ques 3: Implement GET http request for Employee to get list of employees.
    @GetMapping("/Ques3")
    public List<Employee> getAllEmployee(){
        return services.findAll();
    }

    //Ques 4:Implement GET http request using path variable top get one employee
    @GetMapping("/Ques4/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        return services.findSingleEmployee(id);

    }


    //Ques 5:Implement POST http request for Employee to create a new employee.
   @PostMapping("/Ques5")
   public void createEmployee(@RequestBody Employee emp){
       Employee emp1 = services.addEmployee(emp);
   }

    // Ques 6:Implement Exception Handling for resource not found
    @GetMapping("/Ques6/{id}")
    public Employee getEmployeeByValidId( @PathVariable int id){
        Employee emp = services.findSingleEmployee(id);
        if(emp == null){
            throw new EmployeeNotFoundException("Id:"+id);
        }
        return emp;
    }

    // Ques 7: Implement DELETE http request for Employee to delete employee
    @DeleteMapping("Ques7/{id}")
    public void deleteEmployee(@PathVariable int id){
        Employee emp = services.deleteEmployeeByID(id);

        if(emp == null){
            throw  new EmployeeNotFoundException("id: "+id);
        }
    }

    //Ques 8:Implement PUT http request for Employee to update employee
    @PutMapping("/Ques8/{id}")
    public void updateEmployee(@PathVariable int id, @RequestBody Employee emp){
        services.updateEmployee(id,emp);

    }


    // Ques 9: Apply validation while create a new employee using POST http Request.
    @PostMapping("/Ques5/{id}")
    public ResponseEntity<Object> createEmployeeApplyValidation(@Valid @RequestBody Employee emp){
        Employee savedEmployee = services.addEmployee(emp);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}")
                .buildAndExpand(savedEmployee.getId())
                .toUri();

        return   ResponseEntity.created(location).build();
    }
}