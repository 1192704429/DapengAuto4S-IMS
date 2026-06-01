package com.repair.manage.module.repair_item_category.controller;

import com.repair.manage.common.Result;
import com.repair.manage.module.repair_item_category.entity.RepairItemCategory;
import com.repair.manage.module.repair_item_category.service.RepairItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author wuming
 * @description: 维修项目分类Controller
 */
@RestController
@RequestMapping("/repair_item_category")
public class RepairItemCategoryController {

    @Autowired
    private RepairItemCategoryService repairItemCategoryService;

    /**
     * 分页查询维修项目分类列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) RepairItemCategory entity) {
        if (entity == null) {
            entity = new RepairItemCategory();
        }
        return Result.success(repairItemCategoryService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有维修项目分类列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) RepairItemCategory entity) {
        if (entity == null) {
            entity = new RepairItemCategory();
        }
        List<RepairItemCategory> list = repairItemCategoryService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询维修项目分类
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        RepairItemCategory repairItemCategory = repairItemCategoryService.getById(id);
        if (repairItemCategory == null) {
            return Result.error("服务项目分类不存在");
        }
        return Result.success(repairItemCategory);
    }

    /**
     * 新增维修项目分类
     */
    @PostMapping
    public Result save(@RequestBody RepairItemCategory repairItemCategory) {
        repairItemCategory.setCreateTime(LocalDateTime.now());
        boolean success = repairItemCategoryService.save(repairItemCategory);
        if (success) {
            return Result.success("新增成功", repairItemCategory);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新维修项目分类
     */
    @PutMapping
    public Result update(@RequestBody RepairItemCategory repairItemCategory) {
        boolean success = repairItemCategoryService.updateById(repairItemCategory);
        if (success) {
            return Result.success("更新成功", repairItemCategory);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除维修项目分类
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = repairItemCategoryService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除维修项目分类
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = repairItemCategoryService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

}
