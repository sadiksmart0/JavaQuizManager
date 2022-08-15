package fr.epita.quizProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDBConnection {

    public static void main(String[] args) throws SQLException {

/** Establish  Database connection   **/
            Connection connection =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/QUIZ","postgres","B00kw0rm1");
            String schema = connection.getSchema();
        System.out.println(schema);
    }
}
