package com.repair.manage.module.repair_part.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.manage.module.part.entity.Part;
import com.repair.manage.module.part.service.PartService;
import com.repair.manage.module.repair_part.entity.RepairPart;
import com.repair.manage.module.repair_part.mapper.RepairPartMapper;
import com.repair.manage.module.repair_part.service.RepairPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 维修配件使用Service实现类
 */
@Service
public class RepairPartServiceImpl extends ServiceImpl<RepairPartMapper, RepairPart> implements RepairPartService {

    @Autowired
    private RepairPartMapper repairPartMapper;
    @Autowired
    private PartService partService;

    @Override
    public IPage<RepairPart> pageQuery(Integer current, Integer size, RepairPart entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<RepairPart> records = repairPartMapper.pageQuery(entity, offset, limit);
        Long total = repairPartMapper.countQuery(entity);

        Page<RepairPart> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<RepairPart> listQuery(RepairPart entity) {
        return repairPartMapper.listQuery(entity);
    }

    @Override
    public RepairPart getById(Long id) {
        return repairPartMapper.getById(id);
    }

    /**
     * 下拉数据：repair_order
     */
    @Override
    public List<SelectVO> listRepairOrder() {
        return repairPartMapper.listRepairOrder();
    }

    /**
     * 下拉数据：part
     */
    @Override
    public List<SelectVO> listPart() {
        return repairPartMapper.listPart();
    }

    /**
     * 保存维修配件使用并扣减库存
     */
    @Override
    public void saveWithStockDeduction(RepairPart repairPart) {
        if (repairPart.getPartId() == null || repairPart.getUseQty() == null || repairPart.getUseQty() <= 0) {
            throw new RuntimeException("配件或使用数量无效");
        }
        Part part = partService.getById(repairPart.getPartId());
        if (part == null) {
            throw new RuntimeException("配件不存在");
        }
        int currentStock = part.getStock() != null ? part.getStock() : 0;
        int useQty = repairPart.getUseQty();
        if (currentStock < useQty) {
            throw new RuntimeException("配件库存不足，当前库存：" + currentStock + "，需要：" + useQty);
        }
        this.save(repairPart);
        part.setStock(currentStock - useQty);
        partService.updateById(part);
    }

    /**
     * 删除维修配件使用并恢复库存
     */
    @Override
    public void removeWithStockRestore(Long id) {
        RepairPart rp = this.getById(id);
        if (rp == null) return;
        if (rp.getPartId() != null && rp.getUseQty() != null && rp.getUseQty() > 0) {
            Part part = partService.getById(rp.getPartId());
            if (part != null) {
                int currentStock = part.getStock() != null ? part.getStock() : 0;
                part.setStock(currentStock + rp.getUseQty());
                partService.updateById(part);
            }
        }
        this.removeById(id);
    }

}
