package com.repair.manage.module.sys_user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.repair.manage.module.sys_user.entity.SysUser;
import com.repair.manage.module.sys_user.mapper.SysUserMapper;
import com.repair.manage.module.sys_user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuming
 * @description: 系统用户Service实现类
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public IPage<SysUser> pageQuery(Integer current, Integer size, SysUser entity) {
        Long offset = (long) (current - 1) * size;
        Long limit = (long) size;
        List<SysUser> records = sysUserMapper.pageQuery(entity, offset, limit);
        Long total = sysUserMapper.countQuery(entity);

        Page<SysUser> page = new Page<>(current, size);
        page.setRecords(records);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<SysUser> listQuery(SysUser entity) {
        return sysUserMapper.listQuery(entity);
    }

    @Override
    public SysUser getById(Long id) {
        return sysUserMapper.getById(id);
    }

}
