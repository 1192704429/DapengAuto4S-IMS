package com.repair.manage.module.repair_part.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.manage.module.repair_part.entity.RepairPart;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 维修配件使用Service接口
 */
public interface RepairPartService extends IService<RepairPart> {

    /**
     * 分页查询
     */
    IPage<RepairPart> pageQuery(Integer current, Integer size, RepairPart entity);

    /**
     * 列表查询（不分页）
     */
    List<RepairPart> listQuery(RepairPart entity);

    /**
     * 根据ID查询
     */
    RepairPart getById(Long id);

    /**
     * 下拉数据：repair_order
     */
    List<SelectVO> listRepairOrder();

    /**
     * 下拉数据：part
     */
    List<SelectVO> listPart();

    /**
     * 保存维修配件使用并扣减库存（库存不足时抛出运行时异常）
     */
    void saveWithStockDeduction(RepairPart repairPart);

    /**
     * 删除维修配件使用并恢复库存
     */
    void removeWithStockRestore(Long id);

}
