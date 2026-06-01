package com.repair.manage.module.finance_order.controller;

import cn.hutool.core.util.StrUtil;
import com.repair.manage.common.Result;
import com.repair.manage.basic.controller.SysUserLoginController;
import com.repair.manage.module.finance_order.entity.FinanceOrder;
import com.repair.manage.module.finance_order.service.FinanceOrderService;
import com.repair.manage.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import com.repair.manage.vo.SelectVO;

/**
 * @author wuming
 * @description: 财务订单Controller
 */
@RestController
@RequestMapping("/finance_order")
public class FinanceOrderController {

    @Autowired
    private FinanceOrderService financeOrderService;

    @Autowired
    private JwtUtil<SysUserLoginController.LoginUser> jwtUtil;

    /**
     * 分页查询财务订单列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) FinanceOrder entity) {
        if (entity == null) {
            entity = new FinanceOrder();
        }
        return Result.success(financeOrderService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有财务订单列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) FinanceOrder entity) {
        if (entity == null) {
            entity = new FinanceOrder();
        }
        List<FinanceOrder> list = financeOrderService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询财务订单
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        FinanceOrder financeOrder = financeOrderService.getById(id);
        if (financeOrder == null) {
            return Result.error("财务订单不存在");
        }
        return Result.success(financeOrder);
    }

    /**
     * 新增财务订单（手动新建默认已审核）
     */
    @PostMapping
    public Result save(@RequestBody FinanceOrder financeOrder) {
        financeOrder.setCreateTime(LocalDateTime.now());
        if (financeOrder.getStatus() == null) {
            financeOrder.setStatus(1); // 手动新建默认已审核
        }
        if (StrUtil.isBlank(financeOrder.getFinanceNo())) {
            financeOrder.setFinanceNo("FO" + System.currentTimeMillis());
        }
        boolean success = financeOrderService.save(financeOrder);
        if (success) {
            return Result.success("新增成功", financeOrder);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新财务订单
     */
    @PutMapping
    public Result update(@RequestBody FinanceOrder financeOrder) {
        boolean success = financeOrderService.updateById(financeOrder);
        if (success) {
            return Result.success("更新成功", financeOrder);
        }
        return Result.error("更新失败");
    }

    /**
     * 财务审核通过：收入项待审核 -> 已审核（仅财务角色可操作）
     */
    @PostMapping("/approve/{id}")
    public Result approve(@PathVariable Long id, HttpServletRequest request) {
        String role = getCurrentUserRole(request);
        if (!"finance".equals(role) && !"admin".equals(role)) {
            return Result.error("仅财务或管理员可审核");
        }
        boolean success = financeOrderService.approve(id);
        if (success) {
            return Result.success("审核通过");
        }
        return Result.error("审核失败，仅待审核的收入项可操作");
    }

    private String getCurrentUserRole(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StrUtil.isEmpty(authorization)) return null;
        try {
            if (!jwtUtil.verifyToken(authorization)) return null;
            SysUserLoginController.LoginUser loginUser = jwtUtil.parseToken(authorization, SysUserLoginController.LoginUser.class);
            return loginUser != null ? loginUser.getRole() : null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据ID删除财务订单
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = financeOrderService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除财务订单
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = financeOrderService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

    /**
     * 下拉数据接口：purchase_order
     */
    @GetMapping("/select/listPurchaseOrder")
    public Result listPurchaseOrder() {
        List<SelectVO> list = financeOrderService.listPurchaseOrder();
        return Result.success(list);
    }

    /**
     * 下拉数据接口：repair_order
     */
    @GetMapping("/select/listRepairOrder")
    public Result listRepairOrder() {
        List<SelectVO> list = financeOrderService.listRepairOrder();
        return Result.success(list);
    }

}
