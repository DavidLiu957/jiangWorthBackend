package com.jiangworth.dto.res;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRes {
	
	@NotEmpty
	private Long userId;
	
	private String username;
	
	private String email;
}