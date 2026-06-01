package com.repair.manage.basic.controller;

import com.repair.manage.basic.service.EchartsService;
import com.repair.manage.vo.EchartsDataVO;
import com.repair.manage.vo.FinanceMonthlyVO;
import com.repair.manage.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 图表可视化接口
 */
@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private EchartsService echartsService;

    /**
     * 用户角色分布统计（前端传 roleType: 1=admin 2=service 3=engineer 4=finance -1=全部）
     */
    @PostMapping("/countUserByRoleType")
    public Result countUserByRoleType(@RequestBody Map<String, Object> params) {
        Integer roleType = Optional.ofNullable(params.get("roleType"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .map(Integer::valueOf)
                .orElse(null);
        String roleTypeStr = null;
        if (roleType != null && roleType != -1) {
            switch (roleType) {
                case 1: roleTypeStr = "admin"; break;
                case 2: roleTypeStr = "service"; break;
                case 3: roleTypeStr = "engineer"; break;
                case 4: roleTypeStr = "finance"; break;
                default: break;
            }
        }
        List<EchartsDataVO> data = echartsService.countUserByRoleType(roleTypeStr);
        return Result.success(data);
    }

    /**
     * 车辆类型分布统计
     */
    @PostMapping("/countVehicleByType")
    public Result countVehicleByType(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        Integer vehicleType = Optional.ofNullable(params.get("vehicleType"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .map(Integer::valueOf)
                .orElse(null);
        List<EchartsDataVO> data = echartsService.countVehicleByType(vehicleType);
        return Result.success(data);
    }

    /**
     * 维修工单状态统计
     */
    @PostMapping("/countRepairOrderByStatus")
    public Result countRepairOrderByStatus(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        String startTime = Optional.ofNullable(params.get("startTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        String endTime = Optional.ofNullable(params.get("endTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        Integer status = Optional.ofNullable(params.get("status"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .map(Integer::valueOf)
                .orElse(null);
        List<EchartsDataVO> data = echartsService.countRepairOrderByStatus(startTime, endTime, status);
        return Result.success(data);
    }

    /**
     * 采购订单状态统计
     */
    @PostMapping("/countPurchaseOrderByStatus")
    public Result countPurchaseOrderByStatus(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        String startTime = Optional.ofNullable(params.get("startTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        String endTime = Optional.ofNullable(params.get("endTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        Integer status = Optional.ofNullable(params.get("status"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .map(Integer::valueOf)
                .orElse(null);
        List<EchartsDataVO> data = echartsService.countPurchaseOrderByStatus(startTime, endTime, status);
        return Result.success(data);
    }

    /**
     * 财务收支金额统计
     */
    @PostMapping("/sumFinanceByOrderType")
    public Result sumFinanceByOrderType(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        String startTime = Optional.ofNullable(params.get("startTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        String endTime = Optional.ofNullable(params.get("endTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        Integer orderType = Optional.ofNullable(params.get("orderType"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .map(Integer::valueOf)
                .orElse(null);
        List<EchartsDataVO> data = echartsService.sumFinanceByOrderType(startTime, endTime, orderType);
        return Result.success(data);
    }

    /**
     * 财务月度收支统计
     */
    @PostMapping("/sumFinanceByMonth")
    public Result sumFinanceByMonth(@RequestBody Map<String, Object> params) {
        String startTime = Optional.ofNullable(params.get("startTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        String endTime = Optional.ofNullable(params.get("endTime"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .orElse(null);
        List<FinanceMonthlyVO> data = echartsService.sumFinanceByMonth(startTime, endTime);
        return Result.success(data);
    }

    /**
     * 配件分类库存统计
     */
    @PostMapping("/countPartByCategory")
    public Result countPartByCategory(@RequestBody Map<String, Object> params) {
        // 从 RequestBody 中提取参数
        Long categoryId = Optional.ofNullable(params.get("categoryId"))
                .map(Object::toString)
                .filter(s -> !s.isBlank())
                .map(Long::valueOf)
                .orElse(null);
        List<EchartsDataVO> data = echartsService.countPartByCategory(categoryId);
        return Result.success(data);
    }

}
