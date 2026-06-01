package com.repair.manage.module.customer.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.manage.module.customer.entity.Customer;
import com.repair.manage.module.customer.mapper.CustomerMapper;
import com.repair.manage.module.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuming
 * @description: 客户信息Service实现类
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public IPage<Customer> pageQuery(Integer current, Integer size, Customer entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<Customer> records = customerMapper.pageQuery(entity, offset, limit);
        Long total = customerMapper.countQuery(entity);

        Page<Customer> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<Customer> listQuery(Customer entity) {
        return customerMapper.listQuery(entity);
    }

    @Override
    public Customer getById(Long id) {
        return customerMapper.getById(id);
    }

}
