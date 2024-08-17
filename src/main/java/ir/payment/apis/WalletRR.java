package ir.payment.apis;

import ir.payment.apis.models.WalletAddAmountResponse;
import ir.payment.apis.models.WalletBalanceAddRequest;
import ir.payment.apis.models.WalletBalanceResponse;
import ir.payment.configurations.GeneralResponse;
import ir.payment.models.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WalletRR {

    private final WalletService walletService;

    @GetMapping(value = "/users/{userId}/wallet/balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse<WalletBalanceResponse> getWalletBalance(@PathVariable Integer userId) {
        return new GeneralResponse<WalletBalanceResponse>(new WalletBalanceResponse(walletService.getBalance(userId)));
    }

    @PutMapping(value = "/users/{userId}/wallet/balance/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse<WalletAddAmountResponse> getWalletBalance(@PathVariable Integer userId, @Validated @RequestBody WalletBalanceAddRequest request) {
        return new GeneralResponse<WalletAddAmountResponse>(new WalletAddAmountResponse(walletService.addBalance(userId, request.getAmount())));
    }

}
