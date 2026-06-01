package com.repair.manage.basic.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.repair.manage.common.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuming
 * @description: 验证码发送接口
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    // 缓存生产的验证码信息
    static Map<String, Map<String, Object>> CAPTCHA_CACHE_MAP = new HashMap<>();

    // 验证码图片的宽度
    public static final Integer CAPTCHA_WIDTH = 100;

    // 验证码图片的高度
    public static final Integer CAPTCHA_HEIGHT = 40;

    // 验证码的有效时间（毫秒），这里设置为1分钟
    public static final long EXPIRATION_TIME = 60 * 1000;

    /**
     * 生成图片验证码
     *
     * @param response
     * @param cacheKey 验证码Key
     * @return 验证码图片
     */
    @GetMapping("/getCaptcha/{cacheKey}")
    public void getCaptcha(HttpServletResponse response, @PathVariable("cacheKey") String cacheKey) {
        // 设置验证码的宽高
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(CAPTCHA_WIDTH, CAPTCHA_HEIGHT);
        // 验证码字符个数
        RandomGenerator randomGenerator = new RandomGenerator(4);
        lineCaptcha.setGenerator(randomGenerator);
        // 设置响应为验证码图片
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache"); // 设置响应头，告诉浏览器不要缓存此内容
        try {
            // 输出验证码图片
            lineCaptcha.write(response.getOutputStream());
            // 缓存验证码信息
            Map<String, Object> captchaInfo = new HashMap<>();
            captchaInfo.put("code", lineCaptcha.getCode());
            captchaInfo.put("timestamp", System.currentTimeMillis());
            CAPTCHA_CACHE_MAP.put(cacheKey, captchaInfo);
            // 关闭输出流
            response.getOutputStream().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
