package com.repair.manage.module.purchase_order_detail.controller;

import com.repair.manage.common.Result;
import com.repair.manage.module.purchase_order_detail.entity.PurchaseOrderDetail;
import com.repair.manage.module.purchase_order_detail.service.PurchaseOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import com.repair.manage.vo.SelectVO;

/**
 * @author wuming
 * @description: 采购订单详情Controller
 */
@RestController
@RequestMapping("/purchase_order_detail")
public class PurchaseOrderDetailController {

    @Autowired
    private PurchaseOrderDetailService purchaseOrderDetailService;

    /**
     * 分页查询采购订单详情列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) PurchaseOrderDetail entity) {
        if (entity == null) {
            entity = new PurchaseOrderDetail();
        }
        return Result.success(purchaseOrderDetailService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有采购订单详情列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) PurchaseOrderDetail entity) {
        if (entity == null) {
            entity = new PurchaseOrderDetail();
        }
        List<PurchaseOrderDetail> list = purchaseOrderDetailService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询采购订单详情
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        PurchaseOrderDetail purchaseOrderDetail = purchaseOrderDetailService.getById(id);
        if (purchaseOrderDetail == null) {
            return Result.error("采购订单详情不存在");
        }
        return Result.success(purchaseOrderDetail);
    }

    /**
     * 新增采购订单详情
     */
    @PostMapping
    public Result save(@RequestBody PurchaseOrderDetail purchaseOrderDetail) {
        purchaseOrderDetail.setCreateTime(LocalDateTime.now());
        boolean success = purchaseOrderDetailService.save(purchaseOrderDetail);
        if (success) {
            return Result.success("新增成功", purchaseOrderDetail);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新采购订单详情
     */
    @PutMapping
    public Result update(@RequestBody PurchaseOrderDetail purchaseOrderDetail) {
        boolean success = purchaseOrderDetailService.updateById(purchaseOrderDetail);
        if (success) {
            return Result.success("更新成功", purchaseOrderDetail);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除采购订单详情
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = purchaseOrderDetailService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除采购订单详情
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = purchaseOrderDetailService.removeByIds(ids);
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
        List<SelectVO> list = purchaseOrderDetailService.listPurchaseOrder();
        return Result.success(list);
    }

    /**
     * 下拉数据接口：part
     */
    @GetMapping("/select/listPart")
    public Result listPart() {
        List<SelectVO> list = purchaseOrderDetailService.listPart();
        return Result.success(list);
    }

}
