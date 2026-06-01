package com.repair.manage.module.part.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.manage.module.part.entity.Part;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 配件信息Mapper接口
 */
@Mapper
public interface PartMapper extends BaseMapper<Part> {

    /**
     * 分页查询
     */
    List<Part> pageQuery(@Param("entity") Part entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") Part entity);

    /**
     * 列表查询（不分页）
     */
    List<Part> listQuery(@Param("entity") Part entity);

    /**
     * 根据ID查询
     */
    Part getById(@Param("id") Long id);

    /**
     * 下拉选项查询
     */
    /**
     * 下拉数据：part_category
     */
    @Select("select category_name as label, id as value from part_category")
    List<SelectVO> listPartCategory();

    /**
     * 下拉数据：supplier
     */
    @Select("select supplier_name as label, id as value from supplier")
    List<SelectVO> listSupplier();

}
