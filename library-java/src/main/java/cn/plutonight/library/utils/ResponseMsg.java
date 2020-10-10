package cn.plutonight.library.utils;

import lombok.Data;

@Data
public class ResponseMsg<T> {

    private int code;
    private String msg;
    private T data;

    public ResponseMsg(ResponseCode responseCode, String msg) {
        this.code = responseCode.code();
        this.msg = msg;
    }

    public ResponseMsg(ResponseCode responseCode, String msg, T data) {
        this.code = responseCode.code();
        this.msg = msg;
        this.data = data;
    }

}
