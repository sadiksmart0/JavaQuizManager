package main.services;

import main.fr.epita.quizProject.datamodel.*;
import main.services.data.dao.MCQQuestionJDBCDAO;
import main.services.data.dao.ExportDAO;
import main.services.data.dao.QuestionJDBCDAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizService {


    /**  ASSEMBLING MULTIPLE CHOICE QUIZ ****************************************************************/
    MCQQuestionJDBCDAO mcqQuestionJDBCDAO = new MCQQuestionJDBCDAO();
    public void mcqQuiz(){

        System.out.println("Please Enter Level from Between 1-Easy, 2-Intermediate, 3-Hard");
        Scanner scanner = new Scanner(System.in);
        Integer difficulty = Integer.valueOf(scanner.nextLine());
        ArrayList<String> qlist = new ArrayList<>();

        try {
            ResultSet mcq = mcqQuestionJDBCDAO.readAllMcqDifficulty(difficulty);
            int i = 1;
            int score = 0;

// Dispaying Questions and Options

            List<String> userChoice = new ArrayList<>();
            while (mcq.next()){

                System.out.println(i+")"+mcq.getString(2));
                System.out.println(mcq.getString(3));
                qlist.add(mcq.getString("question"));
                qlist.add(mcq.getString("Q_options"));
                String choice = scanner.nextLine().toUpperCase();
                userChoice.add(choice);

//      Recording user score
                if (choice.equals(mcq.getString(6))){
                    score++;
                }
                i++;

            }

//      Printing final result
            int k = 0;

            ResultSet rs = mcqQuestionJDBCDAO.readAllMcqDifficulty(difficulty);
            while(rs.next()){
                System.out.println(k+1+") "+userChoice.get(k));
                System.out.println("Correct:"+ rs.getString(6) );
                k++;
            }
            System.out.println("\nyou scored:  "+score+"/"+userChoice.size());
            int percentageScore = (score  * 100)/userChoice.size();
            System.out.println("\nYour percentage score is = "+percentageScore+"%");
//      Exporting As text Quiz
            ExportDAO exportDAO = new ExportDAO();
            exportDAO.textExport(qlist);
            System.out.println("\n Exported as text Succesfully");
            scanner.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    /**  ASSEMBLING OF OPEN QUIZ ******************************************************************************/
    public void  openQuiz(){
        System.out.println("Please Enter Level from Between 1-Easy, 2-Intermediate, 3-Hard");
        Scanner scanner = new Scanner(System.in);
        Integer difficulty = Integer.valueOf(scanner.nextLine());

        try {
            ResultSet openQ = QuestionJDBCDAO.readAllOpenQ(difficulty);
            int i = 1;
            int score = 0;
            List<String> userEntry = new ArrayList<>();
            ArrayList<String> qlist = new ArrayList<>();
            int k = 0;

//  Displaying Questions and Options
        while (openQ.next()){

        System.out.println(i+")"+openQ.getString(2));
        qlist.add(openQ.getString("question"));
        String choice = scanner.nextLine().toUpperCase();
        userEntry.add(choice);
//   Recording Quiz score
        if (choice.equals(openQ.getString(5))){
            score++;
        }
        i++;

    }

//    Displaying final result
            ResultSet rs = QuestionJDBCDAO.readAllOpenQ(difficulty);

            while(rs.next()){
                System.out.println(k+1+") "+userEntry.get(k));
                System.out.println("Correct:"+ rs.getString(5) );
                System.out.println();
                k++;
            }
            System.out.println("\nyou scored:  "+score+"/"+userEntry.size());
            int percentageScore = (score * 100)/userEntry.size();
            System.out.println("\nYour percentage score is = "+percentageScore+"%");

//      Exporting As text Quiz
            ExportDAO exportDAO = new ExportDAO();
            exportDAO.textExport(qlist);
            System.out.println("\n Exported as text Succesfully");

            scanner.close();




} catch (SQLException e) {
        throw new RuntimeException(e);
        }
    }




/**  ADDING NEW OPEN QUESTIONS  ******************************************************/
    public String createOpenQuestion(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter questions with option");
        String newQuestion = scanner.nextLine();
        System.out.println("Enter topic category");
        String newTopic = scanner.nextLine();
        System.out.println("Enter difficulty 1-easy, 2-medium, 3- hard");
        Integer newDifficulty = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter the correct Answer");
        String newAnswer = scanner.nextLine();

        try {
//     Inserting values received from  user
            QuestionJDBCDAO.addOpenQuestion(new Question(newQuestion,newTopic,newDifficulty),new Answer(newAnswer));
            System.out.println("Added successfully");
            scanner.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    /** DELETING MULTIPLE CHOICE QUESTIONS  BY TOPIC  ***************************************/
    public String deleteMcq(){
        Scanner scanner = new Scanner(System.in);
        String delQuestion = null;
        System.out.println("Note: Input is case sensitive,First letter of Each word capitalised\n");
        System.out.println("Enter topic category");
        String delTopic = scanner.nextLine();
        Integer delDifficulty = null;
        String delAnswer = null;

        try {
//     Deleting  Questions  based on topic received from  user
            mcqQuestionJDBCDAO.deleteMCQQuestion(new Question(delQuestion,delTopic,delDifficulty),new MCQAnswer(delAnswer));
            System.out.println("Deleted Successfully");
            scanner.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /**  DELETING OPEN QUESTIONS BY TOPIC ***************************************************/
    public String deleteOpen(){
        Scanner scanner = new Scanner(System.in);
        String delQuestion = null;
        System.out.println("Note: Input is case sensitive,First letter of Each word capitalised\n");
        System.out.println("Enter topic category");
        String delTopic = scanner.nextLine();
        Integer delDifficulty = null;
        String delAnswer = null;

        try {
//     Deleting  Questions  based on topic received from  user
            QuestionJDBCDAO.deleteOpenQuestion(new Question(delQuestion,delTopic,delDifficulty),new Answer(delAnswer));
            System.out.println("Deleted Succesfully");
            scanner.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /**  SEARCHING OPEN QUESTIONS BY TOPIC ****************************************************/
    public ResultSet searchOpen(){
        Scanner scanner = new Scanner(System.in);
        String delQuestion = null;
        System.out.println("Note: Input is case sensitive,First letter of Each word capitalised\n");
        System.out.println("Enter topic category");
        String delTopic = scanner.nextLine();
        Integer delDifficulty = null;
        Answer delAnswer = null;

        try {
//     Searching  Questions  based on topic received from  user
            ResultSet sr = QuestionJDBCDAO.searchOpenQuestions(new Question(delQuestion,delTopic,delDifficulty, delAnswer));
            int i = 1;
            while (sr.next()){
                System.out.println(i+")"+sr.getString(2));
                i++;
            }
            scanner.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /**  SEARCHING MULTIPLE CHOICE QUESTION BY TOPIC ***********************************************************/
    public ResultSet searchMCQ(){
        Scanner scanner = new Scanner(System.in);
        String delQuestion = null;
        System.out.println("Note: Input is case sensitive,First letter of Each word capitalised\n");
        System.out.println("Enter topic category");
        String delTopic = scanner.nextLine();
        Integer delDifficulty = null;
        String delAnswer = null;

        try {
//     Searching  Questions  based on topic received from  user
            ResultSet sr = MCQQuestionJDBCDAO.searchMcqQuestions(new Question(delQuestion,delTopic,delDifficulty),new MCQAnswer(delAnswer));
            int i = 1;
            while (sr.next()){
                System.out.println(i+")"+sr.getString(2));
                System.out.println(sr.getString("Q_options"));
                i++;
            }
            scanner.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /**  UPDATING MULTIPLE CHOICE QUESTIONS BY ID *********************************************************/
    public void updateMcq(){

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

//     Updating  Questions  based on ID received from  user
        mcqQuestionJDBCDAO.updateMcqQuestions(new Question(newQuestion,newTopic,newDifficulty),new MCQChoice(options, valid, newAnswer), questionId);
        System.out.println("Update succesful");
        scanner.close();


    }

    /**  UPDATING MULTIPLE CHOICE QUESTIONS BY ID *******************************************************8*/
    public void updateOpenQ(){

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

//     Updating  Questions  based on ID received from  user
        QuestionJDBCDAO.updateOpenQuestions(new Question(newQuestion,newTopic,newDifficulty, newAnswer),questionId);
        System.out.println("Update Successful");
        scanner.close();


    }



    /**  ASSEMBLING MULTIPLE CHOICE QUIZ BASED ON TOPICS AND EXPORTING QUESTION AS PDF **********/

    public void customMcqQuiz(){


        try {
            ResultSet mcq = mcqQuestionJDBCDAO.readAllMcq();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Note: Input is case sensitive\n");
            System.out.println("First topic");
            String topic1 = scanner.nextLine();
            System.out.println("Second topic");
            String topic2 = scanner.nextLine();
            int score = 0;
            int i = 1;

            List<String> userChoice = new ArrayList<>();
            List<String> actualAnswer = new ArrayList<>();
            com.itextpdf.layout.element.List qlist = new com.itextpdf.layout.element.List();

            while (mcq.next()) {

                if (topic1.equals(mcq.getString("topic"))||topic2.equals(mcq.getString("topic"))){

                        System.out.println(i+")"+mcq.getString("question"));
                        System.out.println(mcq.getString("Q_options"));
//       Adding questions list for export
                        qlist.add(i+")"+mcq.getString("question"));
                        qlist.add(mcq.getString("Q_options"));
                        String choice = scanner.nextLine().toUpperCase();
                        userChoice.add(choice);
                        actualAnswer.add(mcq.getString("answer"));
//              Recording score
                        if (choice.equals(mcq.getString("answer"))){
                            score++;
                        }
                }
                i++;
            }
            scanner.close();

            int k = 0;

//   Displaying Final result
            while(k < actualAnswer.size()){
                System.out.println(k+1+")"+userChoice.get(k));
                System.out.println("Correct:"+ actualAnswer.get(k));

                k++;
            }
            System.out.println("\nyou scored:  "+score+"/"+userChoice.size());
            int percentageScore = (score*100)/userChoice.size();
            System.out.println("\nYour percentage score is ="+percentageScore+"%");
            scanner.close();

/** EXPORTING QUESTIONS TO PDF  *********************************************************************************8**/
             ExportDAO exportDAO = new ExportDAO();
             exportDAO.pdfExport(qlist);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public String createMcqQuestion(){

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


        try {
//     Inserting values received from  user
            mcqQuestionJDBCDAO.addMCQQuestion(new  Question(newQuestion,newTopic,newDifficulty),new MCQChoice(option,valid, newAnswer));
            System.out.println("Added Successfully");
            scanner.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}
