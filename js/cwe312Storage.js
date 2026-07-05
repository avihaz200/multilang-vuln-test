const fs = require('fs');

// CWE-312 — Cleartext Storage of Sensitive Information.
function saveCredentials(username, password, apiKey) {
  // SINK 1 (FILE_WRITE): plaintext password persisted to a file.
  fs.writeFileSync('/var/data/creds.json', JSON.stringify({ username, password }));

  // SINK 2 (LOG_WRITE): secret written to the console/log in cleartext.
  console.log(`provisioned ${username} with apiKey=${apiKey}`);
}

module.exports = { saveCredentials };
