package fr.epita.quizProject;

import main.services.data.dao.ExportDAO;
import main.services.data.dao.MCQQuestionJDBCDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestMCQUIZJDBCDAO {

    public static void main(String[] args) throws SQLException {
/***   Context Data ****/
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter Level from Between 1-Easy, 2-Intermediate, 3-Hard");
        Integer level = Integer.valueOf(scanner.nextLine());
        Connection connection = getConnection();

        final String readQuery = "SELECT * FROM MULTIPLECHOICE WHERE DIFFICULTY = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(readQuery);
        preparedStatement.setInt(1, level);
        ResultSet resultSet = preparedStatement.executeQuery();

            int i = 1;
            int score = 0;



        /***   Action Data ****/

        ArrayList<String> qlist = new ArrayList<>();




        /***   Check Data ****/

        List<String> userChoice = new ArrayList<>();
        while (resultSet.next()){

            System.out.println(i+")"+resultSet.getString(2));
            System.out.println(resultSet.getString(3));
            qlist.add(resultSet.getString("question"));
            qlist.add(resultSet.getString("Q_options"));
            String choice = scanner.nextLine().toUpperCase();
            userChoice.add(choice);
            if (choice.equals(resultSet.getString(6))){
                score++;
            }
            i++;

        }
//            Print final result
        int k = 0;
        ResultSet resultSet1 = preparedStatement.executeQuery();
        while(resultSet1.next()){
            System.out.println(k+1+") "+userChoice.get(k));
            System.out.println("Correct:"+ resultSet1.getString(6) );
            k++;
        }
        System.out.println("\nyou scored:  "+score+"/"+userChoice.size());
        int percentageScore = (score * 100)/userChoice.size();
        System.out.println("\nYour percentage score is = "+percentageScore+"%");
        ExportDAO exportDAO = new ExportDAO();
        exportDAO.textExport(qlist);
        System.out.println("\n Exported as text Succesfully");
        scanner.close();

        }
// Database connection strings
    private static Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/QUIZ","postgres","B00kw0rm1");

    }
}
