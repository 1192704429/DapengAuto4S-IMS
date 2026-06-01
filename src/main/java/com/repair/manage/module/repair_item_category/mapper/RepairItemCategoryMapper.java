package com.repair.manage.module.repair_item_category.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.manage.module.repair_item_category.entity.RepairItemCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuming
 * @description: 维修项目分类Mapper接口
 */
@Mapper
public interface RepairItemCategoryMapper extends BaseMapper<RepairItemCategory> {

    /**
     * 分页查询
     */
    List<RepairItemCategory> pageQuery(@Param("entity") RepairItemCategory entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") RepairItemCategory entity);

    /**
     * 列表查询（不分页）
     */
    List<RepairItemCategory> listQuery(@Param("entity") RepairItemCategory entity);

    /**
     * 根据ID查询
     */
    RepairItemCategory getById(@Param("id") Long id);

}
