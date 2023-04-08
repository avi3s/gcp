package com.test.gcp.util;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Component @PropertySource(value = "classpath:error_code_mapping.json") @ConfigurationProperties @Data
public class ErrorCodeMapping {

    private List<ErrorCode> errorCodes;

    public static class ErrorCode {

        @JsonProperty("error_type")
        private ErrorCodes errorType;

        @JsonProperty("error_code")
        private String errorCode;

        @JsonProperty("error_message")
        private String errorMessage;

        public ErrorCodes getErrorType() {
            return errorType;
        }

        public void setErrorType(ErrorCodes errorType) {
            this.errorType = errorType;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
