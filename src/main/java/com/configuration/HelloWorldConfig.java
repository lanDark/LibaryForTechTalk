/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.configuration;


import com.DAOImpl.*;
import com.Service.DanhMucService;
import com.ServiceImpl.DanhMucServiceImpl;
import com.model.*;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc // (equivalent to <mvc:annotation-driven />
//@Import(HibernateConfig.class) import vào bị lỗi :(
@ComponentScan(basePackages = "com")
public class HelloWorldConfig extends WebMvcConfigurerAdapter {
    @Bean
    @Scope("singleton")
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/Resource/**").addResourceLocations("/Resource/");
    }
    @Bean
    public DanhMuc danhMuc(){
        return new DanhMuc();
    }
    @Bean
    public TheLoai theLoai(){
        return new TheLoai();
    }
    @Bean
    public DanhMucService danhMucService(){
        return new DanhMucServiceImpl();
    }
}