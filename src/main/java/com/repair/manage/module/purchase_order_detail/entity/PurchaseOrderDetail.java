package com.repair.manage.module.purchase_order_detail.entity;

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
 * @description: 采购订单详情实体类
 */
@Data
@TableName("purchase_order_detail")
public class PurchaseOrderDetail implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 关联采购单 */
    private Long purchaseOrderId;
    /** 采购配件 */
    private Long partId;
    /** 采购单价 */
    private BigDecimal purchasePrice;
    /** 采购数量 */
    private Integer quantity;
    /** 小计金额 */
    private BigDecimal amount;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 采购订单编号(gen:OR) */
    @TableField(exist = false)
    private String purchaseNo;
    /** 配件名称 */
    @TableField(exist = false)
    private String partName;


}
