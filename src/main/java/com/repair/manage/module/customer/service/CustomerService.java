package com.repair.manage.module.customer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.manage.module.customer.entity.Customer;

import java.util.List;

/**
 * @author wuming
 * @description: 客户信息Service接口
 */
public interface CustomerService extends IService<Customer> {

    /**
     * 分页查询
     */
    IPage<Customer> pageQuery(Integer current, Integer size, Customer entity);

    /**
     * 列表查询（不分页）
     */
    List<Customer> listQuery(Customer entity);

    /**
     * 根据ID查询
     */
    Customer getById(Long id);

}
