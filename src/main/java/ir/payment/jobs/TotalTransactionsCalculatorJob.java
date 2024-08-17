package ir.payment.jobs;

import ir.payment.models.repositories.UserWalletTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TotalTransactionsCalculatorJob {

    private final UserWalletTransactionRepository userWalletTransactionRepository;

    /**
     * <minute> <hour> <day-of-month> <month> <day-of-week> <command>
     */
    @Scheduled(cron = "0 0 23 * * ?")
    public void calculate() {
        long count = userWalletTransactionRepository.findAll().size();
        log.info("Total Transactions: {}", count);
    }

}
