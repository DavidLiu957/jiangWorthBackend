package com.jiangworth.dto.res;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRes {

	@NotEmpty
	private Long customerId;

	private String customerName;
	// 生日
	private LocalDate birthday;
	// 性別
	private String sex;
	// 電話
	private String phone;
	// 地址
	private String address;
	// 風險屬性 (如 RR5)
	private String riskProfile;
	// 備註
	private String note;
	// 是否封存
	private Boolean isArchived;
	// 建立時間
	private LocalDateTime createdAt;

}