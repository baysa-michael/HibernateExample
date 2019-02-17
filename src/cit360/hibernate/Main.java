package cit360.hibernate;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create a new instance of the Data Access Object
        DataAccessObject thisTest = DataAccessObject.getInstance();

        // Retrieve and display a list of employees in the database
        System.out.println("Initial Entries in Database:");
        List<Employee> myEmployees = thisTest.getEmployees();
        for (int i = 0; i < myEmployees.size(); i++) {
            System.out.println(myEmployees.get(i).toString() + "\n");
        }
/*
        for (Employee thisEmployee : myEmployees) {
            System.out.println(thisEmployee.toString() + "\n");
        }
*/

        // Insert a new employee
        System.out.println("Test Entry in Database:");
        System.out.print("Name:  ");
        String employeeName = myScanner.nextLine();
        System.out.print("Position:  ");
        String employeePosition = myScanner.nextLine();

        thisTest.addSingleEmployee(employeeName, employeePosition);

        // Redisplay the new list of employees in the database
        System.out.println("Final Entries in Database:");
        myEmployees = thisTest.getEmployees();
        for (Employee thisEmployee : myEmployees) {
            System.out.println(thisEmployee.toString() + "\n");
        }


    }
}
