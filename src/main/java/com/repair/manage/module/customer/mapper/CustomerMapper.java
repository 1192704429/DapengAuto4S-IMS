package com.repair.manage.module.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.manage.module.customer.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuming
 * @description: 客户信息Mapper接口
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

    /**
     * 分页查询
     */
    List<Customer> pageQuery(@Param("entity") Customer entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") Customer entity);

    /**
     * 列表查询（不分页）
     */
    List<Customer> listQuery(@Param("entity") Customer entity);

    /**
     * 根据ID查询
     */
    Customer getById(@Param("id") Long id);

}
