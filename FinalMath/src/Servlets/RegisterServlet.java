package Servlets;

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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    public RegisterServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");
        MySQLdb db = MySQLdb.getInstance();
        try {
            PasswordSecurity passwordSecurity = new PasswordSecurity();
            User user = db.registerUser(firstname, lastname, username, passwordSecurity.encrypt(password), User.STUDENT);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                RequestDispatcher requestDispatcher =
                        request.getRequestDispatcher("/StudentDashboard");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("message",
                        "Sorry, that username has been taken!");
                request.getRequestDispatcher("register.jsp").include(request,
                        response);
            }
        } catch (SQLException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | InvalidAlgorithmParameterException ex) {
            request.setAttribute("message",
                    "Registration unsuccessful. Database is down!");
            request.getRequestDispatcher("register.jsp").include(request,
                    response);
        }
    }

}
