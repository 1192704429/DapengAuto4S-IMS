package com.repair.manage.module.repair_order.controller;

import cn.hutool.core.util.StrUtil;
import com.repair.manage.common.Result;
import com.repair.manage.module.repair_order.entity.RepairOrder;
import com.repair.manage.module.repair_order.service.RepairOrderService;
import com.repair.manage.basic.controller.SysUserLoginController;
import com.repair.manage.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import com.repair.manage.vo.SelectVO;

/**
 * @author wuming
 * @description: 维修工单Controller
 */
@RestController
@RequestMapping("/repair_order")
public class RepairOrderController {

    @Autowired
    private RepairOrderService repairOrderService;

    @Autowired
    private JwtUtil<SysUserLoginController.LoginUser> jwtUtil;

    /**
     * 分页查询维修工单列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) RepairOrder entity) {
        if (entity == null) {
            entity = new RepairOrder();
        }
        return Result.success(repairOrderService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有维修工单列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) RepairOrder entity) {
        if (entity == null) {
            entity = new RepairOrder();
        }
        List<RepairOrder> list = repairOrderService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询维修工单
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        RepairOrder repairOrder = repairOrderService.getById(id);
        if (repairOrder == null) {
            return Result.error("售后工单不存在");
        }
        return Result.success(repairOrder);
    }

    /**
     * 新增维修工单
     * 接待客服：当前登录用户ID；状态：待派单(0)；工程师：不分配，需后续派单
     */
    @PostMapping
    public Result save(@RequestBody RepairOrder repairOrder, HttpServletRequest request) {
        repairOrder.setCreateTime(LocalDateTime.now());
        // 新建时接待客服为当前登录用户
        if (repairOrder.getServiceUserId() == null) {
            Long currentUserId = getCurrentUserId(request);
            if (currentUserId != null) {
                repairOrder.setServiceUserId(currentUserId);
            }
        }
        // 新建时状态固定为待派单
        if (repairOrder.getId() == null) {
            repairOrder.setStatus(0);
        }
        boolean success = repairOrderService.save(repairOrder);
        if (success) {
            return Result.success("新增成功", repairOrder);
        }
        return Result.error("新增失败");
    }

    /**
     * 派单：自动分配工单数最少的工程师，状态变为维修中
     */
    @PostMapping("/assign/{id}")
    public Result assignEngineer(@PathVariable Long id) {
        boolean success = repairOrderService.assignEngineer(id);
        if (success) {
            return Result.success("派单成功");
        }
        return Result.error("派单失败，可能无可用工程师");
    }

    /**
     * 完成工单：计算费用、生成财务订单、状态变为已完成
     */
    @PostMapping("/complete/{id}")
    public Result completeOrder(@PathVariable Long id) {
        boolean success = repairOrderService.completeOrder(id);
        if (success) {
            return Result.success("工单已完成");
        }
        return Result.error("完成失败，仅作业中的工单可操作");
    }

    private Long getCurrentUserId(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StrUtil.isEmpty(authorization)) {
            return null;
        }
        try {
            if (!jwtUtil.verifyToken(authorization)) {
                return null;
            }
            SysUserLoginController.LoginUser loginUser =
                    jwtUtil.parseToken(authorization, SysUserLoginController.LoginUser.class);
            return loginUser != null ? loginUser.getId() : null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 更新维修工单
     */
    @PutMapping
    public Result update(@RequestBody RepairOrder repairOrder) {
        boolean success = repairOrderService.updateById(repairOrder);
        if (success) {
            return Result.success("更新成功", repairOrder);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除维修工单
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = repairOrderService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除维修工单
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = repairOrderService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

    /**
     * 下拉数据接口：customer
     */
    @GetMapping("/select/listCustomer")
    public Result listCustomer() {
        List<SelectVO> list = repairOrderService.listCustomer();
        return Result.success(list);
    }

    /**
     * 下拉数据接口：vehicle
     */
    @GetMapping("/select/listVehicle")
    public Result listVehicle() {
        List<SelectVO> list = repairOrderService.listVehicle();
        return Result.success(list);
    }

    /**
     * 下拉数据接口：sys_user
     */
    @GetMapping("/select/listSysUser")
    public Result listSysUser() {
        List<SelectVO> list = repairOrderService.listSysUser();
        return Result.success(list);
    }

}
