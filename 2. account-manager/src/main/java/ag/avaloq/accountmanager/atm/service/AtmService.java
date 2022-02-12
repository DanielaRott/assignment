package ag.avaloq.accountmanager.atm.service;

import ag.avaloq.accountmanager.account.model.Account;
import ag.avaloq.accountmanager.account.repository.IAccountRepository;
import ag.avaloq.accountmanager.atm.exception.AmountNotAvailableException;
import ag.avaloq.accountmanager.dispenser.ATMDispenseChain;
import ag.avaloq.accountmanager.atm.exception.InsufficientFoundsException;
import ag.avaloq.accountmanager.atm.exception.NotImplementedException;
import ag.avaloq.accountmanager.atm.model.Atm;
import ag.avaloq.accountmanager.atm.model.AtmDTO;
import ag.avaloq.accountmanager.atm.repository.IAtmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Transactional
@Service
public class AtmService implements IAtmService {

    @Autowired
    private IAtmRepository atmRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public Atm getById(UUID id) {
        return atmRepository.getById(id);
    }

    @Override
    public Account withdraw(AtmDTO atmDTO) throws Exception {
        log.info("Called AtmService::withdraw with atmDTO: {}", atmDTO);

        if (atmDTO.getAmount().remainder(BigDecimal.TEN).compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalArgumentException("The requested amount should be a multiple of 10!");
        }

        var atm = atmRepository.findById(atmDTO.getAtmId()).get();

        if(atm.getBalance().compareTo(atmDTO.getAmount()) < 0) {
            log.error("Your requested amount exceeds the available founds on this machine!");
            throw new AmountNotAvailableException("Your requested amount exceeds the available founds on this machine!");
        }

        return withdrawAmount(atm, atmDTO);
    }

    private Account withdrawAmount(Atm atm, AtmDTO atmDTO) throws Exception {
        return Optional.ofNullable(accountRepository.findById(atmDTO.getAccountId()).get())
                .map(account -> {
                    processInsufficientFounds(atmDTO.getAmount(), account.getBalance());

                    new ATMDispenseChain().initDispenseChain.dispense(atm, atmDTO.getAmount());

                    atm.setBalance(atm.getBalance().subtract(atmDTO.getAmount()));
                    atmRepository.save(atm);

                    account.setBalance(account.getBalance().subtract(atmDTO.getAmount()));
                    return accountRepository.save(account);
                })
                .orElseThrow(IllegalArgumentException::new);
    }

    private void processInsufficientFounds(BigDecimal atmAmount, BigDecimal accountBalance) {
        if(accountBalance.compareTo(atmAmount) < 0) {
            try {
                throw new InsufficientFoundsException("Insufficient founds! Please check your account!");
            } catch (InsufficientFoundsException e) {
                log.error("Insufficient founds! Please check your account!", e);
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    @Override
    public Account deposit(AtmDTO atmDTO) throws NotImplementedException {
        log.error("This functionality has not been implemented yet!");
        throw new NotImplementedException("This functionality has not been implemented yet!");
    }

    @Override
    public List<Atm> getAll() {
        return atmRepository.findAll();
    }
}
