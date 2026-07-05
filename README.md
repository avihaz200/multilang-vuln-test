# multilang-vuln-test

Deliberately vulnerable test project for the Agentic-SAST scanner. Not real
code — do not deploy.

## Agentic-engine CWE coverage (the point of this repo)

The agentic engine supports exactly three CWEs (per `cwe_registry.py`):
**CWE-319**, **CWE-639**, **CWE-312**. Each is planted as a high-confidence
(Tier-1) example in the three catalogued languages — Java, JavaScript, Python —
so we can confirm every supported CWE is detected in every language.

| CWE | Vulnerability | Java | JavaScript | Python |
|---|---|---|---|---|
| **CWE-319** | Cleartext Transmission | `java/src/Cwe319PaymentClient.java` (creds over `http://`) | `js/cwe319Transmission.js` (`http://` + `rejectUnauthorized:false`) | `python/cwe319_transmission.py` (`http://` + `verify=False`) |
| **CWE-639** | IDOR / Authz Bypass | `java/src/Cwe639AccountController.java` (`@PathVariable`→`findById`, no `@PreAuthorize`) | `js/cwe639Idor.js` (`req.params.id`→by-id lookup, no authz) | `python/cwe639_idor.py` (`<order_id>`→`filter_by(id=...)`, no ownership) |
| **CWE-312** | Cleartext Storage | `java/src/Cwe312CredentialStore.java` (`Files.write` + `logger.info` of password) | `js/cwe312Storage.js` (`fs.writeFileSync` + `console.log` of secret) | `python/cwe312_storage.py` (`open().write` + `logger.info` of secret) |

Expected: **9 agentic findings** (3 CWEs × 3 languages), some files carrying two
sinks (e.g. CWE-312 file-write + log-write).

## Classic-engine baseline (not agentic)

The original SQL-injection files (CWE-89) remain as a control. CWE-89 is **not**
an agentic-supported CWE — those findings come from the classic AST engine, and
PHP from the legacy Perl engine.

| Language | File | Source → Sink |
|---|---|---|
| Java | `java/src/UserSearchServlet.java` | `request.getParameter` → `executeQuery` |
| JavaScript | `js/userSearch.js` | `req.query.username` → `connection.query` |
| Python | `python/user_search.py` | `request.args.get` → `cursor.execute` |
| PHP | `php/user_search.php` | `$_GET["username"]` → `mysqli_query` |
