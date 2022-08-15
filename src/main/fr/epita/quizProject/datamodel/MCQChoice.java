package main.fr.epita.quizProject.datamodel;
public class MCQChoice {
    private String choice;
    private Boolean valid;
    private MCQAnswer mcqAnswers;

    public MCQChoice(String choice, Boolean valid, MCQAnswer mcqAnswers) {
        this.choice = choice;
        this.valid = valid;
        this.mcqAnswers = mcqAnswers;
    }



    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public MCQAnswer getMcqAnswers() {
        return mcqAnswers;
    }

    public void setMcqAnswers(MCQAnswer mcqAnswers) {
        this.mcqAnswers = mcqAnswers;
    }


}