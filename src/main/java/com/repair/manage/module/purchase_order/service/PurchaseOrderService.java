package com.repair.manage.module.purchase_order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.manage.module.purchase_order.entity.PurchaseOrder;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 采购订单Service接口
 */
public interface PurchaseOrderService extends IService<PurchaseOrder> {

    /**
     * 分页查询
     */
    IPage<PurchaseOrder> pageQuery(Integer current, Integer size, PurchaseOrder entity);

    /**
     * 列表查询（不分页）
     */
    List<PurchaseOrder> listQuery(PurchaseOrder entity);

    /**
     * 根据ID查询
     */
    PurchaseOrder getById(Long id);

    /**
     * 下拉数据：supplier
     */
    List<SelectVO> listSupplier();

    /**
     * 审核通过：管理员审核，状态 待审核(0) -> 审核通过(1)
     */
    boolean approve(Long orderId);

    /**
     * 确认入库：审核通过后可入库，更新配件库存，状态 审核通过(1) -> 已入库(2)
     */
    boolean confirmInbound(Long orderId);

}
