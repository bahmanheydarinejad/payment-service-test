package ir.payment.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {

    private final List<MessageException> messageExceptions;

    public BusinessException(MessageException... messageExceptions) {
        this.messageExceptions = Arrays.asList(messageExceptions);
    }

    public BusinessException(String messageId, Object... args) {
        this(new MessageException(messageId, args));
    }
}
