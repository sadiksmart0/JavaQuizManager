package main.services.data.dao;

import main.services.DbConnectionStrings;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;

import java.sql.*;


public class LoadQuestionDAO {

    public void loadAllQuestion(){

 /** LOADING QUESTIONS TO DATA ON FIRST INSTALLATION ************************************************/

//       DATABASE CONNECTION STRINGS
        DbConnectionStrings dbConnectionStrings = new DbConnectionStrings();
        Connection connection = dbConnectionStrings.getConnection();

        final String readQuery = "CREATE TABLE IF NOT EXISTS MULTIPLECHOICE(id SERIAL PRIMARY KEY, question varchar(255), q_options varchar(200), topic varchar(60), difficulty int, answer varchar(10))";

        final String openQQuery = "CREATE TABLE IF NOT EXISTS OPENQUESTIONS(id SERIAL PRIMARY KEY, question varchar(255), topic varchar(60), difficulty int, answer varchar(10))";

        final String studentQuery = "CREATE TABLE IF NOT EXISTS STUDENTS(name varchar(255), id varchar(200) PRIMARY KEY)";


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readQuery);
            PreparedStatement prepStatement = connection.prepareStatement(openQQuery);
            PreparedStatement pStatement = connection.prepareStatement(studentQuery);
            preparedStatement.execute();
            prepStatement.execute();
            pStatement.execute();

            BufferedReader mcq = new BufferedReader(new FileReader("./resources/QuestionToLoad/MCQ.sql"));
            BufferedReader open = new BufferedReader(new FileReader("./resources/QuestionToLoad/Openq.sql"));
            BufferedReader student = new BufferedReader(new FileReader("./resources/QuestionToLoad/student.sql"));

            ScriptRunner sr = new ScriptRunner(connection);
            sr.runScript(mcq);
            sr.runScript(open);
            sr.runScript(student);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
