package com.repair.manage.config;

import com.repair.manage.config.mybatis_plus.TableNameContextFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author wuming
 * @description: web配置类
 */
@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<TableNameContextFilter> tableNameContextFilter() {
        FilterRegistrationBean<TableNameContextFilter> registrationBean =
            new FilterRegistrationBean<>();

        registrationBean.setFilter(new TableNameContextFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE); // 最低优先级
        registrationBean.setName("tableNameContextFilter");
        return registrationBean;
    }

}
