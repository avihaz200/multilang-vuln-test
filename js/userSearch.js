const express = require('express');
const mysql = require('mysql');

const app = express();

const connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: 'root',
  database: 'appdb',
});

// Looks up a user by name. Vulnerable to SQL injection (CWE-89):
// the request query parameter flows unsanitized into a concatenated SQL query.
app.get('/users/search', (req, res) => {
  // SOURCE: attacker-controlled HTTP query parameter
  const username = req.query.username;

  // SINK: unsanitized input concatenated directly into the SQL text
  const query = "SELECT id, email FROM users WHERE username = '" + username + "'";

  connection.query(query, (err, results) => {
    if (err) {
      res.status(500).send('error: ' + err.message);
      return;
    }
    res.json(results);
  });
});

app.listen(3000, () => console.log('listening on 3000'));
