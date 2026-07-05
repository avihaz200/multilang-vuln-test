import sqlite3

from flask import Flask, request, jsonify

app = Flask(__name__)


@app.route("/users/search")
def search_users():
    """Looks up a user by name. Vulnerable to SQL injection (CWE-89):
    the request argument flows unsanitized into a concatenated SQL query."""
    # SOURCE: attacker-controlled HTTP query parameter
    username = request.args.get("username")

    conn = sqlite3.connect("app.db")
    cursor = conn.cursor()

    # SINK: unsanitized input concatenated directly into the SQL text
    query = "SELECT id, email FROM users WHERE username = '" + username + "'"
    cursor.execute(query)

    rows = cursor.fetchall()
    conn.close()
    return jsonify(rows)


if __name__ == "__main__":
    app.run(port=5000)

# rescan trigger: no-op comment (semantically neutral) to force CWE-319 re-evaluation
