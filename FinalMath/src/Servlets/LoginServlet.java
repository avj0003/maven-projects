package Servlets;

import Models.MathTopic;
import Services.MySQLdb;
import Models.User;
import Services.PasswordSecurity;

import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            PasswordSecurity passwordSecurity = new PasswordSecurity();
            MySQLdb db = MySQLdb.getInstance();
            User user = db.login(username, passwordSecurity.encrypt(password));
            if (user != null) {
                if (user.getRole().equals("student")) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    RequestDispatcher requestDispatcher =
                            request.getRequestDispatcher("/StudentDashboard");
                    requestDispatcher.forward(request, response);
                }
                if (user.getRole().equals("professor")) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    RequestDispatcher requestDispatcher =
                            request.getRequestDispatcher("/DashboardServlet");
                    requestDispatcher.forward(request, response);
                }
            } else {
                request.setAttribute("message", "Sorry, username or password is incorrect!");
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
        } catch (SQLException | NoSuchPaddingException | InvalidKeyException | NoSuchAlgorithmException | InvalidAlgorithmParameterException ex) {
            request.setAttribute("message", "Login unsuccessful. The database is down!");
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
    }
}