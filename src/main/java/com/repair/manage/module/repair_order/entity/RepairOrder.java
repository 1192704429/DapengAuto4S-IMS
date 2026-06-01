package com.repair.manage.module.repair_order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wuming
 * @description: 维修工单实体类
 */
@Data
@TableName("repair_order")
public class RepairOrder implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 维修工单编号(gen:OR) */
    private String orderNo;
    /** 关联客户 */
    private Long customerId;
    /** 关联车辆 */
    private Long vehicleId;
    /** 接待客服 */
    private Long serviceUserId;
    /** 维修工程师 */
    private Long engineerUserId;
    /** 关联维修项目分类 */
    private Long repairItemCategoryId;
    /** 故障描述 */
    private String faultDesc;
    /** 工单状态 */
    private Integer status;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 客户姓名 */
    @TableField(exist = false)
    private String customerName;
    /** 车牌号 */
    @TableField(exist = false)
    private String plateNumber;
    /** 真实姓名 */
    @TableField(exist = false)
    private String realName;
    /** 真实姓名 */
    @TableField(exist = false)
    private String realName2;
    /** 维修项目分类名称 */
    @TableField(exist = false)
    private String categoryName;


}
