package com.repair.manage.module.finance_order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.manage.module.finance_order.entity.FinanceOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 财务订单Mapper接口
 */
@Mapper
public interface FinanceOrderMapper extends BaseMapper<FinanceOrder> {

    /**
     * 分页查询
     */
    List<FinanceOrder> pageQuery(@Param("entity") FinanceOrder entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") FinanceOrder entity);

    /**
     * 列表查询（不分页）
     */
    List<FinanceOrder> listQuery(@Param("entity") FinanceOrder entity);

    /**
     * 根据ID查询
     */
    FinanceOrder getById(@Param("id") Long id);

    /**
     * 下拉选项查询
     */
    /**
     * 下拉数据：purchase_order
     */
    @Select("select purchase_no as label, id as value from purchase_order")
    List<SelectVO> listPurchaseOrder();

    /**
     * 下拉数据：repair_order
     */
    @Select("select order_no as label, id as value from repair_order")
    List<SelectVO> listRepairOrder();

}
