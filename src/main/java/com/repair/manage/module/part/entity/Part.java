package com.repair.manage.module.part.entity;

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
 * @description: 配件信息实体类
 */
@Data
@TableName("part")
public class Part implements Serializable {

    private static final long serialVersionUID=1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 配件编码 */
    private String partCode;
    /** 配件名称 */
    private String partName;
    /** 规格型号 */
    private String specModel;
    /** 品牌 */
    private String brand;
    /** 所属分类 */
    private Long categoryId;
    /** 供应商 */
    private Long supplierId;
    /** 计量单位 */
    private String unit;
    /** 采购价 */
    private BigDecimal purchasePrice;
    /** 销售价 */
    private BigDecimal salePrice;
    /** 当前库存 */
    private Integer stock;
    /** 库存预警 */
    private Integer warnStock;
    /** 配件图片 */
    private String image;
    /** 备注 */
    private String remark;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 配件分类名称 */
    @TableField(exist = false)
    private String categoryName;
    /** 供应商名称 */
    @TableField(exist = false)
    private String supplierName;


}
