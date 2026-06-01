package com.repair.manage.module.purchase_order_detail.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.manage.module.purchase_order_detail.entity.PurchaseOrderDetail;
import com.repair.manage.module.purchase_order_detail.mapper.PurchaseOrderDetailMapper;
import com.repair.manage.module.purchase_order_detail.service.PurchaseOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 采购订单详情Service实现类
 */
@Service
public class PurchaseOrderDetailServiceImpl extends ServiceImpl<PurchaseOrderDetailMapper, PurchaseOrderDetail> implements PurchaseOrderDetailService {

    @Autowired
    private PurchaseOrderDetailMapper purchaseOrderDetailMapper;

    @Override
    public IPage<PurchaseOrderDetail> pageQuery(Integer current, Integer size, PurchaseOrderDetail entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<PurchaseOrderDetail> records = purchaseOrderDetailMapper.pageQuery(entity, offset, limit);
        Long total = purchaseOrderDetailMapper.countQuery(entity);

        Page<PurchaseOrderDetail> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<PurchaseOrderDetail> listQuery(PurchaseOrderDetail entity) {
        return purchaseOrderDetailMapper.listQuery(entity);
    }

    @Override
    public PurchaseOrderDetail getById(Long id) {
        return purchaseOrderDetailMapper.getById(id);
    }

    /**
     * 下拉数据：purchase_order
     */
    @Override
    public List<SelectVO> listPurchaseOrder() {
        return purchaseOrderDetailMapper.listPurchaseOrder();
    }

    /**
     * 下拉数据：part
     */
    @Override
    public List<SelectVO> listPart() {
        return purchaseOrderDetailMapper.listPart();
    }

}
