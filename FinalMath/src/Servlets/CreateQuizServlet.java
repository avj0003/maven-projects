package Servlets;

import Models.User;
import Services.MySQLdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CreateQuizServlet")
public class CreateQuizServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user.getRole().equals("professor")) {
                String quiz_name = request.getParameter("quiz_name");
                String due_date = request.getParameter("due_date");
                String[] selected_questions = request.getParameterValues("selected_questions");
                MySQLdb db = MySQLdb.getInstance();
                try {
                    Boolean tag = db.createQuiz(quiz_name, due_date, selected_questions);
                    if (tag) {
                        request.setAttribute("message",
                                "Quiz created successfully!");
                        request.getRequestDispatcher("/QuizServlet").include(request,
                                response);
                    } else {
                        request.setAttribute("message",
                                "Quiz already exists.!");
                        request.getRequestDispatcher("/QuizServlet").include(request,
                                response);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                session.invalidate();
                request.setAttribute("message", "Please Login to continue.");
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
        } else {
            request.setAttribute("message", "Please Login to continue.");
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
