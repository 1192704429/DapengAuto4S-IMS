package com.repair.manage.module.finance_order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * @author wuming
 * @description: 财务订单实体类
 */
@Data
@TableName("finance_order")
public class FinanceOrder implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 财务订单编号(gen:OR) */
    private String financeNo;
    /** 订单类型 */
    private Integer orderType;
    /** 关联维修单 */
    private Long repairOrderId;
    /** 关联采购单 */
    private Long purchaseOrderId;
    /** 金额 */
    private BigDecimal amount;
    /** 状态：0待审核 1已审核（收入项需财务审核后才真正完成） */
    private Integer status;
    /** 备注 */
    private String remark;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 维修工单编号(gen:OR) */
    @TableField(exist = false)
    private String orderNo;
    /** 采购订单编号(gen:OR) */
    @TableField(exist = false)
    private String purchaseNo;


}
