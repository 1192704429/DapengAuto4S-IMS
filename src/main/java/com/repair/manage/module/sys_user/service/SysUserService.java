package com.repair.manage.module.sys_user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.repair.manage.module.sys_user.entity.SysUser;

import java.util.List;

/**
 * @author wuming
 * @description: 系统用户Service接口
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 分页查询
     */
    IPage<SysUser> pageQuery(Integer current, Integer size, SysUser entity);

    /**
     * 列表查询（不分页）
     */
    List<SysUser> listQuery(SysUser entity);

    /**
     * 根据ID查询
     */
    SysUser getById(Long id);

}
