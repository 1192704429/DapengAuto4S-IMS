package com.repair.manage.module.repair_detail.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.manage.module.repair_detail.entity.RepairDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 维修详情Mapper接口
 */
@Mapper
public interface RepairDetailMapper extends BaseMapper<RepairDetail> {

    /**
     * 分页查询
     */
    List<RepairDetail> pageQuery(@Param("entity") RepairDetail entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") RepairDetail entity);

    /**
     * 列表查询（不分页）
     */
    List<RepairDetail> listQuery(@Param("entity") RepairDetail entity);

    /**
     * 根据ID查询
     */
    RepairDetail getById(@Param("id") Long id);

    /**
     * 下拉选项查询
     */
    /**
     * 下拉数据：repair_order
     */
    @Select("select order_no as label, id as value from repair_order")
    List<SelectVO> listRepairOrder();

}
