package Servlets;

import Models.Question;
import Models.User;
import Services.MySQLdb;
import org.nfunk.jep.JEP;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AttemptQuestion")
public class AttemptQuiz extends HttpServlet {
    private static int coefficient;
    private static int constant;
    String s = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MySQLdb db = MySQLdb.getInstance();
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user.getRole().equals("student")) {
                int id = Integer.parseInt(request.getParameter("id"));
                try {
                    List<Question> questions = db.getQuestionListFromId(id);
                    request.setAttribute("questions", questions);
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                RequestDispatcher requestDispatcher =
                        request.getRequestDispatcher("/pages/student/each_quiz.jsp");
                requestDispatcher.forward(request, response);
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
