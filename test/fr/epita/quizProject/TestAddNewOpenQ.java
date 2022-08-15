package fr.epita.quizProject;

import main.fr.epita.quizProject.datamodel.Answer;
import main.fr.epita.quizProject.datamodel.Question;
import main.services.data.dao.QuestionJDBCDAO;

import java.sql.*;
import java.util.Scanner;

public class TestAddNewOpenQ {
    public static void main(String[] args) throws SQLException {


/**  Context Data /   Retrieving from user
 *
 */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter questions with option");
        String newQuestion = scanner.nextLine();
        System.out.println("Enter topic category");
        String newTopic = scanner.nextLine();
        System.out.println("Enter difficulty 1-easy, 2-medium, 3- hard");
        Integer newDifficulty = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter the correct Answer");
        String newAnswer = scanner.nextLine();


        Question question = new Question(newQuestion,newTopic,newDifficulty);
        Answer answer = new Answer(newAnswer);
        Connection connection = getConnection();

        String insertQuery = "INSERT INTO OPENQUESTIONS( question, topic, difficulty, answer)" + "values(?, ?, ?,?)";
        PreparedStatement ps = connection.prepareStatement(insertQuery);
        ps.setString(1, question.getQuestion());
        ps.setString(2, question.getTopics());
        ps.setInt(3, question.getDifficulty());
        ps.setString(4, answer.getText());

 /**  Action /  Inserting to database  **/
        ps.execute();
        connection.close();

/****** Checking  /    printing contents of table      ************/
        final String readQuery = "SELECT * FROM MCQQUESTIONS WHERE topic = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(readQuery);
        preparedStatement.setString(1, question.getTopics());
        ResultSet resultSet = preparedStatement.executeQuery();

        int i = 1;
        while (resultSet.next()){
            System.out.println(i+")"+resultSet.getString(2));
            i++;
        }

        System.out.println("Added successfully");
        scanner.close();

        }


// Database connection strings
    private static Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/QUIZ","postgres","B00kw0rm1");

    }
}
