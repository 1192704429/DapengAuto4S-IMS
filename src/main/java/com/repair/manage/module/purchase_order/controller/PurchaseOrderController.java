package com.repair.manage.module.purchase_order.controller;

import cn.hutool.core.util.StrUtil;
import com.repair.manage.common.Result;
import com.repair.manage.basic.controller.SysUserLoginController;
import com.repair.manage.module.purchase_order.entity.PurchaseOrder;
import com.repair.manage.module.purchase_order.service.PurchaseOrderService;
import com.repair.manage.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import com.repair.manage.vo.SelectVO;

/**
 * @author wuming
 * @description: 采购订单Controller
 */
@RestController
@RequestMapping("/purchase_order")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private JwtUtil<SysUserLoginController.LoginUser> jwtUtil;

    /**
     * 分页查询采购订单列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) PurchaseOrder entity) {
        if (entity == null) {
            entity = new PurchaseOrder();
        }
        return Result.success(purchaseOrderService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有采购订单列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) PurchaseOrder entity) {
        if (entity == null) {
            entity = new PurchaseOrder();
        }
        List<PurchaseOrder> list = purchaseOrderService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询采购订单
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderService.getById(id);
        if (purchaseOrder == null) {
            return Result.error("采购订单不存在");
        }
        return Result.success(purchaseOrder);
    }

    /**
     * 新增采购订单
     */
    @PostMapping
    public Result save(@RequestBody PurchaseOrder purchaseOrder) {
        purchaseOrder.setCreateTime(LocalDateTime.now());
        if (purchaseOrder.getId() == null && (purchaseOrder.getPurchaseNo() == null || purchaseOrder.getPurchaseNo().isEmpty())) {
            purchaseOrder.setPurchaseNo("PO" + System.currentTimeMillis());
        }
        if (purchaseOrder.getId() == null) {
            purchaseOrder.setStatus(0);
        }
        boolean success = purchaseOrderService.save(purchaseOrder);
        if (success) {
            return Result.success("新增成功", purchaseOrder);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新采购订单
     */
    @PutMapping
    public Result update(@RequestBody PurchaseOrder purchaseOrder) {
        boolean success = purchaseOrderService.updateById(purchaseOrder);
        if (success) {
            return Result.success("更新成功", purchaseOrder);
        }
        return Result.error("更新失败");
    }

    /**
     * 审核通过：管理员审核，状态 待审核(0) -> 审核通过(1)
     */
    @PostMapping("/approve/{id}")
    public Result approve(@PathVariable Long id, HttpServletRequest request) {
        String role = getCurrentUserRole(request);
        if (!"admin".equals(role)) {
            return Result.error("仅管理员可审核");
        }
        boolean success = purchaseOrderService.approve(id);
        if (success) {
            return Result.success("审核通过");
        }
        return Result.error("审核失败，仅待审核订单可操作");
    }

    /**
     * 确认入库：审核通过后可入库，更新配件库存，状态 审核通过(1) -> 已入库(2)
     */
    @PostMapping("/confirm/{id}")
    public Result confirmInbound(@PathVariable Long id) {
        boolean success = purchaseOrderService.confirmInbound(id);
        if (success) {
            return Result.success("入库成功");
        }
        return Result.error("入库失败，仅审核通过的订单可操作");
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
     * 根据ID删除采购订单
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = purchaseOrderService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除采购订单
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = purchaseOrderService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

    /**
     * 下拉数据接口：supplier
     */
    @GetMapping("/select/listSupplier")
    public Result listSupplier() {
        List<SelectVO> list = purchaseOrderService.listSupplier();
        return Result.success(list);
    }

}
