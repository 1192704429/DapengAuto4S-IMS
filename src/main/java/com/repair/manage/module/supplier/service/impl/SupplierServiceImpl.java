package com.repair.manage.module.supplier.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.manage.module.supplier.entity.Supplier;
import com.repair.manage.module.supplier.mapper.SupplierMapper;
import com.repair.manage.module.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuming
 * @description: 供应商管理Service实现类
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public IPage<Supplier> pageQuery(Integer current, Integer size, Supplier entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<Supplier> records = supplierMapper.pageQuery(entity, offset, limit);
        Long total = supplierMapper.countQuery(entity);

        Page<Supplier> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<Supplier> listQuery(Supplier entity) {
        return supplierMapper.listQuery(entity);
    }

    @Override
    public Supplier getById(Long id) {
        return supplierMapper.getById(id);
    }

}
