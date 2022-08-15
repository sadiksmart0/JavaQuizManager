package fr.epita.quizProject;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TesTLoadQuestionsJDBCDAO {

    public static void main(String[] args) throws SQLException, FileNotFoundException {

        /**  Context /  creating tables  **/

        Connection connection = getConnection();

        final String readQuery = "CREATE TABLE IF NOT EXISTS MULTIPLECHOICES(id SERIAL PRIMARY KEY, question varchar(255), q_options varchar(200), topic varchar(60), difficulty int, answer varchar(10))";

        final String openQQuery = "CREATE TABLE IF NOT EXISTS OPENQUESTIONSS(id SERIAL PRIMARY KEY, question varchar(255), topic varchar(60), difficulty int, answer varchar(10))";

        final String studentQuery = "CREATE TABLE IF NOT EXISTS STUDENT(name varchar(255), id varchar(200) PRIMARY KEY)";


        PreparedStatement preparedStatement = connection.prepareStatement(readQuery);
        PreparedStatement prepStatement = connection.prepareStatement(openQQuery);
        PreparedStatement pStatement = connection.prepareStatement(studentQuery);

 /**  Action    **/
        preparedStatement.execute();
        prepStatement.execute();
        pStatement.execute();

        BufferedReader mcq = new BufferedReader(new FileReader("./resources/MCQ.sql"));
        BufferedReader open = new BufferedReader(new FileReader("./resources/Openq.sql"));
        BufferedReader student = new BufferedReader(new FileReader("./resources/student.sql"));

        ScriptRunner sr = new ScriptRunner(connection);
        sr.runScript(mcq);
        sr.runScript(open);
        sr.runScript(student);


    }
    /**  DATABASE CONNECTION STRINGS   **********************************************************************************8**/
    private static Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/QUIZ","postgres","B00kw0rm1");

    }


}
