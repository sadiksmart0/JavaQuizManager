package main.services.data.dao;

import main.fr.epita.quizProject.datamodel.Student;
import main.services.DbConnectionStrings;

import java.sql.*;
import java.util.Scanner;

public class StudentJDBCDAO {

/**  AUTHENTICATING OF  STUDENT   *************************************/
    public boolean authenticateStudent(){

//       DATABASE CONNECTION STRINGS
        DbConnectionStrings dbConnectionStrings = new DbConnectionStrings();
        Connection connection = dbConnectionStrings.getConnection();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input is case sensitive");
        System.out.println("Enter Your name:");
        String studentname = scanner.nextLine();
        System.out.println("Enter Your Email");
        String studentemail = scanner.nextLine();
        boolean authenticated = Boolean.parseBoolean(null);
        String authQuery = "SELECT * FROM STUDENTS WHERE Students.Id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(authQuery);
            preparedStatement.setString(1,studentemail);
            ResultSet resultSet = preparedStatement.executeQuery();



             while (resultSet.next()){

                 if(!studentemail.equals(resultSet.getString("Id")) || !studentname.equals(resultSet.getString("name"))){


                     authenticated = false;


                 }else {
                     System.out.println("\nWelcome    "+resultSet.getString("name"));

                     authenticated=true;
                 }
             }
            return authenticated;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

/**  REGISTERING NEW STUDENT TO DATABASE  ************************/
    public void createStudent(Student student) throws SQLException {

//       DATABASE CONNECTION STRINGS
        DbConnectionStrings dbConnectionStrings = new DbConnectionStrings();
        Connection connection = dbConnectionStrings.getConnection();

        String createQuery = "CREATE TABLE IF NOT EXISTS STUDENTS(name varchar(255), Id varchar(200) PRIMARY KEY)";
        connection.prepareStatement(createQuery).execute();
        String insertQuery = "INSERT INTO STUDENTS( name, Id)" + "values(?, ?)";
        PreparedStatement ps = connection.prepareStatement(insertQuery);
        ps.setString(1, student.getName());
        ps.setString(2, student.getId());
        ps.execute();
        connection.close();


    }

}
