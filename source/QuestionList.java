


import javax.swing.JButton;


public class QuestionList {
    public String question;
    public String[] options;
    public JButton[] optionsClicked;
    public String correctString;

    public QuestionList(String question, String[] options, String correctString) {
        this.question = question;
        this.options = options;
        this.correctString = correctString;
        this.optionsClicked = new JButton[options.length];
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectString() {
        return correctString;
    }

    public JButton[] getOptionsClicked() {
        return optionsClicked;
    }
}
