package com.test.gcp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;

@Data @EqualsAndHashCode(callSuper = false) @JsonInclude(Include.NON_NULL) @Generated
public class StudentDTO {

    private String studentId;

    private String studentName;

    private String address;

    private String section;

}
