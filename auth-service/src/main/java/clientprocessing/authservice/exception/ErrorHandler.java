package clientprocessing.authservice.exception;

import clientprocessing.authservice.model.ClientResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ErrorHandler {

    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            MethodArgumentNotValidException.class,
            BindException.class,
    })
    public ResponseEntity<ClientResponseDto> handleMethodArgumentException(Exception e) {
        log.error(getStackTrace(e));
        ClientResponseDto response = getCommonErrorResponse(e);
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(InvalidSidException.class)
    public ResponseEntity<ClientResponseDto> handleInvalidHeaderException(InvalidSidException e) {
        log.error(getStackTrace(e));
        ClientResponseDto response = ClientResponseDto.builder()
                .timestamp(LocalDateTime.now(ZoneId.systemDefault()))
                .errorMessage(e.getMessage())
                .build();
        return ResponseEntity.status(UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ClientResponseDto> handleException(Exception e) {
        log.error(getStackTrace(e));
        ClientResponseDto response = getCommonErrorResponse(e);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(response);
    }

    private ClientResponseDto getCommonErrorResponse(Exception e) {
        log.error(getStackTrace(e));
        return ClientResponseDto.builder()
                .errorMessage(e.getMessage())
                .timestamp(LocalDateTime.now())
                .errorCode("-1").build();
    }

    private String getStackTrace(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}

