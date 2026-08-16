const express = require('express');
const mysql = require('mysql');
const app = express();
const connection = mysql.createConnection({ host: 'localhost', user: 'root', password: 'root', database: 'appdb' });

// Looks up a user by name. Vulnerable to SQL injection (CWE-89):
app.get('/login', (req, res) => {
  const username = req.query.username;          // SOURCE: attacker-controlled HTTP query parameter
  const query = "SELECT id, email FROM users WHERE username = '" + username + "'";  // SINK
  connection.query(query, (err, results) => {
    if (err) { res.status(500).send('error: ' + err.message); return; }
    res.json(results);
  });
});
app.listen(3001, () => console.log('listening on 3001'));
