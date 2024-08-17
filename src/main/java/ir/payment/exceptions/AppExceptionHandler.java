package ir.payment.exceptions;

import ir.payment.configurations.GeneralResponse;
import ir.payment.configurations.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class AppExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<GeneralResponse<?>> handleBusinessException(BusinessException e) {
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(
                new GeneralResponse<>(e.getMessageExceptions().stream()
                        .map(m -> new ResponseMessage(messageSource.getMessage(m.getMessageId(), m.getArgs(), LocaleContextHolder.getLocale())))
                        .collect(Collectors.toList()))
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<GeneralResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(
                new GeneralResponse<>(e.getBindingResult().getAllErrors()
                        .stream()
                        .map(objectError -> new ResponseMessage(objectError.getDefaultMessage()))
                        .collect(Collectors.toList()))
        );
    }

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<GeneralResponse<?>> handleAnyError(Throwable t) {
        log.error("Unknown Error: ", t);
        try {
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON)
                    .body(new GeneralResponse<>(List.of(new ResponseMessage(messageSource.getMessage("error.unknown", null, LocaleContextHolder.getLocale())))));
        } catch (Throwable th) {
            log.error("Unknown Error: ", th);
            return ResponseEntity.internalServerError().contentType(MediaType.APPLICATION_JSON).body(new GeneralResponse<>(List.of(new ResponseMessage("Unknown Error!"))));
        }
    }
}
