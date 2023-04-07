package com.test.gcp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;

@Data @EqualsAndHashCode(callSuper = false) @JsonInclude(Include.NON_NULL) @Generated
public class TeacherDTO {

    private String teacherId;

    private String phoneNumber;

    private String teacherName;

    private String email;

    private String address;

}
