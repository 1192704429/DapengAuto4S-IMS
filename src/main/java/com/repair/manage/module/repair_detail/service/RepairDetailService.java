package com.repair.manage.module.repair_detail.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.manage.module.repair_detail.entity.RepairDetail;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 维修详情Service接口
 */
public interface RepairDetailService extends IService<RepairDetail> {

    /**
     * 分页查询
     */
    IPage<RepairDetail> pageQuery(Integer current, Integer size, RepairDetail entity);

    /**
     * 列表查询（不分页）
     */
    List<RepairDetail> listQuery(RepairDetail entity);

    /**
     * 根据ID查询
     */
    RepairDetail getById(Long id);

    /**
     * 下拉数据：repair_order
     */
    List<SelectVO> listRepairOrder();

}
