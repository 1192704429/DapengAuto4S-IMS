package com.repair.manage.module.customer.controller;

import com.repair.manage.common.Result;
import com.repair.manage.module.customer.entity.Customer;
import com.repair.manage.module.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author wuming
 * @description: 客户信息Controller
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 分页查询客户信息列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) Customer entity) {
        if (entity == null) {
            entity = new Customer();
        }
        return Result.success(customerService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有客户信息列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) Customer entity) {
        if (entity == null) {
            entity = new Customer();
        }
        List<Customer> list = customerService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询客户信息
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        Customer customer = customerService.getById(id);
        if (customer == null) {
            return Result.error("客户信息不存在");
        }
        return Result.success(customer);
    }

    /**
     * 新增客户信息
     */
    @PostMapping
    public Result save(@RequestBody Customer customer) {
        customer.setCreateTime(LocalDateTime.now());
        boolean success = customerService.save(customer);
        if (success) {
            return Result.success("新增成功", customer);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新客户信息
     */
    @PutMapping
    public Result update(@RequestBody Customer customer) {
        boolean success = customerService.updateById(customer);
        if (success) {
            return Result.success("更新成功", customer);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除客户信息
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = customerService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除客户信息
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = customerService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

}
