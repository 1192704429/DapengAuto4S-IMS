package com.repair.manage.module.repair_part.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.manage.module.repair_part.entity.RepairPart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 维修配件使用Mapper接口
 */
@Mapper
public interface RepairPartMapper extends BaseMapper<RepairPart> {

    /**
     * 分页查询
     */
    List<RepairPart> pageQuery(@Param("entity") RepairPart entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") RepairPart entity);

    /**
     * 列表查询（不分页）
     */
    List<RepairPart> listQuery(@Param("entity") RepairPart entity);

    /**
     * 根据ID查询
     */
    RepairPart getById(@Param("id") Long id);

    /**
     * 按工单ID查询配件使用（含配件销售价）
     */
    List<RepairPart> listByRepairOrderIdWithPrice(@Param("repairOrderId") Long repairOrderId);

    /**
     * 下拉选项查询
     */
    /**
     * 下拉数据：repair_order
     */
    @Select("select order_no as label, id as value from repair_order")
    List<SelectVO> listRepairOrder();

    /**
     * 下拉数据：part
     */
    @Select("select part_name as label, id as value from part")
    List<SelectVO> listPart();

}
