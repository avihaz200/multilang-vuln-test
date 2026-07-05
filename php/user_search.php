<?php
/**
 * Looks up a user by name. Vulnerable to SQL injection (CWE-89):
 * the request parameter flows unsanitized into a concatenated SQL query.
 */

$conn = mysqli_connect("localhost", "root", "root", "appdb");
if (!$conn) {
    die("connection failed: " . mysqli_connect_error());
}

// SOURCE: attacker-controlled HTTP GET parameter
$username = $_GET["username"];

// SINK: unsanitized input concatenated directly into the SQL text
$query = "SELECT id, email FROM users WHERE username = '" . $username . "'";
$result = mysqli_query($conn, $query);

while ($row = mysqli_fetch_assoc($result)) {
    echo $row["id"] . " : " . $row["email"] . "\n";
}

mysqli_close($conn);
