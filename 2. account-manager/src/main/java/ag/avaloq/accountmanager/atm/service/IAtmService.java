package ag.avaloq.accountmanager.atm.service;

import ag.avaloq.accountmanager.account.model.Account;
import ag.avaloq.accountmanager.atm.model.Atm;
import ag.avaloq.accountmanager.atm.model.AtmDTO;

import java.util.List;
import java.util.UUID;

public interface IAtmService {

    Atm getById(UUID id);

    Account withdraw(AtmDTO atmDTO) throws Exception;

    Account deposit(AtmDTO atmDTO) throws Exception;

    List<Atm> getAll();
}
