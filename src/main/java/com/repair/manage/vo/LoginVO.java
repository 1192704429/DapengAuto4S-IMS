package com.repair.manage.vo;

import lombok.Data;

/**
 * @author wuming
 * @description: 登录请求体对象
 */
@Data
public class LoginVO {

    // 登录用户名
    private String username;

    // 登录密码
    private String password;

    // 验证码
    private String captcha;

    // 验证码Key
    private String captchaKey;

}
