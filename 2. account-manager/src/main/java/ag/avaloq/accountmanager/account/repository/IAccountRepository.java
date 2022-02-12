package ag.avaloq.accountmanager.account.repository;

import ag.avaloq.accountmanager.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAccountRepository extends JpaRepository<Account, UUID> {
}
