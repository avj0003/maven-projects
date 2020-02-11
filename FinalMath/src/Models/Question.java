package Models;

public class Question {

    protected String question;
    protected String topic_name;
    protected int points;
    protected int question_id;

    public int getQuestionId() {
        return question_id;
    }

    public String getQuestion() {
        return question;
    }

    public String getTopicName() {
        return topic_name;
    }

    public int getPoints() {
        return points;
    }

    public Question(int id, String que, int point, String tname) {
        question_id = id;
        topic_name  = tname;
        points = point;
        question = que;
    }
}
