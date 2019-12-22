/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.configuration;


import com.Service.DanhMucService;
import com.ServiceImpl.DanhMucServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.model.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.*;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc // (equivalent to <mvc:annotation-driven />   
//@Import(HibernateConfig.class) import vào bị lỗi :(
@EnableCaching
@ComponentScan(basePackages = "com")
public class HelloWorldConfig extends WebMvcConfigurerAdapter {
    @Bean
    @Scope("singleton")
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setContentType("UTF-8");
        return viewResolver;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/Resource/**").addResourceLocations("/Resource/");
        
        registry.addResourceHandler("/js/**")
                .addResourceLocations("/Resource/js/")
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
                .resourceChain(false)
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
        
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/Resource/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
    }

    //More configuration....

    /* Here we register the Hibernate4Module into an ObjectMapper, then set this custom-configured ObjectMapper
     * to the MessageConverter and return it to be added to the HttpMessageConverters of our application*/
    public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper mapper = new ObjectMapper();
        //Registering Hibernate4Module to support lazy objects
        mapper.registerModule(new Hibernate5Module());

        messageConverter.setObjectMapper(mapper);
        return messageConverter;

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //Here we add our custom-configured HttpMessageConverter
        converters.add(jacksonMessageConverter());
        super.configureMessageConverters(converters);
    }

    //More configuration....
    @Bean
    public CacheManager cacheManager() {
       SimpleCacheManager cacheManager = new SimpleCacheManager();
       Cache cache = new ConcurrentMapCache("mycache");
       cacheManager.setCaches(Arrays.asList(cache));
       return cacheManager;
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