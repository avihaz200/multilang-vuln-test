package com.example.app;

import java.sql.*;

// Reasoning-cache probe: a unique CWE-89 sink (user input -> concatenated SQL on a
// raw Statement) so sink_type_reasoning must LLM-reason it -> guaranteed org-DB MISS.
public class RcCacheProbe132738 {
    public String lookupWidget(String rcProbeParam) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:rcprobe");
        Statement stmt = conn.createStatement();
        String q = "SELECT sku FROM widgets WHERE label = '" + rcProbeParam + "' AND active = 1";
        ResultSet rs = stmt.executeQuery(q);
        return rs.next() ? rs.getString("sku") : "none";
    }
}
