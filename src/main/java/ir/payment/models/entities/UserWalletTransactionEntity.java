package ir.payment.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "USER_WALLET_TRANSACTION")
public class UserWalletTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserWalletEntity wallet;
    private BigDecimal amount;
    private BigDecimal oldBalance;
    private BigDecimal newBalance;
    private Timestamp createTimestamp;

}
