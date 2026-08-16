const express = require("express");
const mysql = require("mysql");
const app = express();
const connection = mysql.createConnection({ host: "localhost", user: "root", password: "root", database: "appdb" });
app.get("/lookup", (req, res) => {
  const username = req.query.username;                                            // SOURCE
  const query = "SELECT id, email FROM users WHERE username = \x27" + username + "\x27";  // SINK CWE-89
  connection.query(query, (err, results) => { if (err) { res.status(500).send("error: " + err.message); return; } res.json(results); });
});
app.listen(3002);
