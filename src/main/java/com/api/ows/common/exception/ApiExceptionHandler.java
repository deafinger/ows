package com.api.ows.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception 전역 처리 핸들러
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 알 수 없는 RuntimeException 처리 핸들러
     * RuntimeException 발생 시, 해당 핸들러가 일괄적으로 처리 함
     *
     * 에러별 코드와 설명은 {@link ErrorCodes}를 참조
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> commonRuntimeException(final RuntimeException ex, final WebRequest request) {

        String instance = ((ServletWebRequest) request).getRequest().getRequestURI();
        ApiException apiException = new ApiException(ErrorCodes.ERR_GENERAL_CD, ErrorCodes.ERR_GENERAL_MSG, HttpStatus.BAD_REQUEST, ex.getMessage(), instance);

        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

    /**
     * 데이터가 존재하지 않는 경우 Exception 처리 핸들러
     * Repository에서 데이터 조회 후, 데이터가 존재하지 않는 경우 orElseThrow를 활용해서 처리 함
     *
     * 에러별 코드와 설명은 {@link ErrorCodes}를 참조
     */
    @ExceptionHandler({DataNotFoundException.class})
    public ResponseEntity<Object> dataNotFoundException(final RuntimeException ex, final WebRequest request) {

        String instance = ((ServletWebRequest) request).getRequest().getRequestURI();
        ApiException apiException = new ApiException(ErrorCodes.ERR_DATA_NOT_FOUND_CD, ErrorCodes.ERR_DATA_NOT_FOUND_MSG, HttpStatus.NOT_FOUND, ex.getMessage(), instance);

        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }
}
