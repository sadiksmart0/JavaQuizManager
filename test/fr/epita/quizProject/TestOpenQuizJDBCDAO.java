package fr.epita.quizProject;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestOpenQuizJDBCDAO {


    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();
/** Context data ***/
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter Level from Between 1-Easy, 2-Intermediate, 3-Hard");
        Integer level = Integer.valueOf(scanner.nextLine());

        String readQuery = "SELECT * From openquestions WHERE difficulty = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(readQuery);
        preparedStatement.setInt(1,level);


        /**  Action
         *********************************************************************/
        ResultSet resultSet = preparedStatement.executeQuery();

 /** checking
       * *********************************************************************/
        int i = 1;
        int score = 0;

        List<String> userEntry = new ArrayList<>();
        int k = 0;
        while (resultSet.next()){

            System.out.println(i+")"+resultSet.getString(2));
            String choice = scanner.nextLine().toUpperCase();
            userEntry.add(choice);
            if (choice.equals(resultSet.getString(5))){
                score++;
            }
            i++;

        }

//            Print final result
        ResultSet resultSet1 = preparedStatement.executeQuery();
        while(resultSet1.next()){
            System.out.println(k+1+") "+userEntry.get(k));
            System.out.println("Correct:"+ resultSet1.getString(5) );
            System.out.println();
            k++;
        }
        System.out.println("\nyou scored:  "+score+"/"+userEntry.size());
        int percentageScore = (score * 100)/userEntry.size();
        System.out.println("\nYour percentage score is = "+percentageScore+"%");
        scanner.close();


    }

    private static Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/QUIZ","postgres","B00kw0rm1");

    }
}
