package com.repair.manage.module.repair_order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.manage.module.repair_order.entity.RepairOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 维修工单Mapper接口
 */
@Mapper
public interface RepairOrderMapper extends BaseMapper<RepairOrder> {

    /**
     * 分页查询
     */
    List<RepairOrder> pageQuery(@Param("entity") RepairOrder entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") RepairOrder entity);

    /**
     * 列表查询（不分页）
     */
    List<RepairOrder> listQuery(@Param("entity") RepairOrder entity);

    /**
     * 根据ID查询
     */
    RepairOrder getById(@Param("id") Long id);

    /**
     * 下拉选项查询
     */
    /**
     * 下拉数据：customer
     */
    @Select("select customer_name as label, id as value from customer")
    List<SelectVO> listCustomer();

    /**
     * 下拉数据：vehicle
     */
    @Select("select plate_number as label, id as value from vehicle")
    List<SelectVO> listVehicle();

    /**
     * 下拉数据：sys_user
     */
    @Select("select real_name as label, id as value from sys_user")
    List<SelectVO> listSysUser();

    /**
     * 查询工单数最少的工程师ID（仅统计维修中status=1的工单）
     */
    Long selectEngineerWithFewestOrders();

}
