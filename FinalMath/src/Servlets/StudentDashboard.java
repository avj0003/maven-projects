package Servlets;

import Models.MathTopic;
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

@WebServlet(name = "DashboardServlet")
public class StudentDashboard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MySQLdb db = MySQLdb.getInstance();
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user.getRole().equals("student")) {
                try {
                    List<Quiz> quizList = db.getQuizList();
                    request.setAttribute("quizzes", quizList);
//                    List<User> studnetlist = db.getStudents();
//                    request.setAttribute("users", studnetlist);
                    int[] countList = db.countForStudent();
                    request.setAttribute("count", countList);
                    RequestDispatcher requestDispatcher =
                            request.getRequestDispatcher("/pages/student/dashboard_student.jsp");
                    requestDispatcher.forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            request.setAttribute("message", "Please login to continue.!");
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
