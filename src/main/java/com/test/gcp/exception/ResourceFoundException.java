package com.test.gcp.exception;

public class ResourceFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 3761020668457610858L;
	
    private String fieldName;
    private String fieldValue;

    public ResourceFoundException(String fieldName, String fieldValue) {
        super(String.format("%s already found with : '%s'", fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}