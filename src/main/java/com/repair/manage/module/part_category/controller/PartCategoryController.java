package com.repair.manage.module.part_category.controller;

import com.repair.manage.common.Result;
import com.repair.manage.module.part_category.entity.PartCategory;
import com.repair.manage.module.part_category.service.PartCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author wuming
 * @description: 配件分类Controller
 */
@RestController
@RequestMapping("/part_category")
public class PartCategoryController {

    @Autowired
    private PartCategoryService partCategoryService;

    /**
     * 分页查询配件分类列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) PartCategory entity) {
        if (entity == null) {
            entity = new PartCategory();
        }
        return Result.success(partCategoryService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有配件分类列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) PartCategory entity) {
        if (entity == null) {
            entity = new PartCategory();
        }
        List<PartCategory> list = partCategoryService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询配件分类
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        PartCategory partCategory = partCategoryService.getById(id);
        if (partCategory == null) {
            return Result.error("配件分类不存在");
        }
        return Result.success(partCategory);
    }

    /**
     * 新增配件分类
     */
    @PostMapping
    public Result save(@RequestBody PartCategory partCategory) {
        partCategory.setCreateTime(LocalDateTime.now());
        boolean success = partCategoryService.save(partCategory);
        if (success) {
            return Result.success("新增成功", partCategory);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新配件分类
     */
    @PutMapping
    public Result update(@RequestBody PartCategory partCategory) {
        boolean success = partCategoryService.updateById(partCategory);
        if (success) {
            return Result.success("更新成功", partCategory);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除配件分类
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = partCategoryService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除配件分类
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = partCategoryService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

}
