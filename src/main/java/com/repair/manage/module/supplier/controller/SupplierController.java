package com.repair.manage.module.supplier.controller;

import com.repair.manage.common.Result;
import com.repair.manage.module.supplier.entity.Supplier;
import com.repair.manage.module.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author wuming
 * @description: 供应商管理Controller
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    /**
     * 分页查询供应商管理列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) Supplier entity) {
        if (entity == null) {
            entity = new Supplier();
        }
        return Result.success(supplierService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有供应商管理列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) Supplier entity) {
        if (entity == null) {
            entity = new Supplier();
        }
        List<Supplier> list = supplierService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询供应商管理
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        Supplier supplier = supplierService.getById(id);
        if (supplier == null) {
            return Result.error("供应商管理不存在");
        }
        return Result.success(supplier);
    }

    /**
     * 新增供应商管理
     */
    @PostMapping
    public Result save(@RequestBody Supplier supplier) {
        supplier.setCreateTime(LocalDateTime.now());
        boolean success = supplierService.save(supplier);
        if (success) {
            return Result.success("新增成功", supplier);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新供应商管理
     */
    @PutMapping
    public Result update(@RequestBody Supplier supplier) {
        boolean success = supplierService.updateById(supplier);
        if (success) {
            return Result.success("更新成功", supplier);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除供应商管理
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = supplierService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除供应商管理
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = supplierService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

}
