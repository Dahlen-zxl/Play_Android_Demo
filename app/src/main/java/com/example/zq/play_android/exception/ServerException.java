package com.example.zq.play_android.exception;

public class ServerException extends Exception{
    private int mcode;

    public ServerException(int code, String message) {
        super(message);
        mcode = code;
    }

    public ServerException(int mcode, String message, Throwable throwable) {
        super(message, throwable);
        mcode = mcode;
    }

    public int getMcode() {
        return mcode;
    }
}
