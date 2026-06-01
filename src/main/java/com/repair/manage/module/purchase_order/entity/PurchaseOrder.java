package com.repair.manage.module.purchase_order.entity;

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
 * @description: 采购订单实体类
 */
@Data
@TableName("purchase_order")
public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 采购订单编号(gen:OR) */
    private String purchaseNo;
    /** 供应商 */
    private Long supplierId;
    /** 订单总金额 */
    private BigDecimal totalAmount;
    /** 采购状态 */
    private Integer status;
    /** 备注 */
    private String remark;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 供应商名称 */
    @TableField(exist = false)
    private String supplierName;


}
