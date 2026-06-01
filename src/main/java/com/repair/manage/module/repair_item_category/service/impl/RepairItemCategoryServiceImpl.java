package com.repair.manage.module.repair_item_category.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.manage.module.repair_item_category.entity.RepairItemCategory;
import com.repair.manage.module.repair_item_category.mapper.RepairItemCategoryMapper;
import com.repair.manage.module.repair_item_category.service.RepairItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuming
 * @description: 维修项目分类Service实现类
 */
@Service
public class RepairItemCategoryServiceImpl extends ServiceImpl<RepairItemCategoryMapper, RepairItemCategory> implements RepairItemCategoryService {

    @Autowired
    private RepairItemCategoryMapper repairItemCategoryMapper;

    @Override
    public IPage<RepairItemCategory> pageQuery(Integer current, Integer size, RepairItemCategory entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<RepairItemCategory> records = repairItemCategoryMapper.pageQuery(entity, offset, limit);
        Long total = repairItemCategoryMapper.countQuery(entity);

        Page<RepairItemCategory> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<RepairItemCategory> listQuery(RepairItemCategory entity) {
        return repairItemCategoryMapper.listQuery(entity);
    }

    @Override
    public RepairItemCategory getById(Long id) {
        return repairItemCategoryMapper.getById(id);
    }

}
