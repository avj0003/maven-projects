package Models;

import java.util.Date;
import java.util.List;

public class Quiz {

    protected String quiz_name;
    protected int quiz_id;
    protected int total_points;
    protected Date duedate;
    protected String questions;

    public int getQuizId() {
        return quiz_id;
    }

    public String getQuizName() {
        return quiz_name;
    }

    public int getPoints() {
        return total_points;
    }

    public Date getDueDate() {
        return duedate;
    }

    public String getQuestionsList() {
        return questions;
    }

    public Quiz(int id, String name, int points, Date due, String questionlist) {
        quiz_id = id;
        quiz_name = name;
        total_points = points;
        duedate = due;
        questions = questionlist;
    }
}
