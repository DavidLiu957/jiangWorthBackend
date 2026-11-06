package com.jiangworth.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "fund_history")
public class FundHistoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// 標的名稱
	private String fundName;
	// 幣別 (USD/TWD)
	private String currency;
	// 歷史淨值
	private BigDecimal nav;
	// 交易日
	private LocalDate TradeDate;
	// 歷史配息率
	private BigDecimal dividendRate;
	// 風險等級 (RR3/RR4)
	private String riskLevel;

}
