package com.repair.manage.module.repair_order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.manage.module.repair_order.entity.RepairOrder;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 维修工单Service接口
 */
public interface RepairOrderService extends IService<RepairOrder> {

    /**
     * 分页查询
     */
    IPage<RepairOrder> pageQuery(Integer current, Integer size, RepairOrder entity);

    /**
     * 列表查询（不分页）
     */
    List<RepairOrder> listQuery(RepairOrder entity);

    /**
     * 根据ID查询
     */
    RepairOrder getById(Long id);

    /**
     * 下拉数据：customer
     */
    List<SelectVO> listCustomer();

    /**
     * 下拉数据：vehicle
     */
    List<SelectVO> listVehicle();

    /**
     * 下拉数据：sys_user
     */
    List<SelectVO> listSysUser();

    /**
     * 派单：自动分配工单数最少的工程师，状态变为维修中
     */
    boolean assignEngineer(Long orderId);

    /**
     * 完成工单：计算费用、生成财务订单、状态变为已完成
     */
    boolean completeOrder(Long orderId);

}
