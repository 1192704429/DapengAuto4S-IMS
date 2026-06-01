package com.repair.manage.module.part_category.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.manage.module.part_category.entity.PartCategory;

import java.util.List;

/**
 * @author wuming
 * @description: 配件分类Service接口
 */
public interface PartCategoryService extends IService<PartCategory> {

    /**
     * 分页查询
     */
    IPage<PartCategory> pageQuery(Integer current, Integer size, PartCategory entity);

    /**
     * 列表查询（不分页）
     */
    List<PartCategory> listQuery(PartCategory entity);

    /**
     * 根据ID查询
     */
    PartCategory getById(Long id);

}
