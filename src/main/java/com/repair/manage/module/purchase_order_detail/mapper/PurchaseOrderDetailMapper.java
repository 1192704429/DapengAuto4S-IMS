package com.repair.manage.module.purchase_order_detail.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.manage.module.purchase_order_detail.entity.PurchaseOrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 采购订单详情Mapper接口
 */
@Mapper
public interface PurchaseOrderDetailMapper extends BaseMapper<PurchaseOrderDetail> {

    /**
     * 分页查询
     */
    List<PurchaseOrderDetail> pageQuery(@Param("entity") PurchaseOrderDetail entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") PurchaseOrderDetail entity);

    /**
     * 列表查询（不分页）
     */
    List<PurchaseOrderDetail> listQuery(@Param("entity") PurchaseOrderDetail entity);

    /**
     * 根据ID查询
     */
    PurchaseOrderDetail getById(@Param("id") Long id);

    /**
     * 下拉选项查询
     */
    /**
     * 下拉数据：purchase_order
     */
    @Select("select purchase_no as label, id as value from purchase_order")
    List<SelectVO> listPurchaseOrder();

    /**
     * 下拉数据：part
     */
    @Select("select part_name as label, id as value from part")
    List<SelectVO> listPart();

}
