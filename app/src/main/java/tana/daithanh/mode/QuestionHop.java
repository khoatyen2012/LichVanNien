package tana.daithanh.mode;

/**
 * Created by Administrator on 21/03/2018.
 */

public class QuestionHop {

    String question;
    String answers;

    public  QuestionHop( String question,String answers)
    {
        this.question=question;
        this.answers=answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }



    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }


}
