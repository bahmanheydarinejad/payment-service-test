package ir.payment.models.repositories;

import ir.payment.models.entities.UserWalletTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWalletTransactionRepository extends JpaRepository<UserWalletTransactionEntity, Long> {
}
