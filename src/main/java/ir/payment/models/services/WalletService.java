package ir.payment.models.services;

import java.math.BigDecimal;

public interface WalletService {

    Long addBalance(Integer userId, BigDecimal amount);

    BigDecimal getBalance(Integer userId);
}
