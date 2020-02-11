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

@WebServlet(name = "DeleteQuestionServlet")
public class DeleteQuestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user.getRole().equals("professor")) {
                if (request.getParameter("id") != null) {
                    MySQLdb db = MySQLdb.getInstance();
                    try {
                        Boolean tag = db.deleteQuestion(request.getParameter("id"));
                        if (tag) {
                            request.setAttribute("message",
                                    "Question deleted successfully!");
                            request.getRequestDispatcher("/QuestionsServlet").include(request,
                                    response);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {

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
