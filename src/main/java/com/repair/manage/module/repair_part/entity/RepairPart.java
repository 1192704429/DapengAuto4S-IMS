package com.repair.manage.module.repair_part.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author wuming
 * @description: 维修配件使用实体类
 */
@Data
@TableName("repair_part")
public class RepairPart implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 关联工单 */
    private Long repairOrderId;
    /** 使用配件 */
    private Long partId;
    /** 使用数量 */
    private Integer useQty;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 维修工单编号(gen:OR) */
    @TableField(exist = false)
    private String orderNo;
    /** 配件名称 */
    @TableField(exist = false)
    private String partName;
    /** 配件销售价（查询时关联） */
    @TableField(exist = false)
    private BigDecimal salePrice;


}
