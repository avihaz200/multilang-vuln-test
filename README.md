# multilang-vuln-test

Deliberately vulnerable test project for the Agentic-SAST scanner.

Each file contains the **same** vulnerability class — SQL injection (CWE-89) —
where an attacker-controlled HTTP parameter flows unsanitized into a
string-concatenated SQL query:

| Language   | File                                    | Source                | Sink                  |
|------------|-----------------------------------------|-----------------------|-----------------------|
| Java       | `java/src/UserSearchServlet.java`       | `request.getParameter`| `stmt.executeQuery`   |
| JavaScript | `js/userSearch.js`                      | `req.query.username`  | `connection.query`    |
| Python     | `python/user_search.py`                 | `request.args.get`    | `cursor.execute`      |
| PHP        | `php/user_search.php`                   | `$_GET["username"]`   | `mysqli_query`        |

This is a scanner test harness, not real code. Do not deploy.
