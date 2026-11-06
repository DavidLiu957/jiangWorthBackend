package com.jiangworth.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "fund_target") // 購買的標的明細
public class FundTargetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 多對一 關聯到基金字典表
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fund_history_id", nullable = false)
	private FundHistoryEntity fundHistory;

	// 配置比例 (%)
	private BigDecimal allocationRatio;
	// 投入金額 (如果是實際保單就填寫，回測報告若用不到可留空 null)
	private BigDecimal investAmount;
	// 持有單位數 (實際保單用，回測報告留空 null)
	private BigDecimal holdingUnits;
	// 投入日期
	private LocalDate initialInvestDate;

}
