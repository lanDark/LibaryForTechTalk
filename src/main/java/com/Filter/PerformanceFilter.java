/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.apache.log4j.Logger;

/**
 *
 * @author vital
 */
@WebFilter(filterName = "myFilter", urlPatterns = {"/"})
public class PerformanceFilter implements Filter{
    private static final Logger logger = Logger.getLogger(PerformanceFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("IP:" + request.getRemoteAddr() +"connect to: "+request.getLocale());
        logger.info("getLocalName: "+request.getLocalName());
        logger.info("getRemoteAddr "+request.getRemoteAddr());
        logger.info("getRemoteHost "+request.getRemoteHost());
        logger.info("getScheme "+request.getScheme());
        logger.info("getServerName "+request.getServerName());
        chain.doFilter(request, response);
        
    }

    @Override
    public void destroy() {
    }
    
}
