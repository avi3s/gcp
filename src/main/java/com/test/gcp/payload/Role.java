package com.test.gcp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.test.gcp.util.RoleType;
import com.test.gcp.util.ValueOfEnum;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public class Role {

	@NotBlank(message = "{role.name.null.message}")
	@ValueOfEnum(enumClass = RoleType.class, message = "{role.name.format.message}")
	private String name;
}