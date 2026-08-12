package com.example.app;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.net.*;
import java.io.*;

// CWE-319 (cleartext transmission) — the engine's flagship CWE. A user-supplied
// token is sent to an http:// (non-TLS) endpoint and echoed back in the response.
@RestController
@RequestMapping("/rcprobe")
public class Cwe319RcProbe141859 {

    @GetMapping("/sync")
    public ResponseEntity<String> sync(@RequestParam String authToken) throws Exception {
        URL url = new URL("http://internal.example.com/ingest?token=" + authToken);
        HttpURLConnection c = (HttpURLConnection) url.openConnection();
        c.setRequestMethod("GET");
        c.getInputStream().close();
        return ResponseEntity.ok("sent token in cleartext: " + authToken);
    }
}
