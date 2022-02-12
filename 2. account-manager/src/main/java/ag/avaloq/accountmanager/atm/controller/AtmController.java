package ag.avaloq.accountmanager.atm.controller;

import ag.avaloq.accountmanager.account.model.Account;
import ag.avaloq.accountmanager.atm.exception.InsufficientFoundsException;
import ag.avaloq.accountmanager.atm.exception.NotImplementedException;
import ag.avaloq.accountmanager.atm.model.Atm;
import ag.avaloq.accountmanager.atm.model.AtmDTO;
import ag.avaloq.accountmanager.atm.service.IAtmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/atm")
public class AtmController {

    @Autowired
    private IAtmService atmService;

    @GetMapping(value = "/{id}")
    public Atm getById(@PathVariable("id") @NotBlank UUID id) {
        log.info("Called AtmController::getAtm with id: {}", id);
        return atmService.getById(id);
    }

    @GetMapping(value = "")
    public List<Atm> getAll() {
        log.info("Called AtmController::getAll");
        return atmService.getAll();
    }

    @PostMapping(value = "/withdraw", consumes = {"application/json"})
    public Account withdraw(@RequestBody @Valid AtmDTO atmDTO) {
        log.info("Called AtmController::withdraw with atmDTO: {}", atmDTO);
        try {
            return atmService.withdraw(atmDTO);
        } catch (IllegalArgumentException | InsufficientFoundsException e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PostMapping(value = "/deposit", consumes = {"application/json"})
    public Account deposit(@RequestBody @Valid AtmDTO atmDTO) {
        log.info("Called AtmController::deposit with atmDTO: {}", atmDTO);
        try {
            return atmService.deposit(atmDTO);
        } catch (NotImplementedException e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
