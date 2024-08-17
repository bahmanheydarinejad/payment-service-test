package ir.payment.models.repositories;

import ir.payment.models.entities.UserWalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserWalletRepository extends JpaRepository<UserWalletEntity, Integer> {

    Optional<UserWalletEntity> findByUserId(Integer userId);

}
