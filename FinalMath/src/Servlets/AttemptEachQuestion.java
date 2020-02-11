package Servlets;

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
public class AttemptEachQuestion extends HttpServlet {
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
                    s = db.getQuestionFromId(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                JEP jep = new JEP();
                jep.setImplicitMul(true);
                jep.addStandardFunctions();
                jep.addStandardConstants();
                jep.addVariable("x", 0);

                String s0 = s.substring(0, s.indexOf("="));
                String s1 = s.substring(s.indexOf("=") + 1, s.length());
                List<String> leftright = new ArrayList<String>();
                leftright.add(s0);
                leftright.add(s1);

                System.out.println("First Step:" +leftright);
                jep.parseExpression(s0);
                if (jep.hasError()) {
                    throw new IllegalArgumentException(jep.getErrorInfo());
                }

                jep.addVariable("x", 0);
                int constant0 = (int) jep.getValue();
                jep.addVariable("x", 1);
                int value0 = (int) jep.getValue();
                int x0 = value0 - constant0;

                jep.parseExpression(s1);
                if (jep.hasError()) {
                    throw new IllegalArgumentException(jep.getErrorInfo());
                }

                jep.addVariable("x", 0);
                int constant1 = (int) jep.getValue();
                jep.addVariable("x", 1);
                int value1 = (int) jep.getValue();
                int x1 = value1 - constant1;

                List<Integer> simpleLeft = new ArrayList<Integer>();
                simpleLeft.add(x0);
                simpleLeft.add(constant0);
                System.out.println("Second Step:" +simpleLeft);

                List<Integer> simpleRight = new ArrayList<Integer>();
                simpleRight.add(x1);
                simpleRight.add(constant1);
                System.out.println("Third Step:" +simpleRight);

                if (x0 > x1) {
                    if (x1 >= 0) {
                        coefficient = x0 - x1;
                    } else {
                        coefficient = x0 + (-1*x1);
                    }
                    if(constant0 >= 0) {
                        constant = constant1 - constant0;
                    } else {
                        constant = constant1 + (-1*constant0);
                    }
                } else {
                    if (x0 >= 0) {
                        coefficient = x1 - x0;
                    } else {
                        coefficient = x1 + (-1*x0);
                    }
                    if(constant1 >= 0) {
                        constant = constant0 - constant1;
                    } else {
                        constant = constant0 + (-1*constant1);
                    }

                }
                List<Integer> solved = new ArrayList<Integer>();
                solved.add(coefficient);
                solved.add(constant);
                System.out.println("Fourth Step:" +solved);

                request.setAttribute("equation", s);
                request.setAttribute("first", leftright);
                request.setAttribute("second", simpleLeft);
                request.setAttribute("third", simpleRight);
                request.setAttribute("fourth", solved);

                RequestDispatcher requestDispatcher =
                        request.getRequestDispatcher("/pages/student/attempt_each_question.jsp");
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
