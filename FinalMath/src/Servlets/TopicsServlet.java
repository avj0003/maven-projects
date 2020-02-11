package Servlets;

import Models.MathTopic;
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

@WebServlet(name = "TopicsServlet")
public class TopicsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MySQLdb db = MySQLdb.getInstance();
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                User user = (User) session.getAttribute("user");
                if (user.getRole().equals("professor")) {
                    List<MathTopic> list = db.getMathTopics();
                    request.setAttribute("topics", list);
                    RequestDispatcher requestDispatcher =
                            request.getRequestDispatcher("/pages/professor/topics.jsp");
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
