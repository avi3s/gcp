package com.test.gcp.util;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.test.gcp.payload.StudentDTO;
import com.test.gcp.payload.TeacherDTO;

@Component
public class CustomValidator implements Validator {

    private static final String PATTERN = "pattern";

    @Override
    public boolean supports(final Class<?> clazz) {
        return StudentDTO.class.isAssignableFrom(clazz) | TeacherDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        if (target instanceof StudentDTO) {
            StudentDTO studentDTO = (StudentDTO) target;
            validateStudentDTO(studentDTO, errors);
        } else if (target instanceof TeacherDTO) {
            TeacherDTO teacherDTO = (TeacherDTO) target;
            validateTeacherDTO(teacherDTO, errors);
        }
    }

    private void validateTeacherDTO(final TeacherDTO teacherDTO, final Errors errors) {

        if (Optional.ofNullable(teacherDTO).isPresent()) {
            if (StringUtils.isEmpty(teacherDTO.getTeacherName())) {
                errors.rejectValue("teacherName", PATTERN, ErrorCodes.VALIDATE_MISSING_TEACHER_NAME.name());
            }
        }
    }

    private void validateStudentDTO(final StudentDTO studentDTO, final Errors errors) {
        if (Optional.ofNullable(studentDTO).isPresent()) {
            if (StringUtils.isEmpty(studentDTO.getStudentName())) {
                errors.rejectValue("studentName", PATTERN, ErrorCodes.VALIDATE_MISSING_STUDENT_NAME.name());
            }
        }
    }
}
