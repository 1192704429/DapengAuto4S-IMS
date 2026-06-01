package com.repair.manage.module.repair_detail.controller;

import com.repair.manage.common.Result;
import com.repair.manage.module.repair_detail.entity.RepairDetail;
import com.repair.manage.module.repair_detail.service.RepairDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import com.repair.manage.vo.SelectVO;

/**
 * @author wuming
 * @description: 维修详情Controller
 */
@RestController
@RequestMapping("/repair_detail")
public class RepairDetailController {

    @Autowired
    private RepairDetailService repairDetailService;

    /**
     * 分页查询维修详情列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) RepairDetail entity) {
        if (entity == null) {
            entity = new RepairDetail();
        }
        return Result.success(repairDetailService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有维修详情列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) RepairDetail entity) {
        if (entity == null) {
            entity = new RepairDetail();
        }
        List<RepairDetail> list = repairDetailService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询维修详情
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        RepairDetail repairDetail = repairDetailService.getById(id);
        if (repairDetail == null) {
            return Result.error("作业明细不存在");
        }
        return Result.success(repairDetail);
    }

    /**
     * 新增维修详情
     */
    @PostMapping
    public Result save(@RequestBody RepairDetail repairDetail) {
        repairDetail.setCreateTime(LocalDateTime.now());
        boolean success = repairDetailService.save(repairDetail);
        if (success) {
            return Result.success("新增成功", repairDetail);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新维修详情
     */
    @PutMapping
    public Result update(@RequestBody RepairDetail repairDetail) {
        boolean success = repairDetailService.updateById(repairDetail);
        if (success) {
            return Result.success("更新成功", repairDetail);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除维修详情
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = repairDetailService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除维修详情
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = repairDetailService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

    /**
     * 下拉数据接口：repair_order
     */
    @GetMapping("/select/listRepairOrder")
    public Result listRepairOrder() {
        List<SelectVO> list = repairDetailService.listRepairOrder();
        return Result.success(list);
    }

}
