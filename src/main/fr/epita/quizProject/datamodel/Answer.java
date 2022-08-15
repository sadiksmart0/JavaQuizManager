package main.fr.epita.quizProject.datamodel;

public class Answer {
    String text;





    public Answer(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

// TO STRING
    @Override
    public String toString() {
        return text;
    }
}
