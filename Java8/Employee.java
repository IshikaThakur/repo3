package Java8;

    interface EmployeeData
    {
        Employee data(String name,int age,String city);
    }
    public class Employee {
        String name;
        int age;
        String city;

        public Employee( String name,int age,String city)
        {
            this.name=name;
            this.age=age;
            this.city=city;

        }
        public String toString()
        {
            return "name "+name+" age "+age+" city "+city;
        }
        public static void main(String[] args)
        {
            EmployeeData ob = Employee::new;
            Employee emp=ob.data("Ishika",22,"Kanpur");
            System.out.println(emp.toString());
        }

    }
