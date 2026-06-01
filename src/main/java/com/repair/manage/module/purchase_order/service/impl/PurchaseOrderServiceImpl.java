package com.repair.manage.module.purchase_order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.manage.module.part.entity.Part;
import com.repair.manage.module.part.service.PartService;
import com.repair.manage.module.purchase_order.entity.PurchaseOrder;
import com.repair.manage.module.purchase_order.mapper.PurchaseOrderMapper;
import com.repair.manage.module.purchase_order.service.PurchaseOrderService;
import com.repair.manage.module.purchase_order_detail.entity.PurchaseOrderDetail;
import com.repair.manage.module.purchase_order_detail.service.PurchaseOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 采购订单Service实现类
 */
@Service
public class PurchaseOrderServiceImpl extends ServiceImpl<PurchaseOrderMapper, PurchaseOrder> implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    private PurchaseOrderDetailService purchaseOrderDetailService;
    @Autowired
    private PartService partService;

    @Override
    public IPage<PurchaseOrder> pageQuery(Integer current, Integer size, PurchaseOrder entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<PurchaseOrder> records = purchaseOrderMapper.pageQuery(entity, offset, limit);
        Long total = purchaseOrderMapper.countQuery(entity);

        Page<PurchaseOrder> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<PurchaseOrder> listQuery(PurchaseOrder entity) {
        return purchaseOrderMapper.listQuery(entity);
    }

    @Override
    public PurchaseOrder getById(Long id) {
        return purchaseOrderMapper.getById(id);
    }

    /**
     * 下拉数据：supplier
     */
    @Override
    public List<SelectVO> listSupplier() {
        return purchaseOrderMapper.listSupplier();
    }

    /**
     * 审核通过：管理员审核，状态 待审核(0) -> 审核通过(1)
     */
    @Override
    public boolean approve(Long orderId) {
        PurchaseOrder order = purchaseOrderMapper.getById(orderId);
        if (order == null) return false;
        if (order.getStatus() == null || order.getStatus() != 0) return false;
        order.setStatus(1);
        return this.updateById(order);
    }

    /**
     * 确认入库：审核通过后可入库，更新配件库存，状态 审核通过(1) -> 已入库(2)
     */
    @Override
    public boolean confirmInbound(Long orderId) {
        PurchaseOrder order = purchaseOrderMapper.getById(orderId);
        if (order == null) return false;
        if (order.getStatus() == null || order.getStatus() != 1) return false;

        PurchaseOrderDetail detailQuery = new PurchaseOrderDetail();
        detailQuery.setPurchaseOrderId(orderId);
        List<PurchaseOrderDetail> details = purchaseOrderDetailService.listQuery(detailQuery);
        for (PurchaseOrderDetail d : details) {
            if (d.getPartId() == null || d.getQuantity() == null) continue;
            Part part = partService.getById(d.getPartId());
            if (part != null) {
                int addQty = d.getQuantity();
                int oldStock = part.getStock() != null ? part.getStock() : 0;
                part.setStock(oldStock + addQty);
                partService.updateById(part);
            }
        }
        order.setStatus(2);
        return this.updateById(order);
    }

}
