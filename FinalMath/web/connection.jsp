<%@ page import="java.sql.DriverManager" %>
<%
    java.sql.Connection conn;
    java.sql.ResultSet rs;
    java.sql.Statement st;

    Class.forName("com.mysql.jdbc.Driver");

    //creating connection with the database
    conn = DriverManager.getConnection
            ("jdbc:mysql://localhost:3306/mymath","root","123456");
    st = conn.createStatement();
%>