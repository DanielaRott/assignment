package ag.avaloq.accountmanager.user.repository;

import ag.avaloq.accountmanager.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {
}
