package com.repair.manage.module.vehicle.entity;

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
 * @description: 车辆档案实体类
 */
@Data
@TableName("vehicle")
public class Vehicle implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 所属客户 */
    private Long customerId;
    /** 车牌号 */
    private String plateNumber;
    /** 车架号 */
    private String vinCode;
    /** 发动机号 */
    private String engineNumber;
    /** 车辆照片 */
    private String carImg;
    /** 车辆品牌 */
    private String brand;
    /** 车辆型号 */
    private String model;
    /** 车辆类型 */
    private Integer vehicleType;
    /** 车辆颜色 */
    private String color;
    /** 购车日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime purchaseDate;
    /** 当前里程 */
    private Integer mileage;
    /** 保险公司 */
    private String insuranceCompany;
    /** 保险到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime insuranceExpireTime;
    /** 备注 */
    private String remark;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 客户姓名 */
    @TableField(exist = false)
    private String customerName;


}
