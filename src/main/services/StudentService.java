package main.services;

import main.fr.epita.quizProject.datamodel.Student;
import main.services.data.dao.StudentJDBCDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class StudentService {

/**  STUDENTS REGISTRATION   HANDLING  ***********************/
    public void  registerStudent(){
        StudentJDBCDAO studentJDBCDAO = new StudentJDBCDAO();
        System.out.println("What would you like to perform?" +
                "\n(a)New Student(Register)\n(b)Exit ");

        Scanner myscanner = new Scanner(System.in);
        String task = myscanner.nextLine();
        System.out.println(task);
        switch (task){
            case "a":
                System.out.println("Enter your Name");
                String name = myscanner.nextLine();
                System.out.println("Enter your email");
                String email = myscanner.nextLine();
                try {
                    studentJDBCDAO.createStudent(new Student(name, email));
                    System.out.println("Registration Successful");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;
            case "b":
                System.out.println("Exited");
                break;
            default:
                System.out.println("Invalid selection");
        }



    }
}
