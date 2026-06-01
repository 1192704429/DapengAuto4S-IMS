package com.repair.manage.module.purchase_order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.manage.module.purchase_order.entity.PurchaseOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 采购订单Mapper接口
 */
@Mapper
public interface PurchaseOrderMapper extends BaseMapper<PurchaseOrder> {

    /**
     * 分页查询
     */
    List<PurchaseOrder> pageQuery(@Param("entity") PurchaseOrder entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") PurchaseOrder entity);

    /**
     * 列表查询（不分页）
     */
    List<PurchaseOrder> listQuery(@Param("entity") PurchaseOrder entity);

    /**
     * 根据ID查询
     */
    PurchaseOrder getById(@Param("id") Long id);

    /**
     * 下拉选项查询
     */
    /**
     * 下拉数据：supplier
     */
    @Select("select supplier_name as label, id as value from supplier")
    List<SelectVO> listSupplier();

}
