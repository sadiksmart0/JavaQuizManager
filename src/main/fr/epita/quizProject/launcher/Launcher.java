package main.fr.epita.quizProject.launcher;

import main.services.AdminService;
import main.services.QuizService;
import main.services.StudentService;
import main.services.data.dao.StudentJDBCDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {

   /**
   *   Launching the app
    */
        Launcher launcher = new Launcher();
        launcher.welcome();

    }


    public void welcome() {

/** ADMIN PROPERTY **/
        Properties properties = new Properties();
        StudentJDBCDAO studentJDBCDAO = new StudentJDBCDAO();
        QuizService quizService = new QuizService();


        Scanner userName = null;
        Scanner userPass = null;
        try {
            properties.load(new FileInputStream("credential.properties"));

            String defaultUserName = properties.getProperty("userName");
            String defaultpass = properties.getProperty("password");
            System.out.println("You are welcome to Quiz Master!!!! Who are you??\n(a) Student\n(b) Admin\n(c) New Student(Register)\n(d) Exit");
            Scanner userType = new Scanner(System.in);

/**
 * Display Home page/Menu
 * Checks user if student/admin
 * Launches quiz type by student
 * Launches admin authentication
 */
            String usertype = userType.nextLine().toUpperCase();

//            CHECKS STUDENTS
            switch (usertype) {
                case "A":

                    Boolean authResult = studentJDBCDAO.authenticateStudent();
                    if (authResult.equals(true)) {

                        System.out.println("Select Quiz type:\n(a) MCQ Quiz\n(b) Open Quiz\n(c)Take Custom Quiz & export as PDF");
                        Scanner scanner = new Scanner(System.in);
                        String qType =  scanner.nextLine();
                        switch (qType){
                            case "a":
                                quizService.mcqQuiz();
                                break;
                            case "b":
                                quizService.openQuiz();
                                break;
                            case "c":
                                quizService.customMcqQuiz();
                                break;
                            default:
                                System.out.println("Invalid Selection");
                                break;

                        }


                    } else {

                        System.out.println("Wrong Login credentials or not on our Database");
                    }


                    break;
                case "B":

                    //authenticate admin Username reception

                    System.out.println("Enter Username");
                    userName = new Scanner(System.in);
                    String username = userName.nextLine();
                    System.out.println("enter password");
                    userPass = new Scanner(System.in);
                    String userpass = userPass.nextLine();

                    AdminService adminService = new AdminService();
                    if (username.equals(defaultUserName) && userpass.equals(defaultpass)) {

                        adminService.adminTask();

                    } else {
                        System.out.println("Wrong Admin Credentials");
                    }

                    break;
                case "C":
//      New Student Handler
                    StudentService studentService = new StudentService();
                    studentService.registerStudent();
                    break;
                default:
                    System.out.println("Invalid selection: enter a or b");
                    break;

            }
            userType.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
