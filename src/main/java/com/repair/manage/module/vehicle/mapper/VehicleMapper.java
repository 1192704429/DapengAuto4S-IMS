package com.repair.manage.module.vehicle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.manage.module.vehicle.entity.Vehicle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 车辆档案Mapper接口
 */
@Mapper
public interface VehicleMapper extends BaseMapper<Vehicle> {

    /**
     * 分页查询
     */
    List<Vehicle> pageQuery(@Param("entity") Vehicle entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") Vehicle entity);

    /**
     * 列表查询（不分页）
     */
    List<Vehicle> listQuery(@Param("entity") Vehicle entity);

    /**
     * 根据ID查询
     */
    Vehicle getById(@Param("id") Long id);

    /**
     * 下拉选项查询
     */
    /**
     * 下拉数据：customer
     */
    @Select("select customer_name as label, id as value from customer")
    List<SelectVO> listCustomer();

}
