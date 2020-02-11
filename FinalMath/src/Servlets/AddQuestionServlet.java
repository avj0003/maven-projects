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

@WebServlet(name = "AddQuestionServlet")
public class AddQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user.getRole().equals("professor")) {
                String math_topic = request.getParameter("topic_id");
                String points = request.getParameter("points");
                String mathml = request.getParameter("question_mathml");
                MySQLdb db = MySQLdb.getInstance();
                try {
                    Boolean tag = db.addQuestion(math_topic, points, mathml);
                    if (tag) {
                        request.setAttribute("message",
                                "Question added successfully!");
                        request.getRequestDispatcher("/QuestionsServlet").include(request,
                                response);
                    } else {
                        request.setAttribute("message",
                                "Error! Couldn't add question.");
                        request.getRequestDispatcher("/QuestionsServlet").include(request,
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
