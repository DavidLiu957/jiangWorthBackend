package com.jiangworth.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "customers")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@CreatedDate
	@Column(updatable = false, nullable = false)
	private LocalDateTime createdAt;
	// 最後更新時間
	@LastModifiedDate
	private LocalDateTime updatedAt;

}
