package cn.plutonight.library.utils;

public class ResponseGenerator {

    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_FAILURE_MESSAGE = "FAILURE";


    public static ResponseMsg getSuccessResponse() {
        return new ResponseMsg(ResponseCode.SUCCESS, DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> ResponseMsg getSuccessResponse(T data) {
        return new ResponseMsg(ResponseCode.SUCCESS, DEFAULT_SUCCESS_MESSAGE, data);
    }

    public static ResponseMsg getFailureResponse() {
        return new ResponseMsg(ResponseCode.FAIL, DEFAULT_FAILURE_MESSAGE);
    }

    public static <T> ResponseMsg getFailureResponse(T data) {
        return new ResponseMsg(ResponseCode.FAIL, DEFAULT_FAILURE_MESSAGE, data);
    }
}
