package com.repair.manage.module.vehicle.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.manage.module.vehicle.entity.Vehicle;
import com.repair.manage.module.vehicle.mapper.VehicleMapper;
import com.repair.manage.module.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 车辆档案Service实现类
 */
@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, Vehicle> implements VehicleService {

    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public IPage<Vehicle> pageQuery(Integer current, Integer size, Vehicle entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<Vehicle> records = vehicleMapper.pageQuery(entity, offset, limit);
        Long total = vehicleMapper.countQuery(entity);

        Page<Vehicle> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<Vehicle> listQuery(Vehicle entity) {
        return vehicleMapper.listQuery(entity);
    }

    @Override
    public Vehicle getById(Long id) {
        return vehicleMapper.getById(id);
    }

    /**
     * 下拉数据：customer
     */
    @Override
    public List<SelectVO> listCustomer() {
        return vehicleMapper.listCustomer();
    }

}
