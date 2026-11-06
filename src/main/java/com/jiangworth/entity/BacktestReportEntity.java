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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "backtest_report") // 理財試算報告模組
public class BacktestReportEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private CustomerEntity customer;

	//TODO 看要開4個TABLE 還是共用同個TABLE
	// 投資方案種類 (1:配置回測 2:定時定額 3:無本煉金 4:資產減壓)
	private String strategyType;
	
	// 預計投資總金額
	private BigDecimal totalInvestment;
	
	//定時定額
	//BigDecimal monthly_amount "每月定額 (NT$)"
	//Integer investment_years "投資年限 (5/10/20年)"
	//Boolean has_life_insurance_fee "是否搭配壽險費用方案"
	
	//無本煉金
	//BigDecimal loan_amount "資金總額 (貸款金額)"
	//BigDecimal interest_rate "年利率 (%)"
	//Integer loan_years "年期"
	//BigDecimal monthly_expense "每月支出 (本息攤還)" 前端計算的話就直接往後帶 後端計算的話可以不用開此欄位
	
	//資產減壓
	//BigDecimal original_monthly_expense "原本每月支出 (NT$)"
	//BigDecimal current_loan_amount "原貸款金額"
	//Integer current_loan_years "原年期"
	//BigDecimal current_interest_rate "原利率 (%)"
	//BigDecimal new_principal_amount "新本利攤金額"
	//BigDecimal new_interest_rate "新本利利率 (%)"
	//Integer new_loan_years "新本利年期"
	//BigDecimal finance_loan_amount "理財型金額"
	//BigDecimal finance_interest_rate "理財型利率 (%)"

	// 建立時間
	@CreatedDate
	@Column(updatable = false, nullable = false)
	private LocalDateTime createdAt;
	// 最後更新時間
	@LastModifiedDate
	private LocalDateTime updatedAt;

	// 單向一對多：指向同一個通用細項表，JPA 會自動在明細表建立 backtest_report_id 外鍵
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "backtest_report_id")
	private List<FundTargetEntity> fundTargets = new ArrayList<>();

}
