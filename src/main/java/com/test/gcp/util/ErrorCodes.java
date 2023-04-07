package com.test.gcp.util;

import java.util.HashMap;
import java.util.Map;

public enum ErrorCodes {

    INVALID_INPUT("INVALID_INPUT"), VALIDATE_MISSING_STUDENT_NAME("VALIDATE_MISSING_STUDENT_NAME"), VALIDATE_MISSING_TEACHER_NAME("VALIDATE_MISSING_TEACHER_NAME");

    private static final Map<String, ErrorCodes> ERROR_MAP = new HashMap<>();

    static {
        for (ErrorCodes paymentErrorCode : values()) {
            ERROR_MAP.put(paymentErrorCode.getErrorCause(), paymentErrorCode);
        }
    }

    private final String errorCause;

    ErrorCodes(final String errorCause) {
        this.errorCause = errorCause;
    }

    public static ErrorCodes getEnum(final String val) {
        for (ErrorCodes code : ErrorCodes.values()) {
            if (code.errorCause.equalsIgnoreCase(val)) {
                return code;
            }
        }
        return null;
    }

    public static ErrorCodes getByErrorCause(final String errorCause) {
        return ERROR_MAP.get(errorCause);
    }

    public String getErrorCause() {
        return errorCause;
    }
}
