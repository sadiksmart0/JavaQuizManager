package main.fr.epita.quizProject.datamodel;


import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String id;

    private List<Answer> answer;

    private ArrayList<MCQAnswer> mcqAnswer;


    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Student(String name, String id, List<Answer> answer) {
        this.name = name;
        this.id = id;
        this.answer = answer;

    }

    public Student(String name, String id, ArrayList<MCQAnswer> mcqAnswer) {
        this.name = name;
        this.id = id;
        this.mcqAnswer = mcqAnswer;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public List<MCQAnswer> getMcqAnswer() {
        return mcqAnswer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }

    public void setMcqAnswer(ArrayList<MCQAnswer> mcqAnswer) {
        this.mcqAnswer = mcqAnswer;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", answer=" + answer +
                ", mcqAnswer=" + mcqAnswer +
                '}';
    }
}