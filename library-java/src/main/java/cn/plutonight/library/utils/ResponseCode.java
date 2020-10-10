package cn.plutonight.library.utils;

public enum  ResponseCode {
    SUCCESS(200),       // 成功
    FAIL(400),          // 失败
    UNAUTHORIZED(401),  // 认证错误
    NOT_FOUND(404),     // 接口不存在
    SERVER_ERROR(500);  // 服务器内部错误

    private final int code;

    ResponseCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
