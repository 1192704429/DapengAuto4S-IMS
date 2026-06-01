package com.repair.manage.module.repair_detail.entity;

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
 * @description: 维修详情实体类
 */
@Data
@TableName("repair_detail")
public class RepairDetail implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 关联工单 */
    private Long repairOrderId;
    /** 维修工时 */
    private BigDecimal workHours;
    /** 维修内容 */
    private String repairContent;
    /** 技术说明 */
    private String techReport;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 维修工单编号(gen:OR) */
    @TableField(exist = false)
    private String orderNo;


}
