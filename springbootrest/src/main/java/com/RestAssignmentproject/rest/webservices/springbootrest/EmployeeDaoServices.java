package com.RestAssignmentproject.rest.webservices.springbootrest;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

    // Ques 2:Create an Employee Bean(id, name, age) and service to  perform different operations related to employee.
    @Component
    public class EmployeeDaoServices{
        private static List<Employee> emp = new ArrayList<>();
        private static int idCounter= 3;
        static {
            emp.add(new Employee(1,"Ishika",new Date()));
            emp.add(new Employee(2,"Ameesha",new Date()));
            emp.add(new Employee(3,"Neha",new Date()));
        }

        public List<Employee> findAll(){
            return emp;
        }

        public Employee addEmployee(Employee emp1){
            if(emp1.getId() == null){
                emp1.setId(++idCounter);
            }
            emp.add(emp1);
            return emp1;
        }

        public Employee findSingleEmployee(int id){
            for (Employee emp1 : emp){
                if(emp1.getId() == id){
                    return emp1;
                }
            }
            return null;
        }
        // Ques 7: Implement DELETE http request for Employee to delete employee
        public Employee deleteEmployeeByID(int id){
            Iterator<Employee> itr = emp.iterator();
            while(itr.hasNext()){
                Employee emp = itr.next();
                if(emp.getId() == id){
                    itr.remove();
                    return emp;
                }
            }
            return null;
        }
        // Ques 8:Implement PUT http request for Employee to update employee
        public void updateEmployee(int id, Employee emp1){
            for(int i=0;i<emp.size();i++)
            {
                Employee t=emp.get(i);
                if(t.getId()==id)
                {
                    emp.set(i,emp1);
                    return;
                }
           /* Iterator<Employee> itr = emp.iterator();
            while(itr.hasNext()){
                Employee emp = itr.next();
                if(emp1.getId() == id){
                    emp.setId(emp1.getId());
                    emp.setName(emp1.getName());
                }*/

            }

        }
}
