package com.repair.manage.module.part.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.manage.module.part.entity.Part;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 配件信息Service接口
 */
public interface PartService extends IService<Part> {

    /**
     * 分页查询
     */
    IPage<Part> pageQuery(Integer current, Integer size, Part entity);

    /**
     * 列表查询（不分页）
     */
    List<Part> listQuery(Part entity);

    /**
     * 根据ID查询
     */
    Part getById(Long id);

    /**
     * 下拉数据：part_category
     */
    List<SelectVO> listPartCategory();

    /**
     * 下拉数据：supplier
     */
    List<SelectVO> listSupplier();

}
