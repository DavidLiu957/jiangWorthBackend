package com.jiangworth.dto.req;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReq {

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

}