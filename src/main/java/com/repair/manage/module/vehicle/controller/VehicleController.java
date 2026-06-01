package com.repair.manage.module.vehicle.controller;

import com.repair.manage.common.Result;
import com.repair.manage.module.vehicle.entity.Vehicle;
import com.repair.manage.module.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import com.repair.manage.vo.SelectVO;

/**
 * @author wuming
 * @description: 车辆档案Controller
 */
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    /**
     * 分页查询车辆档案列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) Vehicle entity) {
        if (entity == null) {
            entity = new Vehicle();
        }
        return Result.success(vehicleService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有车辆档案列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) Vehicle entity) {
        if (entity == null) {
            entity = new Vehicle();
        }
        List<Vehicle> list = vehicleService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据客户ID查询该客户关联的车辆列表（仅返回该客户的车辆，保证数据隔离）
     */
    @GetMapping("/list-by-customer/{customerId}")
    public Result listByCustomerId(@PathVariable Long customerId) {
        if (customerId == null) {
            return Result.success(java.util.Collections.emptyList());
        }
        Vehicle query = new Vehicle();
        query.setCustomerId(customerId);
        List<Vehicle> list = vehicleService.listQuery(query);
        return Result.success(list);
    }

    /**
     * 根据ID查询车辆档案
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.getById(id);
        if (vehicle == null) {
            return Result.error("车辆档案不存在");
        }
        return Result.success(vehicle);
    }

    /**
     * 新增车辆档案
     */
    @PostMapping
    public Result save(@RequestBody Vehicle vehicle) {
        vehicle.setCreateTime(LocalDateTime.now());
        boolean success = vehicleService.save(vehicle);
        if (success) {
            return Result.success("新增成功", vehicle);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新车辆档案
     */
    @PutMapping
    public Result update(@RequestBody Vehicle vehicle) {
        boolean success = vehicleService.updateById(vehicle);
        if (success) {
            return Result.success("更新成功", vehicle);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除车辆档案
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = vehicleService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除车辆档案
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = vehicleService.removeByIds(ids);
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
        List<SelectVO> list = vehicleService.listCustomer();
        return Result.success(list);
    }

}
