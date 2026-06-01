package com.repair.manage.module.supplier.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.manage.module.supplier.entity.Supplier;

import java.util.List;

/**
 * @author wuming
 * @description: 供应商管理Service接口
 */
public interface SupplierService extends IService<Supplier> {

    /**
     * 分页查询
     */
    IPage<Supplier> pageQuery(Integer current, Integer size, Supplier entity);

    /**
     * 列表查询（不分页）
     */
    List<Supplier> listQuery(Supplier entity);

    /**
     * 根据ID查询
     */
    Supplier getById(Long id);

}
