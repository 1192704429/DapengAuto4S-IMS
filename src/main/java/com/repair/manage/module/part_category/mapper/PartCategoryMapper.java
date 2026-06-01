package com.repair.manage.module.part_category.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.manage.module.part_category.entity.PartCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuming
 * @description: 配件分类Mapper接口
 */
@Mapper
public interface PartCategoryMapper extends BaseMapper<PartCategory> {

    /**
     * 分页查询
     */
    List<PartCategory> pageQuery(@Param("entity") PartCategory entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") PartCategory entity);

    /**
     * 列表查询（不分页）
     */
    List<PartCategory> listQuery(@Param("entity") PartCategory entity);

    /**
     * 根据ID查询
     */
    PartCategory getById(@Param("id") Long id);

}
