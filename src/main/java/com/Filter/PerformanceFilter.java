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
import javax.servlet.http.HttpServletRequest;
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
        try{
            HttpServletRequest req = (HttpServletRequest) request;
            logger.info("IP:" + request.getRemoteAddr() +"connect to: "+req.getPathInfo());

            chain.doFilter(request, response);
            
        }catch(Exception e){
            
        }
        
    }

    @Override
    public void destroy() {
    }
    
}
