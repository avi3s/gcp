package com.test.gcp.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 3761020668457610857L;
	
    private String fieldName;
    private String fieldValue;

    public ResourceNotFoundException(String fieldName, String fieldValue) {
        super(String.format("%s not found with : '%s'", fieldName, fieldValue)); // Post not found with id : 1
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}