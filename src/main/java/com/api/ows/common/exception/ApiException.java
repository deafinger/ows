package com.api.ows.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

/**
 * 표준화된 Exception 메세지 클래스
 * RFC7807 기준에 따르며, 추가로 statusCd를 HttpStatus에서 추출하여 반환
 */
public class ApiException {

    /** 에러코드 */
    private final String type;
    /** 에러의 간단한 설명 */
    private final String title;
    /** HttpStatus 객체 */
    private final HttpStatus httpStatus;
    /** HttpStatus의 코드 */
    private final int status;
    /** 에러의 상세 내용 */
    private final String detail;
    /** 호출 경로 */
    private final String instance;

    /**
     * 생성자
     * @param type 에러코드
     * @param title 에러의 간단한 설명
     * @param httpStatus HttpStatus 객체
     * @param detail HttpStatus의 코드
     * @param instance 호출 경로
     */
    public ApiException(String type, String title, HttpStatus httpStatus, String detail, String instance) {
        this.type = type;
        this.title = title;
        this.httpStatus = httpStatus;
        this.status = this.httpStatus.value();
        this.detail = detail;
        this.instance = instance;
    }

    /**
     * HttpStatus 객체 얻기
     * 얻어진 객체는 ResponseEntity로 전달
     * 실제 적용은 {@link ApiExceptionHandler#commonRuntimeException(RuntimeException, WebRequest)} 참고
     * @return HttpStatus 객체
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /** type 얻기 */
    public String getType() {
        return type;
    }

    /** title 얻기 */
    public String getTitle() {
        return title;
    }

    /** status 얻기 */
    public int getStatus() {
        return status;
    }

    /** detail 얻기 */
    public String getDetail() {
        return detail;
    }

    /** instance 얻기 */
    public String getInstance() {
        return instance;
    }
}
