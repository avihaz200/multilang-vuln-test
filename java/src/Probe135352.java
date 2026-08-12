package com.example.app;
import java.sql.*;
public class Probe135352 {
    public String q(String p) throws Exception {
        Statement s = DriverManager.getConnection("jdbc:h2:mem:x").createStatement();
        ResultSet r = s.executeQuery("SELECT a FROM t WHERE b = '" + p + "'");
        return r.next() ? r.getString("a") : "x";
    }
}
