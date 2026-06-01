package com.repair.manage.module.sys_user.controller;

import com.repair.manage.common.Result;
import com.repair.manage.module.sys_user.entity.SysUser;
import com.repair.manage.module.sys_user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author wuming
 * @description: 系统用户Controller
 */
@RestController
@RequestMapping("/sys_user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 分页查询系统用户列表
     */
    @PostMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestBody(required = false) SysUser entity) {
        if (entity == null) {
            entity = new SysUser();
        }
        return Result.success(sysUserService.pageQuery(current, size, entity));
    }

    /**
     * 查询所有系统用户列表
     */
    @PostMapping("/list")
    public Result list(@RequestBody(required = false) SysUser entity) {
        if (entity == null) {
            entity = new SysUser();
        }
        List<SysUser> list = sysUserService.listQuery(entity);
        return Result.success(list);
    }

    /**
     * 根据ID查询系统用户
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
        SysUser sysUser = sysUserService.getById(id);
        if (sysUser == null) {
            return Result.error("系统用户不存在");
        }
        return Result.success(sysUser);
    }

    /**
     * 新增系统用户
     */
    @PostMapping
    public Result save(@RequestBody SysUser sysUser) {
        sysUser.setCreateTime(LocalDateTime.now());
        boolean success = sysUserService.save(sysUser);
        if (success) {
            return Result.success("新增成功", sysUser);
        }
        return Result.error("新增失败");
    }

    /**
     * 更新系统用户
     */
    @PutMapping
    public Result update(@RequestBody SysUser sysUser) {
        boolean success = sysUserService.updateById(sysUser);
        if (success) {
            return Result.success("更新成功", sysUser);
        }
        return Result.error("更新失败");
    }

    /**
     * 根据ID删除系统用户
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean success = sysUserService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除系统用户
     */
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestBody List<Long> ids) {
        boolean success = sysUserService.removeByIds(ids);
        if (success) {
            return Result.success("批量删除成功");
        }
        return Result.error("批量删除失败");
    }

}
