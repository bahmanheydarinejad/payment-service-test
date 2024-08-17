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
@Table(name = "USER_WALLET")
public class UserWalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private BigDecimal balance;
    private Timestamp updateTimestamp;
    private Timestamp createTimestamp;

}
