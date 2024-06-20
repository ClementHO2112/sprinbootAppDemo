package com.example.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@Component
public class ApiLogInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ApiLogInterceptor.class);

    private long time_start;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // Log request details before controller execution
        logger.info("Controller Logging Message:");
        logger.info("---------------------------------------------------");
        logger.info(String.format("  Session ID: %s", request.getSession().getId()));
        logger.info(String.format("    Protocol: %s", request.getProtocol()));
        logger.info(String.format("      Method: %s", request.getMethod()));
        logger.info(String.format("         Url: %s", request.getRequestURL()));
        logger.info(String.format("       Agent: %s", request.getHeader("User-Agent")));
        logger.info(String.format("      Remote: %s", request.getRemoteAddr()));
        logger.info(String.format("         URI: %s", request.getRequestURI()));

        logger.info(String.format(" %s Parameters :", request.getParameterMap().size()));
        for(Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()){
            logger.info("   " + entry.getKey() + ":" + Arrays.toString(entry.getValue()));
        }

        time_start = System.currentTimeMillis();

        // Continue with the request handling
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Log after controller method completion
        String nowTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        logger.info(String.format("info [%s] : API Finished Normally.", Thread.currentThread().getName()));
        logger.info(String.format("info [%s] : Time used: %s ms", Thread.currentThread().getName(), System.currentTimeMillis()-time_start));
        logger.info("");
        logger.info("---------------------------------------------------");
    }
}