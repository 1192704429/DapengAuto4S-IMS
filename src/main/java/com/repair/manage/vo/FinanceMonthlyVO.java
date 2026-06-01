package com.repair.manage.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 财务月度统计 VO
 */
@Data
public class FinanceMonthlyVO {

    /** 月份 yyyy-MM */
    private String month;
    /** 收入合计 */
    private BigDecimal income;
    /** 支出合计 */
    private BigDecimal expense;
}
