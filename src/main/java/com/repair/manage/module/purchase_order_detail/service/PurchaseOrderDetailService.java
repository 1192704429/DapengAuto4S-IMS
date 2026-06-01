package com.repair.manage.module.purchase_order_detail.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.manage.module.purchase_order_detail.entity.PurchaseOrderDetail;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 采购订单详情Service接口
 */
public interface PurchaseOrderDetailService extends IService<PurchaseOrderDetail> {

    /**
     * 分页查询
     */
    IPage<PurchaseOrderDetail> pageQuery(Integer current, Integer size, PurchaseOrderDetail entity);

    /**
     * 列表查询（不分页）
     */
    List<PurchaseOrderDetail> listQuery(PurchaseOrderDetail entity);

    /**
     * 根据ID查询
     */
    PurchaseOrderDetail getById(Long id);

    /**
     * 下拉数据：purchase_order
     */
    List<SelectVO> listPurchaseOrder();

    /**
     * 下拉数据：part
     */
    List<SelectVO> listPart();

}
