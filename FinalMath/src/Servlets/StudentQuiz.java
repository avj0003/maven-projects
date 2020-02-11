package Servlets;

import Models.Question;
import Models.Quiz;
import Models.User;
import Services.MySQLdb;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StudentQuiz")
public class StudentQuiz extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MySQLdb db = MySQLdb.getInstance();
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                User user = (User) session.getAttribute("user");
                if (user.getRole().equals("student")) {
                    List<Quiz> quizList = db.getQuizList();
                    request.setAttribute("quizzes", quizList);
                    RequestDispatcher requestDispatcher =
                            request.getRequestDispatcher("/pages/student/quiz.jsp");
                    requestDispatcher.forward(request, response);
                }
            } else {
                request.setAttribute("message", "Please login to continue.!");
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
        } catch (SQLException ex) {
            request.setAttribute("message", "Unsuccessful. The database is down!");
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
