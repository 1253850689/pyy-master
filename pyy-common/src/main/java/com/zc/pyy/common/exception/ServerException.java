package com.zc.pyy.common.exception;

import com.zc.pyy.common.api.IErrorCode;

public class ServerException extends RuntimeException {

    private IErrorCode iErrorCode;

    public ServerException(IErrorCode iErrorCode){
        super(iErrorCode.getMessage());
        this.iErrorCode = iErrorCode;
    }

    public IErrorCode getiErrorCode() {
        return iErrorCode;
    }

    public void setiErrorCode(IErrorCode iErrorCode) {
        this.iErrorCode = iErrorCode;
    }
}
