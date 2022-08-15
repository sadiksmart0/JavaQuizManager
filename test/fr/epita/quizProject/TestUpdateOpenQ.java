package fr.epita.quizProject;

import main.fr.epita.quizProject.datamodel.Answer;
import main.fr.epita.quizProject.datamodel.Question;
import main.services.data.dao.QuestionJDBCDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TestUpdateOpenQ {

    public static void main(String[] args) throws SQLException {


  /********   Context Data   ******************/
        Scanner scanner = new Scanner(System.in);
        System.out.println("New Updated question");
        String newQuestion = scanner.nextLine();
        System.out.println("Enter  topic category");
        String newTopic = scanner.nextLine();
        System.out.println("Enter new Difficulty ");
        Integer newDifficulty = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter new Answer to question");
        String ans = scanner.nextLine();
        Answer newAnswer = new Answer(ans);
        System.out.println("Enter Id of question to update");
        Integer questionId = Integer.valueOf(scanner.nextLine());

        Question question = new Question(newQuestion,newTopic,newDifficulty,newAnswer);


        Connection connection = getConnection();
        PreparedStatement updateQuestion = null;

            updateQuestion = connection.prepareStatement("UPDATE OPENQUESTIONS SET question = ?,topic = ?," +
                    "difficulty = ?," +
                    "answer = ? WHERE id = ?");


            updateQuestion.setString(1, question.getQuestion());
            updateQuestion.setString(2, String.valueOf(question.getTopics()));
            updateQuestion.setInt(3, question.getDifficulty());
            updateQuestion.setString(4, String.valueOf(question.getOpenAnswer()));
            updateQuestion.setInt(5, questionId);
            updateQuestion.executeUpdate();



            System.out.println("Update Successful");
            scanner.close();


        }
        private static Connection getConnection() throws SQLException {

            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/QUIZ","postgres","B00kw0rm1");

        }
}
