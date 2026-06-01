package com.repair.manage.module.repair_order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.manage.module.finance_order.entity.FinanceOrder;
import com.repair.manage.module.finance_order.service.FinanceOrderService;
import com.repair.manage.module.repair_detail.entity.RepairDetail;
import com.repair.manage.module.repair_detail.mapper.RepairDetailMapper;
import com.repair.manage.module.repair_order.entity.RepairOrder;
import com.repair.manage.module.repair_order.mapper.RepairOrderMapper;
import com.repair.manage.module.repair_order.service.RepairOrderService;
import com.repair.manage.module.repair_part.entity.RepairPart;
import com.repair.manage.module.repair_part.mapper.RepairPartMapper;
import com.repair.manage.module.sys_user.entity.SysUser;
import com.repair.manage.module.sys_user.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repair.manage.vo.SelectVO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author wuming
 * @description: 维修工单Service实现类
 */
@Service
public class RepairOrderServiceImpl extends ServiceImpl<RepairOrderMapper, RepairOrder> implements RepairOrderService {

    @Autowired
    private RepairOrderMapper repairOrderMapper;
    @Autowired
    private RepairDetailMapper repairDetailMapper;
    @Autowired
    private RepairPartMapper repairPartMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private FinanceOrderService financeOrderService;

    @Override
    public IPage<RepairOrder> pageQuery(Integer current, Integer size, RepairOrder entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<RepairOrder> records = repairOrderMapper.pageQuery(entity, offset, limit);
        Long total = repairOrderMapper.countQuery(entity);

        Page<RepairOrder> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<RepairOrder> listQuery(RepairOrder entity) {
        return repairOrderMapper.listQuery(entity);
    }

    @Override
    public RepairOrder getById(Long id) {
        return repairOrderMapper.getById(id);
    }

    /**
     * 下拉数据：customer
     */
    @Override
    public List<SelectVO> listCustomer() {
        return repairOrderMapper.listCustomer();
    }

    /**
     * 下拉数据：vehicle
     */
    @Override
    public List<SelectVO> listVehicle() {
        return repairOrderMapper.listVehicle();
    }

    /**
     * 下拉数据：sys_user
     */
    @Override
    public List<SelectVO> listSysUser() {
        return repairOrderMapper.listSysUser();
    }

    /**
     * 派单：自动分配工单数最少的工程师，状态变为维修中
     */
    @Override
    public boolean assignEngineer(Long orderId) {
        RepairOrder order = repairOrderMapper.getById(orderId);
        if (order == null) {
            return false;
        }
        if (order.getStatus() != null && order.getStatus() != 0) {
            return false; // 只有待派单才能派单
        }
        Long engineerId = repairOrderMapper.selectEngineerWithFewestOrders();
        if (engineerId == null) {
            return false;
        }
        order.setEngineerUserId(engineerId);
        order.setStatus(1);
        return this.updateById(order);
    }

    /**
     * 完成工单：计算费用、生成财务订单、状态变为已完成
     */
    @Override
    public boolean completeOrder(Long orderId) {
        RepairOrder order = repairOrderMapper.getById(orderId);
        if (order == null) {
            return false;
        }
        if (order.getStatus() == null || order.getStatus() != 1) {
            return false; // 只有维修中才能完成
        }
        Long engineerId = order.getEngineerUserId();
        if (engineerId == null) {
            return false;
        }
        SysUser engineer = sysUserMapper.getById(engineerId);
        BigDecimal hourPrice = engineer != null ? engineer.getEngineerHourPrice() : BigDecimal.ZERO;
        if (hourPrice == null) {
            hourPrice = BigDecimal.ZERO;
        }

        // 维修详情：总工时
        RepairDetail detailQuery = new RepairDetail();
        detailQuery.setRepairOrderId(orderId);
        List<RepairDetail> detailList = repairDetailMapper.listQuery(detailQuery);
        BigDecimal totalWorkHours = BigDecimal.ZERO;
        for (RepairDetail d : detailList) {
            if (d.getWorkHours() != null) {
                totalWorkHours = totalWorkHours.add(d.getWorkHours());
            }
        }
        BigDecimal laborCost = totalWorkHours.multiply(hourPrice);

        // 配件使用：价格*数量
        List<RepairPart> partList = repairPartMapper.listByRepairOrderIdWithPrice(orderId);
        BigDecimal partsCost = BigDecimal.ZERO;
        StringBuilder partDetailSb = new StringBuilder();
        for (RepairPart rp : partList) {
            BigDecimal price = rp.getSalePrice() != null ? rp.getSalePrice() : BigDecimal.ZERO;
            int qty = rp.getUseQty() != null ? rp.getUseQty() : 0;
            BigDecimal lineTotal = price.multiply(BigDecimal.valueOf(qty));
            partsCost = partsCost.add(lineTotal);
            if (partDetailSb.length() > 0) partDetailSb.append("；");
            partDetailSb.append(rp.getPartName() != null ? rp.getPartName() : "配件").append("×").append(qty).append("=").append(lineTotal).append("元");
        }

        BigDecimal totalAmount = laborCost.add(partsCost);

        // 构建备注明细
        StringBuilder remark = new StringBuilder();
        remark.append("【工时费】总工时").append(totalWorkHours).append("×工程师工时费").append(hourPrice).append("元/时=").append(laborCost).append("元");
        if (partList.size() > 0) {
            remark.append("；【配件费】").append(partDetailSb);
        }
        remark.append("；【合计】").append(totalAmount).append("元");

        // 检查是否已有该工单的财务订单
        FinanceOrder foQuery = new FinanceOrder();
        foQuery.setRepairOrderId(orderId);
        List<FinanceOrder> existingList = financeOrderService.listQuery(foQuery);
        FinanceOrder financeOrder;
        if (existingList != null && !existingList.isEmpty()) {
            financeOrder = existingList.get(0);
            financeOrder.setAmount(totalAmount);
            financeOrder.setRemark(remark.toString());
            financeOrder.setStatus(0); // 待财务审核
            financeOrderService.updateById(financeOrder);
        } else {
            financeOrder = new FinanceOrder();
            financeOrder.setFinanceNo("FO" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
            financeOrder.setOrderType(1); // 1=收入
            financeOrder.setRepairOrderId(orderId);
            financeOrder.setAmount(totalAmount);
            financeOrder.setRemark(remark.toString());
            financeOrder.setStatus(0); // 待财务审核
            financeOrder.setCreateTime(LocalDateTime.now());
            financeOrderService.save(financeOrder);
        }

        order.setStatus(2);
        return this.updateById(order);
    }

}
