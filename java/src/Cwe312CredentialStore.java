package com.example.app;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

/**
 * CWE-312 — Cleartext Storage of Sensitive Information.
 * A plaintext password is written to disk and to logs with no hashing,
 * encryption, or masking.
 */
public class Cwe312CredentialStore {

    private static final Logger LOGGER = Logger.getLogger(Cwe312CredentialStore.class.getName());

    public void persist(String username, String password) throws Exception {
        // SINK 1 (FILE_WRITE): plaintext password persisted to a file.
        Files.write(Path.of("/var/data/credentials.txt"),
                (username + ":" + password).getBytes());

        // SINK 2 (LOG_WRITE): plaintext password written to the log.
        LOGGER.info("Stored credentials for " + username + " password=" + password);
    }
}
