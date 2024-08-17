package ir.payment.apis.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletBalanceAddRequest {

    @NotNull(message = "user.wallet.amount.required")
    @Min(value = 10000, message = "user.wallet.amount.min")
    private BigDecimal amount;

}
