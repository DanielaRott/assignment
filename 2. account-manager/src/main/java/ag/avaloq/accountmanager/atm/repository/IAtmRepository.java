package ag.avaloq.accountmanager.atm.repository;

import ag.avaloq.accountmanager.atm.model.Atm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAtmRepository extends JpaRepository<Atm, UUID> {
}
