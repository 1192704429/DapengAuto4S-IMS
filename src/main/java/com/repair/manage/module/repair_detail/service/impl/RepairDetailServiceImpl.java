package com.repair.manage.module.repair_detail.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.manage.module.repair_detail.entity.RepairDetail;
import com.repair.manage.module.repair_detail.mapper.RepairDetailMapper;
import com.repair.manage.module.repair_detail.service.RepairDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 维修详情Service实现类
 */
@Service
public class RepairDetailServiceImpl extends ServiceImpl<RepairDetailMapper, RepairDetail> implements RepairDetailService {

    @Autowired
    private RepairDetailMapper repairDetailMapper;

    @Override
    public IPage<RepairDetail> pageQuery(Integer current, Integer size, RepairDetail entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<RepairDetail> records = repairDetailMapper.pageQuery(entity, offset, limit);
        Long total = repairDetailMapper.countQuery(entity);

        Page<RepairDetail> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<RepairDetail> listQuery(RepairDetail entity) {
        return repairDetailMapper.listQuery(entity);
    }

    @Override
    public RepairDetail getById(Long id) {
        return repairDetailMapper.getById(id);
    }

    /**
     * 下拉数据：repair_order
     */
    @Override
    public List<SelectVO> listRepairOrder() {
        return repairDetailMapper.listRepairOrder();
    }

}
