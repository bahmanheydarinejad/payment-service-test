package ir.payment.models.services;

import ir.payment.exceptions.BusinessException;
import ir.payment.models.entities.UserWalletEntity;
import ir.payment.models.mappers.WalletMapper;
import ir.payment.models.repositories.UserWalletRepository;
import ir.payment.models.repositories.UserWalletTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class WalletServiceImpl implements WalletService {

    private final UserWalletRepository userWalletRepository;
    private final UserWalletTransactionRepository userWalletTransactionRepository;
    private final WalletMapper walletMapper;

    @Override
    public Long addBalance(Integer userId, BigDecimal amount) {
        UserWalletEntity userWallet = findByUserId(userId);
        BigDecimal oldBalance = userWallet.getBalance();
        Optional.ofNullable(amount).ifPresent(a -> userWallet.setBalance(Optional.ofNullable(userWallet.getBalance()).orElse(BigDecimal.ZERO).add(a)));
        BigDecimal newBalance = userWallet.getBalance();
        if (Objects.nonNull(amount) && amount.compareTo(BigDecimal.ZERO) != 0) {
            UserWalletEntity updatedUserWallet = userWalletRepository.save(userWallet);
            return userWalletTransactionRepository.save(walletMapper.toCreateUserWalletTransaction(updatedUserWallet, oldBalance, newBalance, amount)).getId();
        }
        return null;
    }

    @Override
    public BigDecimal getBalance(Integer userId) {
        return findByUserId(userId).getBalance();
    }

    private UserWalletEntity findByUserId(Integer userId) {
        return userWalletRepository.findByUserId(userId).orElseThrow(() -> new BusinessException("user.wallet.not-found", userId));
    }
}
