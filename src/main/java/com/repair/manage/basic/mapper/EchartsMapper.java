package com.repair.manage.basic.mapper;

import com.repair.manage.vo.EchartsDataVO;
import com.repair.manage.vo.FinanceMonthlyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EchartsMapper {

    /**
     * 用户角色分布统计（roleTypeStr: admin/service/engineer/finance，为空则查全部）
     */
    List<EchartsDataVO> countUserByRoleType(@Param("roleTypeStr") String roleTypeStr);

    /**
     * 车辆类型分布统计
     */
    List<EchartsDataVO> countVehicleByType(@Param("vehicleType") Integer vehicleType);

    /**
     * 维修工单状态统计
     */
    List<EchartsDataVO> countRepairOrderByStatus(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("status") Integer status);

    /**
     * 采购订单状态统计
     */
    List<EchartsDataVO> countPurchaseOrderByStatus(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("status") Integer status);

    /**
     * 财务收支金额统计
     */
    List<EchartsDataVO> sumFinanceByOrderType(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("orderType") Integer orderType);

    /**
     * 配件分类库存统计
     */
    List<EchartsDataVO> countPartByCategory(@Param("categoryId") Long categoryId);

    /**
     * 财务月度收支统计
     */
    List<FinanceMonthlyVO> sumFinanceByMonth(@Param("startTime") String startTime, @Param("endTime") String endTime);

}
