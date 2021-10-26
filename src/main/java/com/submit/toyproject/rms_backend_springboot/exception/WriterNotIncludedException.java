package com.submit.toyproject.rms_backend_springboot.exception;

import com.submit.toyproject.rms_backend_springboot.exception.handler.ErrorCode;
import com.submit.toyproject.rms_backend_springboot.exception.handler.RmsException;

public class WriterNotIncludedException extends RmsException {
    public WriterNotIncludedException() {
        super(ErrorCode.WRITER_IS_NOT_INCLUDED_IN_MEMBER_LIST);
    }
}
