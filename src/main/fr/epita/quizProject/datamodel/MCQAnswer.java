package main.fr.epita.quizProject.datamodel;

public class MCQAnswer {
//  Student student;
    private String answerMcq;

    public MCQAnswer(String answerMcq) {
        this.answerMcq = answerMcq;
    }

    public String getAnswerMcq() {
        return answerMcq;
    }

    public void setAnswerMcq(String answerMcq) {
        this.answerMcq = answerMcq;
    }

    @Override
    public String toString() {
        return answerMcq;
    }
}

