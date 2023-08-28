package BatDongSan.example.BatDongSan.config;

import BatDongSan.example.BatDongSan.dto.ErrorDto;
import BatDongSan.example.BatDongSan.exception.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = {AppException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(AppException appException){
        return ResponseEntity
                .status(appException.getHttpStatus())
                .body(new ErrorDto(appException.getMessage()));
    }

}
