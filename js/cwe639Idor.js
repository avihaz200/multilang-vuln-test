const express = require('express');

const app = express();

// naive in-memory data access — no ownership checks anywhere.
const invoices = {};
function getInvoiceById(id) {
  return invoices[id];
}

// CWE-639 — Authorization Bypass Through User-Controlled Key (IDOR).
// The user-controlled invoice id reaches a by-id lookup with no comparison
// to the authenticated session user.
app.get('/invoices/:invoiceId', (req, res) => {
  // SOURCE: user-controlled path parameter.
  const invoiceId = req.params.invoiceId;
  // SINK: generic by-id lookup, no ownership/authorization check.
  const invoice = getInvoiceById(invoiceId);
  res.json(invoice);
});

module.exports = app;
