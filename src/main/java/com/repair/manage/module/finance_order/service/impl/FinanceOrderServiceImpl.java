package com.repair.manage.module.finance_order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.manage.module.finance_order.entity.FinanceOrder;
import com.repair.manage.module.finance_order.mapper.FinanceOrderMapper;
import com.repair.manage.module.finance_order.service.FinanceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 财务订单Service实现类
 */
@Service
public class FinanceOrderServiceImpl extends ServiceImpl<FinanceOrderMapper, FinanceOrder> implements FinanceOrderService {

    @Autowired
    private FinanceOrderMapper financeOrderMapper;

    @Override
    public IPage<FinanceOrder> pageQuery(Integer current, Integer size, FinanceOrder entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<FinanceOrder> records = financeOrderMapper.pageQuery(entity, offset, limit);
        Long total = financeOrderMapper.countQuery(entity);

        Page<FinanceOrder> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<FinanceOrder> listQuery(FinanceOrder entity) {
        return financeOrderMapper.listQuery(entity);
    }

    @Override
    public FinanceOrder getById(Long id) {
        return financeOrderMapper.getById(id);
    }

    /**
     * 下拉数据：purchase_order
     */
    @Override
    public List<SelectVO> listPurchaseOrder() {
        return financeOrderMapper.listPurchaseOrder();
    }

    /**
     * 下拉数据：repair_order
     */
    @Override
    public List<SelectVO> listRepairOrder() {
        return financeOrderMapper.listRepairOrder();
    }

    /**
     * 财务审核通过：收入项待审核(0) -> 已审核(1)
     */
    @Override
    public boolean approve(Long id) {
        FinanceOrder fo = financeOrderMapper.getById(id);
        if (fo == null) return false;
        if (fo.getOrderType() == null || fo.getOrderType() != 1) return false; // 仅收入项可审核
        if (fo.getStatus() == null || fo.getStatus() != 0) return false; // 仅待审核可审核
        fo.setStatus(1);
        return this.updateById(fo);
    }

}
