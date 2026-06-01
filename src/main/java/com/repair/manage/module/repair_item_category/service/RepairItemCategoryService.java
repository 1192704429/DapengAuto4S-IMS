package com.repair.manage.module.repair_item_category.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.manage.module.repair_item_category.entity.RepairItemCategory;

import java.util.List;

/**
 * @author wuming
 * @description: 维修项目分类Service接口
 */
public interface RepairItemCategoryService extends IService<RepairItemCategory> {

    /**
     * 分页查询
     */
    IPage<RepairItemCategory> pageQuery(Integer current, Integer size, RepairItemCategory entity);

    /**
     * 列表查询（不分页）
     */
    List<RepairItemCategory> listQuery(RepairItemCategory entity);

    /**
     * 根据ID查询
     */
    RepairItemCategory getById(Long id);

}
