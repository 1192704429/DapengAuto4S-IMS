package com.repair.manage.basic.service;

import com.repair.manage.vo.EchartsDataVO;
import com.repair.manage.vo.FinanceMonthlyVO;

import java.util.List;

public interface EchartsService {

    /**
     * 用户角色分布统计（roleTypeStr: admin/service/engineer/finance，为空则查全部）
     */
    List<EchartsDataVO> countUserByRoleType(String roleTypeStr);

    /**
     * 车辆类型分布统计
     */
    List<EchartsDataVO> countVehicleByType(Integer vehicleType);

    /**
     * 维修工单状态统计
     */
    List<EchartsDataVO> countRepairOrderByStatus(String startTime, String endTime, Integer status);

    /**
     * 采购订单状态统计
     */
    List<EchartsDataVO> countPurchaseOrderByStatus(String startTime, String endTime, Integer status);

    /**
     * 财务收支金额统计
     */
    List<EchartsDataVO> sumFinanceByOrderType(String startTime, String endTime, Integer orderType);

    /**
     * 配件分类库存统计
     */
    List<EchartsDataVO> countPartByCategory(Long categoryId);

    /**
     * 财务月度收支统计
     */
    List<FinanceMonthlyVO> sumFinanceByMonth(String startTime, String endTime);

}
