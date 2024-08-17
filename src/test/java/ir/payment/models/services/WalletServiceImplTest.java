package ir.payment.models.services;

import ir.payment.exceptions.BusinessException;
import ir.payment.models.entities.UserWalletEntity;
import ir.payment.models.repositories.UserWalletRepository;
import ir.payment.models.repositories.UserWalletTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class WalletServiceImplTest {

    @Autowired
    private WalletServiceImpl walletService;

    @Autowired
    private UserWalletRepository userWalletRepository;

    @Autowired
    private UserWalletTransactionRepository userWalletTransactionRepository;

    @BeforeEach
    public void setup() {
        userWalletTransactionRepository.deleteAll();
        userWalletRepository.deleteAll();
        userWalletRepository.save(new UserWalletEntity().setUserId(1000).setBalance(BigDecimal.ZERO));
        userWalletRepository.save(new UserWalletEntity().setUserId(1001).setBalance(BigDecimal.valueOf(5000)));
        userWalletRepository.save(new UserWalletEntity().setUserId(1002).setBalance(BigDecimal.valueOf(7000)));
    }

    @Test
    public void testAddBalance() {
        Long transactionId = walletService.addBalance(1001, BigDecimal.valueOf(50));

        assertNotNull(transactionId);
        assertEquals(BigDecimal.valueOf(5050), userWalletRepository.findByUserId(1001).get().getBalance());
    }

    @Test
    public void testAddBalanceZeroAmount() {
        Long transactionId = walletService.addBalance(1001, BigDecimal.ZERO);

        assertNull(transactionId); // Assuming zero amount should not create a transaction
        assertEquals(Long.valueOf(0), userWalletTransactionRepository.count());
    }

    @Test
    public void testAddBalanceNullAmount() {
        Long transactionId = walletService.addBalance(1001, null);

        assertNull(transactionId); // Assuming null amount should not create a transaction
        assertEquals(Long.valueOf(0), userWalletTransactionRepository.count());
    }

    @Test
    public void testAddBalanceUserNotFound() {
        BusinessException thrown = assertThrows(BusinessException.class, () -> {
            walletService.addBalance(9999, BigDecimal.valueOf(100));
        });

        assertNotNull(thrown);
    }

    @Test
    public void testAddNegativeBalance() {
        Long transactionId = walletService.addBalance(1000, BigDecimal.valueOf(-25));

        assertNotNull(transactionId);
        assertEquals(BigDecimal.valueOf(-25), userWalletRepository.findByUserId(1000).get().getBalance());
    }

    @Test
    public void testGetBalance() {
        BigDecimal balance = walletService.getBalance(1001);

        assertEquals(BigDecimal.valueOf(5000), balance);
    }

    @Test
    public void testGetBalanceUserNotFound() {
        BusinessException thrown = assertThrows(BusinessException.class, () -> {
            walletService.getBalance(9999);
        });

        assertNotNull(thrown);
    }
}
