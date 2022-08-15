package fr.epita.quizProject;

import main.fr.epita.quizProject.datamodel.MCQAnswer;
import main.fr.epita.quizProject.datamodel.MCQChoice;
import main.fr.epita.quizProject.datamodel.Question;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TestMcqUpdate {
    public static void main(String[] args) throws SQLException {


/****  Contest  Data   ****/
        Scanner scanner = new Scanner(System.in);
        System.out.println("New Updated question");
        String newQuestion = scanner.nextLine();
        System.out.println("Enter the question choices/options");
        String options = scanner.nextLine();
        System.out.println("Enter  topic category");
        String newTopic = scanner.nextLine();
        System.out.println("Enter new Difficulty ");
        Boolean valid = true;
        Integer newDifficulty = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter new Answer to question");
        MCQAnswer newAnswer = new MCQAnswer(scanner.nextLine());
        System.out.println("Enter Id of question to update");
        Integer questionId = Integer.valueOf(scanner.nextLine());



        Connection connection = getConnection();
        PreparedStatement updateQuestion = null;
            Question question = new Question(newQuestion,newTopic,newDifficulty);
            MCQChoice mcqChoice = new MCQChoice(options,valid,newAnswer);

            updateQuestion = connection.prepareStatement("UPDATE MULTIPLECHOICE SET question = ?, Q_options = ?, +,topic = ?," +
                    "difficulty = ?," +
                    "answer = ? WHERE id = ?");

            updateQuestion.setString(1, question.getQuestion());
            updateQuestion.setString(2, mcqChoice.getChoice());
            updateQuestion.setString(3, question.getTopics());
            updateQuestion.setInt(4, question.getDifficulty());
            updateQuestion.setString(5, String.valueOf(mcqChoice.getMcqAnswers()));
            updateQuestion.setInt(6, questionId);

/***  Action   *****/
            updateQuestion.executeUpdate();

 /***** Checking ****/

        System.out.println("Update succesful");


    }


    private static Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/QUIZ","postgres","B00kw0rm1");

    }
}
