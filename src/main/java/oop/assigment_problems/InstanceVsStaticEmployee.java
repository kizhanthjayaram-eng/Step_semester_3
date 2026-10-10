package oop.assigment_problems;

public class InstanceVsStaticEmployee {
    static class Employee {
        String empName;
        double salary;
        static String companyName = "Bright Horizon Technologies";
        static int employeeCount;
        Employee(String empName, double salary) {
            this.empName = empName;
            this.salary = salary;
            employeeCount++;
        }
        static void printCompanyInfo() {
            System.out.println(companyName);
            System.out.println("Employees on record: " + employeeCount);
        }
    }
    public static void main(String[] args) {
        new Employee("Asha", 40000);
        new Employee("Ravi", 45000);
        new Employee("Meera", 50000);
        Employee.printCompanyInfo();
    }
}