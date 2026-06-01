package com.repair.manage.util;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuming
 * @description: Jwt帮助类
 */
@Component
public class JwtUtil<T> {

    @Value("${secret-key:123456789}")
    private String secretKey;

    /**
     * 创建token
     *
     * @param t
     * @return
     */
    public String createToken(T t) {
        // 生成 JWT 令牌，使用自定义密钥进行签名
        String token = JWTUtil.createToken(beanToMap(t), secretKey.getBytes());
        return token;
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public boolean verifyToken(String token) {
        // 验证 JWT 令牌
        boolean isValid = JWTUtil.verify(token, secretKey.getBytes());
        return isValid;
    }

    /**
     * 解析token
     *
     * @param token
     * @param clazz
     * @return
     */
    public T parseToken(String token, Class<T> clazz) {

        JWT jwt = JWTUtil.parseToken(token);
        Map<String, Object> payload = jwt.getPayloads();
        return JSON.parseObject(JSON.toJSONString(payload), clazz);
    }


    public static Map<String, Object> beanToMap(Object bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean == null) {
            return map;
        }
        Class<?> clazz = bean.getClass();
        // 获取所有字段（包括私有字段）
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // 设置可访问私有字段
            try {
                String fieldName = field.getName();
                Object fieldValue = field.get(bean);
                map.put(fieldName, fieldValue.toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 立即过期token
     *
     * @param token
     * @return
     */
    public String expireTokenImmediately(String token) {
        // 解析原 Token
        JWT originalJwt = JWT.of(token);

        // 创建新的 JWT 构建器
        JWT newJwt = JWT.create().setKey(secretKey.getBytes()).setExpiresAt(new Date(0)); // 设置过期时间为过去

        // 复制原 Token 的所有 Payload 声明（除了可能存在的 exp）
        Map<String, Object> payloads = originalJwt.getPayloads();
        for (Map.Entry<String, Object> entry : payloads.entrySet()) {
            String key = entry.getKey();
            // 跳过原有的过期时间，使用新设置的
            if (!"exp".equalsIgnoreCase(key)) {
                newJwt.setPayload(key, entry.getValue());
            }
        }

        return newJwt.sign();
    }

}
