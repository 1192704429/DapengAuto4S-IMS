package com.repair.manage.config.mybatis_plus;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author wuming
 * @description: 线程容器过滤器，用于清除ThreadLocal中的表名
 */
@WebFilter("/*")
public class TableNameContextFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(TableNameContextFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("TableNameContextFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } finally {
            // 确保每个请求完成后清理ThreadLocal
            TableNameContext.clear();
        }
    }

    @Override
    public void destroy() {
        log.info("TableNameContextFilter destroyed");
    }

}
