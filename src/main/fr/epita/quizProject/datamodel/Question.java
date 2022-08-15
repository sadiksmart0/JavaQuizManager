package main.fr.epita.quizProject.datamodel;


public class Question {
    private String question;
    private String topics;
    private Integer difficulty;

    private Answer openAnswer;

    public Question(String question, String topics, Integer difficulty, Answer openAnswer) {
        this.question = question;
        this.topics = topics;
        this.difficulty = difficulty;
        this.openAnswer = openAnswer;
    }

    public Question(String question, String topics, Integer difficulty) {
        this.question = question;
        this.topics = topics;
        this.difficulty = difficulty;

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }


    public Answer getOpenAnswer() {
        return openAnswer;
    }

    public void setOpenAnswer(Answer openAnswer) {
        this.openAnswer = openAnswer;
    }
}




