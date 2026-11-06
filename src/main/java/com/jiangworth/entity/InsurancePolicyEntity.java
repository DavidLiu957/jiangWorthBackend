package com.jiangworth.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "insurance_policy")
public class InsurancePolicyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long policyId;

	// 多對一：多張保單屬於同一個客戶
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private CustomerEntity customer;

	// 多對一：很多張保單，可以對應到同一個「保險商品規格」
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "insurance_product_id", nullable = false)
	private InsuranceProductEntity insuranceProduct;

	// 投入保費/總金額
	private BigDecimal totalPremium;
	// 保單始期
	private LocalDate startDate;
	// 保單號碼
	private String policyNumber;
	// 建立時間
	@CreatedDate
	@Column(updatable = false, nullable = false)
	private LocalDateTime createdAt;
	// 最後更新時間
	@LastModifiedDate
	private LocalDateTime updatedAt;

	// 單向一對多：JPA 會自動在明細表建立 policy_id 外鍵來管理關聯
	// cascade = ALL 代表儲存/刪除保單時，底下的基金配置細項也會連動一起儲存/刪除
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "policy_id")
	private List<FundTargetEntity> fundTargets = new ArrayList<>();

}
