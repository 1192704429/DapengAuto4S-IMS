package com.repair.manage.module.vehicle.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.manage.module.vehicle.entity.Vehicle;
import com.repair.manage.vo.SelectVO;

import java.util.List;

/**
 * @author wuming
 * @description: 车辆档案Service接口
 */
public interface VehicleService extends IService<Vehicle> {

    /**
     * 分页查询
     */
    IPage<Vehicle> pageQuery(Integer current, Integer size, Vehicle entity);

    /**
     * 列表查询（不分页）
     */
    List<Vehicle> listQuery(Vehicle entity);

    /**
     * 根据ID查询
     */
    Vehicle getById(Long id);

    /**
     * 下拉数据：customer
     */
    List<SelectVO> listCustomer();

}
