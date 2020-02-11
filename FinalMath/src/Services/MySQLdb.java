package Services;

import Models.MathTopic;
import Models.Question;
import Models.Quiz;
import Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLdb {
    private Connection connection = null;
    private static MySQLdb instance = null;
    private Statement statement = null;

    private MySQLdb() {
        try {
            //loading drivers for mysql
            Class.forName("com.mysql.cj.jdbc.Driver");

            //creating connection with the database
            connection = DriverManager.getConnection("", "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized MySQLdb getInstance() {
        if (instance == null) {
            instance = new MySQLdb();
        }
        return instance;
    }

    public String getRole(String username) throws SQLException {
        final String queryLogin = "SELECT role from users WHERE username = ?";
        PreparedStatement ps = connection.prepareStatement(queryLogin);
        ps.setString(1, username);
        final ResultSet resultSet = ps.executeQuery();
        String role = null;
        if (resultSet.next()) {
            role = resultSet.getString("role");
        }
        ps.close();
        resultSet.close();
        return role;
    }

    // return false if user name exist
    public User registerUser(String fname, String lname, String username,
                             String password, String role) throws SQLException {
        // check if this username already exists
        final String queryCheck = "SELECT count(*) from users WHERE username= ?";
        PreparedStatement ps = connection.prepareStatement(queryCheck);
        ps.setString(1, username);
        final ResultSet resultSet = ps.executeQuery();
        boolean exists = false;
        if (resultSet.next()) {
            if (resultSet.getInt(1) > 0) {
                exists = true;
            }
        }
        ps.close();
        resultSet.close();
        if (exists) {
            return null;
        }
        connection.setAutoCommit(false);
        final String insertUser = "INSERT INTO users VALUES (?,?,?,?,?)";
        ps = connection.prepareStatement(insertUser);
        ps.setString(1, username);
        ps.setString(2, fname);
        ps.setString(3, lname);
        ps.setString(4, password);
        ps.setString(5, role);
        ps.executeUpdate();
        ps.close();
        User user = new User(username, fname, lname, role);
        // Finally, commit the transaction.
        connection.commit();
        connection.setAutoCommit(true);
        return user;
    }

    // null means not correct username or password
    public User login(String uname, String password) throws SQLException {
        final String queryLogin = "SELECT username, firstname, lastname, role, password FROM users " +
                "WHERE username= ? AND password = ?";
        PreparedStatement ps = connection.prepareStatement(queryLogin);
        ps.setString(1, uname);
        ps.setString(2, password);
        final ResultSet resultSet = ps.executeQuery();
        User user = null;
        if (resultSet.next()) {
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String username = resultSet.getString("username");
            String role = resultSet.getString("role");
            user = new User(username, firstname, lastname, role);
        }
        ps.close();
        resultSet.close();
        return user;
    }

    public Boolean addMathTopic(String topic) throws SQLException {
        Boolean match = false;
        final String checkTopic = "SELECT topic_name FROM math_topics";
        statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(checkTopic);
        while (resultSet.next()) {
            if (resultSet.getString("topic_name").equals(topic)) {
                match = true;
            }
        }
        statement.close();
        resultSet.close();
        if(match) {
            return false;
        }
        final String addTopic = "INSERT INTO math_topics(topic_name) VALUES (?)";
        PreparedStatement ps = connection.prepareStatement(addTopic);
        ps.setString(1, topic);
        ps.executeUpdate();
        return true;
    }


    public Boolean deleteMathTopic(String topic) throws SQLException {
        final String deleteTopic = "DELETE FROM math_topics WHERE topic_id = ?";
        PreparedStatement ps = connection.prepareStatement(deleteTopic);
        ps.setString(1, topic);
        ps.executeUpdate();
        return true;
    }

    public Boolean addQuestion(String math_topic, String points, String mathml) throws SQLException {
        final String addQuestion = "INSERT INTO math_questions(topic_id, question, points) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(addQuestion);
        ps.setString(1, math_topic);
        ps.setString(2, mathml);
        ps.setString(3, points);
        ps.executeUpdate();
        return true;
    }

    public Boolean deleteQuestion(String id) throws SQLException {
        final String deleteQuestion = "DELETE FROM math_questions WHERE question_id = ?";
        PreparedStatement ps = connection.prepareStatement(deleteQuestion);
        ps.setString(1, id);
        ps.executeUpdate();
        return true;
    }

    public Boolean createQuiz(String quiz_name, String due_date, String[] selected_questions) throws SQLException {
        Boolean flag = false;
        final String checkQuiz = "SELECT quiz_name FROM math_quizzes";
        statement = connection.createStatement();
        final ResultSet resultSet1 = statement.executeQuery(checkQuiz);
        while (resultSet1.next()) {
            if (resultSet1.getString("quiz_name").equals(quiz_name)) {
                flag = true;
            }
        }
        statement.close();
        resultSet1.close();
        if(flag) {
            return false;
        }

        final String addInQuiz = "INSERT INTO math_quizzes (quiz_name, duedate) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(addInQuiz, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, quiz_name);
        ps.setString(2, due_date);
        ps.executeUpdate();
        ResultSet resultSet = ps.getGeneratedKeys();
        int key = 0;
        if (resultSet.next()) {
            key = resultSet.getInt(1);
        }

        for (int i = 0; i < selected_questions.length; i++) {
            String addEntry = "INSERT INTO quiz_questions (quiz_id, question_id) VALUES (?, ?)";
            PreparedStatement ps1 = connection.prepareStatement(addEntry);
            ps1.setInt(1, key);
            ps1.setInt(2, Integer.parseInt(selected_questions[i]));
            ps1.executeUpdate();
        }
        return true;
    }

    public List<MathTopic> getMathTopics() throws SQLException {
        String query = "select * from math_topics";
        List<MathTopic> list = new ArrayList<MathTopic>();
        MathTopic topics = null;
        statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int topic_id = resultSet.getInt("topic_id");
            String topic_name = resultSet.getString("topic_name");
            topics = new MathTopic(topic_id, topic_name);
            list.add(topics);
        }
        statement.close();
        resultSet.close();
        return list;
    }

    public List<User> getStudents() throws SQLException {
        String query = "select * from users where role='student'";
        List<User> list = new ArrayList<User>();
        User user = null;
        statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String username = resultSet.getString("username");
            String role = resultSet.getString("role");
            user = new User(username, firstname, lastname, role);
            list.add(user);
        }
        statement.close();
        resultSet.close();
        return list;
    }

    public List<Question> getMathQuestions() throws SQLException {
        String query = "select A.question, A.question_id, A.points, B.topic_name from math_questions A, math_topics B where A.topic_id = B.topic_id";
        List<Question> list = new ArrayList<Question>();
        Question question = null;
        statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("question_id");
            String que = resultSet.getString("question");
            int points = resultSet.getInt("points");
            String topic_name = resultSet.getString("topic_name");
            question = new Question(id, que, points, topic_name);
            list.add(question);
        }
        statement.close();
        resultSet.close();
        return list;
    }

    public List<Quiz> getQuizList() throws SQLException {
        String query = "select A.quiz_id, A.quiz_name, A.duedate, SUM(B.points) as total_points, " +
                "group_concat(B.question_id) as question_ids from math_quizzes A, math_questions B, quiz_questions C " +
                "where A.quiz_id = C.quiz_id and B.question_id = C.question_id group by quiz_id;";
        List<Quiz> list = new ArrayList<Quiz>();
        Quiz quiz = null;
        statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int points = resultSet.getInt("total_points");
            String que = resultSet.getString("question_ids");
            int quiz_id = resultSet.getInt("quiz_id");
            String quiz_name = resultSet.getString("quiz_name");
            Date due = resultSet.getDate("duedate");
            quiz = new Quiz(quiz_id, quiz_name, points, due, que);
            list.add(quiz);
        }
        statement.close();
        resultSet.close();
        return list;
    }

    public int[] countAll() throws SQLException {
        String query = "SELECT  (SELECT COUNT(*)\n" +
                "        FROM   math_questions) AS que,\n" +
                "        (SELECT COUNT(*)\n" +
                "        FROM   math_quizzes) AS quiz,\n" +
                "        (SELECT COUNT(*)\n" +
                "        FROM   users) AS user";
        int[] countArray = {0,0,0};
        statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            int user_count = resultSet.getInt("user");
            int question_count = resultSet.getInt("que");
            int quiz_count = resultSet.getInt("quiz");
            countArray[0] = user_count;
            countArray[1] = question_count;
            countArray[2] = quiz_count;
        }
        statement.close();
        resultSet.close();
        return countArray;
    }

    public int[] countForStudent() throws SQLException {
        String query = "SELECT  (SELECT COUNT(*)\n" +
                "        FROM   math_questions) AS que,\n" +
                "        (SELECT COUNT(*)\n" +
                "        FROM   math_quizzes) AS quiz";
        int[] countArray = {0,0};
        statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            int question_count = resultSet.getInt("que");
            int quiz_count = resultSet.getInt("quiz");
            countArray[0] = question_count;
            countArray[1] = quiz_count;
        }
        statement.close();
        resultSet.close();
        return countArray;
    }

    public String getQuestionFromId(int id) throws SQLException {
        String query = "SELECT question FROM math_questions WHERE question_id='"+id+"'";
        statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(query);
        String question = null;
        if (resultSet.next()) {
            question = resultSet.getString("question");
        }
        statement.close();
        resultSet.close();
        return question;
    }

    public List<Question> getQuestionListFromId(int id) throws SQLException {
        String query = "SELECT * FROM math_questions A, quiz_questions B, math_quizzes C, math_topics D WHERE C.quiz_id='"+id+"' " +
                "AND C.quiz_id = B.quiz_id AND B.question_id = A.question_id AND A.topic_id = D.topic_id";
        statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(query);
        List<Question> newList = new ArrayList<>();
        Question question = null;
        while (resultSet.next()) {
            int que_id = resultSet.getInt("question_id");
            String que = resultSet.getString("question");
            String topic = resultSet.getString("topic_name");
            int points = resultSet.getInt("points");
            question = new Question(que_id, que, points, topic);
            newList.add(question);
        }
        statement.close();
        resultSet.close();
        return newList;
    }

}