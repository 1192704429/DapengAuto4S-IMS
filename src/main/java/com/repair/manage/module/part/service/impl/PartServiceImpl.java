package com.repair.manage.module.part.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.manage.module.part.entity.Part;
import com.repair.manage.module.part.mapper.PartMapper;
import com.repair.manage.module.part.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 配件信息Service实现类
 */
@Service
public class PartServiceImpl extends ServiceImpl<PartMapper, Part> implements PartService {

    @Autowired
    private PartMapper partMapper;

    @Override
    public IPage<Part> pageQuery(Integer current, Integer size, Part entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<Part> records = partMapper.pageQuery(entity, offset, limit);
        Long total = partMapper.countQuery(entity);

        Page<Part> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<Part> listQuery(Part entity) {
        return partMapper.listQuery(entity);
    }

    @Override
    public Part getById(Long id) {
        return partMapper.getById(id);
    }

    /**
     * 下拉数据：part_category
     */
    @Override
    public List<SelectVO> listPartCategory() {
        return partMapper.listPartCategory();
    }

    /**
     * 下拉数据：supplier
     */
    @Override
    public List<SelectVO> listSupplier() {
        return partMapper.listSupplier();
    }

}
