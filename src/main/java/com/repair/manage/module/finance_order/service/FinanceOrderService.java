package com.repair.manage.module.finance_order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.manage.module.finance_order.entity.FinanceOrder;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 财务订单Service接口
 */
public interface FinanceOrderService extends IService<FinanceOrder> {

    /**
     * 分页查询
     */
    IPage<FinanceOrder> pageQuery(Integer current, Integer size, FinanceOrder entity);

    /**
     * 列表查询（不分页）
     */
    List<FinanceOrder> listQuery(FinanceOrder entity);

    /**
     * 根据ID查询
     */
    FinanceOrder getById(Long id);

    /**
     * 下拉数据：purchase_order
     */
    List<SelectVO> listPurchaseOrder();

    /**
     * 下拉数据：repair_order
     */
    List<SelectVO> listRepairOrder();

    /**
     * 财务审核通过：收入项待审核(0) -> 已审核(1)
     */
    boolean approve(Long id);

}
