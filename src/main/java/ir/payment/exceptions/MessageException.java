package ir.payment.exceptions;

import lombok.Getter;

@Getter
public class MessageException {

    private final String messageId;
    private final Object[] args;

    public MessageException(String messageId, Object... args) {
        this.messageId = messageId;
        this.args = args;
    }
}
