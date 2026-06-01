package com.repair.manage.module.sys_user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.repair.manage.module.sys_user.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuming
 * @description: 系统用户Mapper接口
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 分页查询
     */
    List<SysUser> pageQuery(@Param("entity") SysUser entity,
                             @Param("offset") Long offset,
                             @Param("limit") Long limit);

    /**
     * 查询总数
     */
    Long countQuery(@Param("entity") SysUser entity);

    /**
     * 列表查询（不分页）
     */
    List<SysUser> listQuery(@Param("entity") SysUser entity);

    /**
     * 根据ID查询
     */
    SysUser getById(@Param("id") Long id);

}
