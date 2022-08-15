package fr.epita.quizProject;

import main.fr.epita.quizProject.datamodel.Question;

import java.sql.*;
import java.util.Scanner;

public class TestDeleteOpenQ {

    public static void main(String[] args) throws SQLException {
/****** Context Data        *************/
        Scanner scanner = new Scanner(System.in);
        String delQuestion = null;
        System.out.println("Enter topic category");
        String delTopic = scanner.nextLine();
        Integer delDifficulty = null;
        String delAnswer = null;
        Question question = new Question(delQuestion, delTopic,delDifficulty);

        Connection connection = getConnection();
        String delQuery = "DELETE FROM OPENQUESTIONS WHERE topic = ?";
        PreparedStatement deleteQuestion = connection.prepareStatement(delQuery);
        deleteQuestion.setString(1, String.valueOf(question.getTopics()));

/******  Action   *****************/
        deleteQuestion.executeUpdate();


/*******   Checking    *************************/

        final String readQuery = "SELECT * FROM OPENQUESTIONS WHERE topic = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(readQuery);
        preparedStatement.setString(1, question.getTopics());
        ResultSet resultSet = preparedStatement.executeQuery();

        int i = 1;
        while (resultSet.next()){
            System.out.println(i+")"+resultSet.getString(2));
            i++;
        }

    }

//    Database connection strings
    private static Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/QUIZ","postgres","B00kw0rm1");

    }
}