package ir.payment.apis.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class WalletBalanceResponse {

    private final BigDecimal balance;

}
