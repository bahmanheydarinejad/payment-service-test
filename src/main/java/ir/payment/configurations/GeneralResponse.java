package ir.payment.configurations;

import lombok.Data;

import java.util.List;

@Data
public class GeneralResponse<PAYLOAD> {

    private PAYLOAD payload;
    private List<ResponseMessage> messages;

    public GeneralResponse(PAYLOAD payload, List<ResponseMessage> messages) {
        this.payload = payload;
        this.messages = messages;
    }

    public GeneralResponse(PAYLOAD payload) {
        this.payload = payload;
    }

    public GeneralResponse(List<ResponseMessage> messages) {
        this.messages = messages;
    }
}
