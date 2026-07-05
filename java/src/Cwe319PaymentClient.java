package com.example.app;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

/**
 * CWE-319 — Cleartext Transmission of Sensitive Information.
 * Tier-1: credentials posted over an explicit plaintext http:// scheme.
 */
public class Cwe319PaymentClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String sendCredentials(String username, String password) {
        // SINK: sensitive credentials transmitted over cleartext http://
        String url = "http://payments.internal.example.com/api/login";
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> body = Map.of("username", username, "password", password);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        return restTemplate.postForObject(url, request, String.class);
    }
}
