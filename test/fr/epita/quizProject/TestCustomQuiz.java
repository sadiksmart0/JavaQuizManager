package fr.epita.quizProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestCustomQuiz {

    public static void main(String[] args) throws SQLException {

  /**  Context Data /   Retrieving      question ***/

        Connection connection = getConnection();
        String readQuery = "SELECT * FROM MULTIPLECHOICE";
        PreparedStatement preparedStatement = connection.prepareStatement(readQuery);
        ResultSet resultSet = preparedStatement.executeQuery();


        Scanner scanner = new Scanner(System.in);
        System.out.println("First topic");
        String topic1 = scanner.nextLine();
        System.out.println("Second topic");
        String topic2 = scanner.nextLine();
        int score = 0;
        int i = 1;

        List<String> userChoice = new ArrayList<>();
        List<String> actualAnswer = new ArrayList<>();
        com.itextpdf.layout.element.List qlist = new com.itextpdf.layout.element.List();

/**   Action  /   Assembling quiz ***/
        while (resultSet.next()) {

            if (topic1.equals(resultSet.getString("topic"))||topic2.equals(resultSet.getString("topic"))){

                System.out.println(i+")"+resultSet.getString("question"));
                System.out.println(resultSet.getString("Q_options"));
                qlist.add(i+")"+resultSet.getString("question"));
                qlist.add(resultSet.getString("Q_options"));
                String choice = scanner.nextLine().toUpperCase();
                userChoice.add(choice);
                actualAnswer.add(resultSet.getString("answer"));
                if (choice.equals(resultSet.getString("answer"))){
                    score++;
                }


            }
            i++;
        }
        scanner.close();
        int k = 0;
        while(k < actualAnswer.size()){
            System.out.println(k+1+")"+userChoice.get(k));
            System.out.println("Correct:"+ actualAnswer.get(k));

            k++;
        }
        System.out.println("\nyou scored:  "+score+"/"+userChoice.size());
        int percentageScore = (score * 100)/userChoice.size();
        System.out.println("\nYour percentage score is = "+percentageScore+"%");
        scanner.close();


    }
// Connection strings
    private static Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/QUIZ","postgres","B00kw0rm1");

    }
}
