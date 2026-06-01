package com.repair.manage.module.part_category.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.manage.module.part_category.entity.PartCategory;
import com.repair.manage.module.part_category.mapper.PartCategoryMapper;
import com.repair.manage.module.part_category.service.PartCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuming
 * @description: 配件分类Service实现类
 */
@Service
public class PartCategoryServiceImpl extends ServiceImpl<PartCategoryMapper, PartCategory> implements PartCategoryService {

    @Autowired
    private PartCategoryMapper partCategoryMapper;

    @Override
    public IPage<PartCategory> pageQuery(Integer current, Integer size, PartCategory entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<PartCategory> records = partCategoryMapper.pageQuery(entity, offset, limit);
        Long total = partCategoryMapper.countQuery(entity);

        Page<PartCategory> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<PartCategory> listQuery(PartCategory entity) {
        return partCategoryMapper.listQuery(entity);
    }

    @Override
    public PartCategory getById(Long id) {
        return partCategoryMapper.getById(id);
    }

}
