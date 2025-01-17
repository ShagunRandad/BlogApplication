package com.blog.api.mapper;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
	private int id;
	@NotBlank
	private String name;
	@Email
    private String email;
	@NotBlank
	private String password;
	@NotBlank
	private String about;
}
