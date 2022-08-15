package main.services;

import main.fr.epita.quizProject.datamodel.MCQChoice;
import main.fr.epita.quizProject.datamodel.Question;
import main.services.data.dao.LoadQuestionDAO;

import java.util.Scanner;

public class AdminService {
     QuizService quizService = new QuizService();

     /**  LINK TO VARIOUS ACTIONS TO BE CARRIED OUT BY ADMIN **************/
    public void adminTask(){
        Scanner myscanner = new Scanner(System.in);

        System.out.println("If this is your first time...SELECT J to load Questions\n");
        System.out.println("What would you like to perform?" +
                "\n(a)Add MCQ Question\n(b)Add Open Question\n(c)Delete MCQ Question" +
                "\n(d)Delete Open Question\n(e)update MCQ Question\n(f)update Open Question" +
                "\n(g)Search Open Question\n(h)Search MCQ Question\n(j) Load Questions");


        String task = myscanner.next();
        System.out.println(task);
        switch (task){
            case "a":
                quizService.createMcqQuestion();
                break;
            case "b":
                quizService.createOpenQuestion();
                break;
            case "c":
                quizService.deleteMcq();
                break;
            case "d":
                quizService.deleteOpen();
                break;
            case "e":
                quizService.updateMcq();
                break;
            case "f":
                quizService.updateOpenQ();
                break;
            case "g":
               quizService.searchOpen();
               break;
            case "h":
                quizService.searchMCQ();
                break;
            case "j":
                LoadQuestionDAO loadQuestionDAO = new LoadQuestionDAO();
                loadQuestionDAO.loadAllQuestion();
                System.out.println("Questions loaded successfully");
                break;
            default:
                System.out.println("Invalid selection");
        }



    }
}
