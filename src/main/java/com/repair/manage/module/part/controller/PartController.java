package com.repair.manage.module.part.controller;

import com.repair.manage.common.Result;
import com.repair.manage.module.part.entity.Part;
import com.repair.manage.module.part.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import com.repair.manage.vo.SelectVO;

/**
 * @author wuming
 * @description: 配件信息Controller
 */
@RestController
@RequestMapping("/part")
public class PartController {

    @Autowired
    private PartService partService;

    /**
     * 分页查询配件信息列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) Part entity) {
        if (entity == null) {
            entity = new Part();
        }
        return Result.success(partService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有配件信息列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) Part entity) {
        if (entity == null) {
            entity = new Part();
        }
        List<Part> list = partService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询配件信息
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        Part part = partService.getById(id);
        if (part == null) {
            return Result.error("配件信息不存在");
        }
        return Result.success(part);
    }

    /**
     * 新增配件信息（库存默认0，由入库自动增加）
     */
    @PostMapping
    public Result save(@RequestBody Part part) {
        part.setCreateTime(LocalDateTime.now());
        if (part.getStock() == null) {
            part.setStock(0);
        }
        boolean success = partService.save(part);
        if (success) {
            return Result.success("新增成功", part);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新配件信息（不允许修改库存，库存由入库/使用自动变更）
     */
    @PutMapping
    public Result update(@RequestBody Part part) {
        Part existing = partService.getById(part.getId());
        if (existing != null) {
            part.setStock(existing.getStock()); // 保持库存不变
        }
        boolean success = partService.updateById(part);
        if (success) {
            return Result.success("更新成功", part);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除配件信息
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = partService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除配件信息
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = partService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

    /**
     * 下拉数据接口：part_category
     */
    @GetMapping("/select/listPartCategory")
    public Result listPartCategory() {
        List<SelectVO> list = partService.listPartCategory();
        return Result.success(list);
    }

    /**
     * 下拉数据接口：supplier
     */
    @GetMapping("/select/listSupplier")
    public Result listSupplier() {
        List<SelectVO> list = partService.listSupplier();
        return Result.success(list);
    }

}
