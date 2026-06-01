package com.repair.manage.module.supplier.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.manage.module.supplier.entity.Supplier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuming
 * @description: 供应商管理Mapper接口
 */
@Mapper
public interface SupplierMapper extends BaseMapper<Supplier> {

    /**
     * 分页查询
     */
    List<Supplier> pageQuery(@Param("entity") Supplier entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") Supplier entity);

    /**
     * 列表查询（不分页）
     */
    List<Supplier> listQuery(@Param("entity") Supplier entity);

    /**
     * 根据ID查询
     */
    Supplier getById(@Param("id") Long id);

}
