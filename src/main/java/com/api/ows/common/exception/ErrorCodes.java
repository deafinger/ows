package com.api.ows.common.exception;

/**
 * 에러 코드 모음 클래스
 * 에러에 대한 코드값과 설명문을 쌍으로 작성
 */
public final class ErrorCodes {

    public static final String ERR_GENERAL_CD           = "error-0001";
    public static final String ERR_GENERAL_MSG          = "알 수 없는 에러가 발생하였습니다.";

    // TODO : 에러코드와 메시지를 작성하세요.
    public static final String ERR_DATA_NOT_FOUND_CD    = "error-0002";
    public static final String ERR_DATA_NOT_FOUND_MSG   = "데이터가 존재하지 않거나, 받아올 수 없는 상태입니다.";

 // TODO : 에러코드와 메시지를 작성하세요.
    public static final String ERR_BAD_PARAMETER_CD    = "error-0003";
    public static final String ERR_BAD_PARAMETER_MSG   = "요청하신 Parameter가 형식에 맞지 않습니다.";
}
