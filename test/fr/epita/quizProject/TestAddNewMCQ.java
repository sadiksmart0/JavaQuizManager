package fr.epita.quizProject;

import main.fr.epita.quizProject.datamodel.MCQAnswer;
import main.fr.epita.quizProject.datamodel.MCQChoice;
import main.fr.epita.quizProject.datamodel.Question;

import java.sql.*;
import java.util.Scanner;

public class TestAddNewMCQ {
   public static void main(String[] args) throws SQLException {

/***      Context Data
 * Get question details from user
 * ***/
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter NEW questions ");
      String newQuestion = scanner.nextLine();
      System.out.println("Enter multiple choices/options");
      String option = scanner.nextLine();
      System.out.println("Enter topic category");
      String newTopic = scanner.nextLine();
      System.out.println("Enter difficulty 1-easy, 2-medium, 3- hard");
      Integer newDifficulty = Integer.valueOf(scanner.nextLine());
      System.out.println("Enter the correct Answer");
      String mcqAns = scanner.nextLine();
      MCQAnswer newAnswer = new MCQAnswer(mcqAns);
      Boolean valid = true;

      Connection connection = getConnection();

      Question question = new Question(newQuestion, newTopic,newDifficulty);
      MCQChoice mcqChoice = new MCQChoice(option, valid,newAnswer);

      final String insertQuery = "INSERT INTO MULTIPLECHOICE( question,Q_options, topic, difficulty, answer)" + "values(?,?,?, ?,?)";
      PreparedStatement ps = connection.prepareStatement(insertQuery);
      ps.setString(1, question.getQuestion());
      ps.setString(2, mcqChoice.getChoice());
      ps.setString(3, question.getTopics());
      ps.setInt(4, question.getDifficulty());
      ps.setString(5, String.valueOf(mcqChoice.getMcqAnswers()));

/***     Action/   Inserting into Database   *****/
      ps.execute();


/**** Checking /    Printing contents of database   *****/

      final String readQuery = "SELECT * FROM MCQQUESTIONS WHERE topic = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(readQuery);
      preparedStatement.setString(1, question.getTopics());
      ResultSet resultSet = preparedStatement.executeQuery();

      int i = 1;
      while (resultSet.next()){
         System.out.println(i+")"+resultSet.getString(2));
         i++;
      }
      System.out.println("Added Successfully");
      scanner.close();
      connection.close();
   }

//   Data base connection strings
   private static Connection getConnection() throws SQLException {

      return DriverManager.getConnection("jdbc:postgresql://localhost:5432/QUIZ","postgres","B00kw0rm1");

   }
}