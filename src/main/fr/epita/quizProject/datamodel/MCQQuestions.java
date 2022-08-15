package main.fr.epita.quizProject.datamodel;

public class MCQQuestions extends Question {
    private MCQChoice mcqChoices;

    public MCQQuestions(String question, String topics, Integer difficulty, MCQChoice mcqChoices) {
        super(question, topics, difficulty);
        this.mcqChoices = mcqChoices;
    }






}
