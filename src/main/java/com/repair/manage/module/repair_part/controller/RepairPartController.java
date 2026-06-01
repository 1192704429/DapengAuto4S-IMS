package com.repair.manage.module.repair_part.controller;

import com.repair.manage.common.Result;
import com.repair.manage.module.repair_part.entity.RepairPart;
import com.repair.manage.module.repair_part.service.RepairPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import com.repair.manage.vo.SelectVO;

/**
 * @author wuming
 * @description: 维修配件使用Controller
 */
@RestController
@RequestMapping("/repair_part")
public class RepairPartController {

    @Autowired
    private RepairPartService repairPartService;

    /**
     * 分页查询维修配件使用列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) RepairPart entity) {
        if (entity == null) {
            entity = new RepairPart();
        }
        return Result.success(repairPartService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有维修配件使用列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) RepairPart entity) {
        if (entity == null) {
            entity = new RepairPart();
        }
        List<RepairPart> list = repairPartService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询维修配件使用
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        RepairPart repairPart = repairPartService.getById(id);
        if (repairPart == null) {
            return Result.error("作业配件使用记录不存在");
        }
        return Result.success(repairPart);
    }

    /**
     * 新增维修配件使用（扣减配件库存）
     */
    @PostMapping
    public Result save(@RequestBody RepairPart repairPart) {
        repairPart.setCreateTime(LocalDateTime.now());
        try {
            repairPartService.saveWithStockDeduction(repairPart);
            return Result.success("新增成功", repairPart);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 更新维修配件使用
     */
    @PutMapping
    public Result update(@RequestBody RepairPart repairPart) {
        boolean success = repairPartService.updateById(repairPart);
        if (success) {
            return Result.success("更新成功", repairPart);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除维修配件使用（恢复配件库存）
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        repairPartService.removeWithStockRestore(id);
        return Result.success("删除成功");
    }

    /**
     * 批量删除维修配件使用（恢复配件库存）
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        if (ids != null) {
            for (Long id : ids) {
                repairPartService.removeWithStockRestore(id);
            }
        }
        return Result.success("批量删除成功");
    }

    /**
     * 下拉数据接口：repair_order
     */
    @GetMapping("/select/listRepairOrder")
    public Result listRepairOrder() {
        List<SelectVO> list = repairPartService.listRepairOrder();
        return Result.success(list);
    }

    /**
     * 下拉数据接口：part
     */
    @GetMapping("/select/listPart")
    public Result listPart() {
        List<SelectVO> list = repairPartService.listPart();
        return Result.success(list);
    }

}
