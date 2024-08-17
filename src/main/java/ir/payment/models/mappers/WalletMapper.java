package ir.payment.models.mappers;

import ir.payment.models.entities.UserWalletEntity;
import ir.payment.models.entities.UserWalletTransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Mapper(componentModel = "spring", imports = {Timestamp.class})
public interface WalletMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "wallet", source = "userWallet")
    @Mapping(target = "createTimestamp", expression = "java(new Timestamp(System.currentTimeMillis()))")
    UserWalletTransactionEntity toCreateUserWalletTransaction(UserWalletEntity userWallet, BigDecimal oldBalance, BigDecimal newBalance, BigDecimal amount);

}
