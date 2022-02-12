package ag.avaloq.accountmanager.account.controller;

import ag.avaloq.accountmanager.account.model.Account;
import ag.avaloq.accountmanager.account.repository.IAccountRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private IAccountRepository accountRepository;

    @GetMapping("/{id}")
    public Account getById(@PathVariable("id") @NonNull UUID id) {
        log.info("Called AccountController::getById with id: {}", id);

        return accountRepository.getById(id);
    }

    @GetMapping(value = "")
    public List<Account> getAll() {
        log.info("Called AtmController::getAll");
        return accountRepository.findAll();
    }
}
