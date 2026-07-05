package com.example.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Looks up a user by name. Vulnerable to SQL injection (CWE-89):
 * the request parameter flows unsanitized into a concatenated SQL query.
 */
public class UserSearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // SOURCE: attacker-controlled HTTP parameter
        String username = request.getParameter("username");

        PrintWriter out = response.getWriter();
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/appdb", "root", "root");
            Statement stmt = conn.createStatement();

            // SINK: unsanitized input concatenated directly into the SQL text
            String query = "SELECT id, email FROM users WHERE username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                out.println(rs.getInt("id") + " : " + rs.getString("email"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            out.println("error: " + e.getMessage());
        }
    }
}
