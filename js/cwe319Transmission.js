const axios = require('axios');
const https = require('https');

// CWE-319 — Cleartext Transmission of Sensitive Information.

async function login(username, password) {
  // SINK: credentials posted over an explicit plaintext http:// scheme.
  const res = await axios.post('http://auth.internal.example.com/login', {
    username,
    password,
  });
  return res.data;
}

async function fetchSecret(token) {
  // SINK: TLS certificate verification disabled while sending a bearer token.
  const agent = new https.Agent({ rejectUnauthorized: false });
  const res = await axios.get('https://api.example.com/secret', {
    httpsAgent: agent,
    headers: { Authorization: `Bearer ${token}` },
  });
  return res.data;
}

module.exports = { login, fetchSecret };
