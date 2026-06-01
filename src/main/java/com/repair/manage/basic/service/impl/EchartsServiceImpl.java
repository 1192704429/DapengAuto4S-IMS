package com.repair.manage.basic.service.impl;

import com.repair.manage.basic.mapper.EchartsMapper;
import com.repair.manage.basic.service.EchartsService;
import com.repair.manage.vo.EchartsDataVO;
import com.repair.manage.vo.FinanceMonthlyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchartsServiceImpl implements EchartsService {

    @Autowired
    private EchartsMapper echartsMapper;

    /**
     * 用户角色分布统计
     */
    @Override
    public List<EchartsDataVO> countUserByRoleType(String roleTypeStr) {
        return echartsMapper.countUserByRoleType(roleTypeStr);
    }

    /**
     * 车辆类型分布统计
     */
    @Override
    public List<EchartsDataVO> countVehicleByType(Integer vehicleType) {
        return echartsMapper.countVehicleByType(vehicleType);
    }

    /**
     * 维修工单状态统计
     */
    @Override
    public List<EchartsDataVO> countRepairOrderByStatus(String startTime, String endTime, Integer status) {
        return echartsMapper.countRepairOrderByStatus(startTime, endTime, status);
    }

    /**
     * 采购订单状态统计
     */
    @Override
    public List<EchartsDataVO> countPurchaseOrderByStatus(String startTime, String endTime, Integer status) {
        return echartsMapper.countPurchaseOrderByStatus(startTime, endTime, status);
    }

    /**
     * 财务收支金额统计
     */
    @Override
    public List<EchartsDataVO> sumFinanceByOrderType(String startTime, String endTime, Integer orderType) {
        return echartsMapper.sumFinanceByOrderType(startTime, endTime, orderType);
    }

    /**
     * 配件分类库存统计
     */
    @Override
    public List<EchartsDataVO> countPartByCategory(Long categoryId) {
        return echartsMapper.countPartByCategory(categoryId);
    }

    /**
     * 财务月度收支统计
     */
    @Override
    public List<FinanceMonthlyVO> sumFinanceByMonth(String startTime, String endTime) {
        return echartsMapper.sumFinanceByMonth(startTime, endTime);
    }

}
