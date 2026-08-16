const express = require("express");
const mysql = require("mysql");
const app = express();
const c = mysql.createConnection({ host: "localhost", user: "root", password: "root", database: "appdb" });
app.get("/q", (req, res) => {
  const u = req.query.username;                                       // SOURCE
  const query = "SELECT id,email FROM users WHERE username = '" + u + "'";  // SINK CWE-89
  c.query(query, (e, r) => { if (e) { res.status(500).send("err: " + e.message); return; } res.json(r); });
});
app.listen(3003);
