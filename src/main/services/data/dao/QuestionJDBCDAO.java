package main.services.data.dao;

import main.fr.epita.quizProject.datamodel.Answer;
import main.fr.epita.quizProject.datamodel.Question;
import main.services.DbConnectionStrings;

import java.sql.*;


/**  ADDING QUESTIONS TO DATABASE OF OPEN QUESTIONS    *************************************/
public class QuestionJDBCDAO {

    public static void addOpenQuestion(Question question, Answer answer) throws SQLException {

//       DATABASE CONNECTION STRINGS
        DbConnectionStrings dbConnectionStrings = new DbConnectionStrings();
        Connection connection = dbConnectionStrings.getConnection();

        String insertQuery = "INSERT INTO OPENQUESTIONS( question, topic, difficulty, answer)" + "values(?, ?, ?,?)";
        PreparedStatement ps = connection.prepareStatement(insertQuery);
        ps.setString(1, question.getQuestion());
        ps.setString(2, question.getTopics());
        ps.setInt(3, question.getDifficulty());
        ps.setString(4, answer.getText());
        ps.execute();
        connection.close();

    }


/** READING ALL OPENQUESTIONS WHERE CONDITIONS IS SATISFIES  *******************/
    public static ResultSet readAllOpenQ(Integer level) throws SQLException {

//       DATABASE CONNECTION STRINGS
        DbConnectionStrings dbConnectionStrings = new DbConnectionStrings();
        Connection connection = dbConnectionStrings.getConnection();

        String readQuery = "SELECT * From openquestions WHERE difficulty = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(readQuery);
        preparedStatement.setInt(1,level);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;


    }

/**    DELETING OPEN QUESTIONS BASED ON TOPIC ********************************/
    public static void deleteOpenQuestion(Question question, Answer answer) throws SQLException {


//       DATABASE CONNECTION STRINGS
        DbConnectionStrings dbConnectionStrings = new DbConnectionStrings();
        Connection connection = dbConnectionStrings.getConnection();

        String delQuery = "DELETE FROM OPENQUESTIONS WHERE topic = ?";
        PreparedStatement deleteQuestion = connection.prepareStatement(delQuery);
        deleteQuestion.setString(1, String.valueOf(question.getTopics()));
        deleteQuestion.executeUpdate();


    }
/**   SEARCHING   THROUGH OPEN QUESTIONS BASED ON TOPIC   **********************/
    public static ResultSet searchOpenQuestions(Question question) throws SQLException {


//       DATABASE CONNECTION STRINGS
        DbConnectionStrings dbConnectionStrings = new DbConnectionStrings();
        Connection connection = dbConnectionStrings.getConnection();

        String readQuery = "SELECT * FROM OPENQUESTIONS WHERE topic = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(readQuery);
        preparedStatement.setString(1, String.valueOf(question.getTopics()));
        return preparedStatement.executeQuery();

    }

    /**  UPDATING QUESTION BASED ON ID  *************************************/
    public static void updateOpenQuestions(Question question, Integer questionId ) {


//       DATABASE CONNECTION STRINGS
        DbConnectionStrings dbConnectionStrings = new DbConnectionStrings();
        Connection connection = dbConnectionStrings.getConnection();

        PreparedStatement updateQuestion = null;
        try {

            updateQuestion = connection.prepareStatement("UPDATE OPENQUESTIONS SET question = ?,topic = ?," +
                    "difficulty = ?," +
                    "answer = ? WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            updateQuestion.setString(1, question.getQuestion());
            updateQuestion.setString(2, String.valueOf(question.getTopics()));
            updateQuestion.setInt(3, question.getDifficulty());
            updateQuestion.setString(4, String.valueOf(question.getOpenAnswer()));
            updateQuestion.setInt(5, questionId);
            updateQuestion.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
