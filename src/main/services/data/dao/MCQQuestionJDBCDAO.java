package main.services.data.dao;

import main.fr.epita.quizProject.datamodel.MCQAnswer;
import main.fr.epita.quizProject.datamodel.MCQChoice;
import main.fr.epita.quizProject.datamodel.Question;
import main.services.DbConnectionStrings;

import java.sql.*;

public class MCQQuestionJDBCDAO {

    /**  ADDING NEW MULTIPLE CHOICE QUESTIONS  DAO     *****************************************************8**/

    public void addMCQQuestion(Question question, MCQChoice mcqChoice) throws SQLException {

//       DATABASE CONNECTION STRINGS
        DbConnectionStrings dbConnectionStrings = new DbConnectionStrings();
        Connection connection = dbConnectionStrings.getConnection();

        final String insertQuery = "INSERT INTO MULTIPLECHOICE( question,Q_options, topic, difficulty, answer)" + "values(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(insertQuery);
        ps.setString(1, question.getQuestion());
        ps.setString(2, mcqChoice.getChoice());
        ps.setString(3, question.getTopics());
        ps.setInt(4, question.getDifficulty());
        ps.setString(5, String.valueOf(mcqChoice.getMcqAnswers()));
        ps.execute();
        connection.close();

    }



    /**  READ ALL MCQ QUESTION FROM BASED ON DIFFICULTY LEVEL FROM DATABASE  ******************************************************************8**/
    public ResultSet readAllMcqDifficulty(Integer level) throws SQLException {

//       DATABASE CONNECTION STRINGS
        DbConnectionStrings dbConnectionStrings = new DbConnectionStrings();
        Connection connection = dbConnectionStrings.getConnection();

        final String readQuery = "SELECT * FROM MULTIPLECHOICE WHERE DIFFICULTY = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(readQuery);
        preparedStatement.setInt(1, level);
        ResultSet resultSet = preparedStatement.executeQuery();
        connection.close();
        return resultSet;

    }

   /** READ ALL MCQ QUESTION  FROM DATABASE   *************************************/
    public ResultSet readAllMcq() throws SQLException {

//       DATABASE CONNECTION STRINGS
        DbConnectionStrings dbConnectionStrings = new DbConnectionStrings();
        Connection connection = dbConnectionStrings.getConnection();

        String readQuery = "SELECT * FROM MULTIPLECHOICE";
        PreparedStatement preparedStatement = connection.prepareStatement(readQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        connection.close();
        return resultSet;

    }


    /**  DELETING MULTIPLE CHOICE QUESTIONS  BASED ON TOPIC    *********************************************************/

    public void deleteMCQQuestion(Question question,MCQAnswer mcqAnswer) throws SQLException {

//       DATABASE CONNECTION STRINGS
        DbConnectionStrings dbConnectionStrings = new DbConnectionStrings();
        Connection connection = dbConnectionStrings.getConnection();
            final String delQuery = "DELETE FROM MULTIPLECHOICE WHERE topic = ?";
            PreparedStatement deleteQuestion = connection.prepareStatement(delQuery);
            deleteQuestion.setString(1, question.getTopics());
            deleteQuestion.executeUpdate();
            connection.close();



    }



    /**  SEARCHING MULTIPLE CHOICE QUESTIONS  BASED ON TOPIC   **************************************************************/

    public static ResultSet searchMcqQuestions(Question question, MCQAnswer mcqAnswer) throws SQLException {

 //       DATABASE CONNECTION STRINGS
        DbConnectionStrings dbConnectionStrings = new DbConnectionStrings();
        Connection connection = dbConnectionStrings.getConnection();

        final String readQuery = "SELECT * FROM MULTIPLECHOICE WHERE topic = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(readQuery);
        preparedStatement.setString(1, question.getTopics());
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;

    }


    /**  UPDATING  MULTIPLE CHOICE QUESTIONS   BY ID   ********************************************************************8**/
    public  void updateMcqQuestions(Question question,MCQChoice mcqChoice,Integer questionId) {

//       DATABASE CONNECTION STRINGS
        DbConnectionStrings dbConnectionStrings = new DbConnectionStrings();
        Connection connection = dbConnectionStrings.getConnection();
        PreparedStatement updateQuestion = null;
        try {

            updateQuestion = connection.prepareStatement("UPDATE MULTIPLECHOICE SET question = ?, Q_options = ?, +,topic = ?," +
                    "difficulty = ?," +
                    "answer = ? WHERE id = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            updateQuestion.setString(1, question.getQuestion());
            updateQuestion.setString(2, mcqChoice.getChoice());
            updateQuestion.setString(3, question.getTopics());
            updateQuestion.setInt(4, question.getDifficulty());
            updateQuestion.setString(5, String.valueOf(mcqChoice.getMcqAnswers()));
            updateQuestion.setInt(6, questionId);
            updateQuestion.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
