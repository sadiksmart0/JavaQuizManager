package fr.epita.quizProject;

import java.sql.*;
import java.util.Scanner;

public class TestStudentJDBCDAO {

    public static void main(String[] args) throws SQLException {
// Database connection
            Connection connection = getConnection();
// Getting student details
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Your name:");
            String studentname = scanner.nextLine();

            System.out.println("Enter Your Email");
            String studentemail = scanner.nextLine();

            boolean authenticated;
//   Querying database
            String authQuery = "SELECT * FROM STUDENTS WHERE Students.Id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(authQuery);
            preparedStatement.setString(1,studentemail);


/**   Action **/
            ResultSet resultSet = preparedStatement.executeQuery();

/***  Check  **/
// checking if user is in our database
            while (resultSet.next()){

                if(!studentemail.equals(resultSet.getString("Id"))){
                    authenticated = false;
                    System.out.println("Wrong Student Credentials");

                }else {
                    authenticated=true;
                    System.out.println("Welcome    "+resultSet.getString("name"));

                }
            }

        }



    /**   Context  **/

    private static Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/QUIZ","postgres","B00kw0rm1");

    }


}
