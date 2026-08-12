package com.example.app;

import java.sql.*;

// Reasoning-cache probe (retry): unique CWE-89 sink -> guaranteed org-DB MISS.
public class RcCacheProbe133111 {
    public String find(String rcParam) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:rcprobe2");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id FROM items WHERE tag = '" + rcParam + "'");
        return rs.next() ? rs.getString("id") : "none";
    }
}
