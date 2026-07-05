package com.example.app;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * CWE-639 — Authorization Bypass Through User-Controlled Key (IDOR).
 * A user-controlled account id reaches a generic by-id lookup with NO
 * @PreAuthorize on the method/class and NO comparison to the caller's
 * identity anywhere in the flow.
 */
@RestController
public class Cwe639AccountController {

    private final AccountRepository accountRepository;

    public Cwe639AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // SOURCE: user-controlled path variable; no authorization annotation.
    @GetMapping("/accounts/{accountId}")
    public Account getAccount(@PathVariable String accountId) {
        // SINK: generic by-id lookup, no ownership/principal check.
        return accountRepository.findById(accountId).orElse(null);
    }
}

interface AccountRepository extends CrudRepository<Account, String> {
}

class Account {
    public String id;
    public String ownerId;
    public double balance;
}
