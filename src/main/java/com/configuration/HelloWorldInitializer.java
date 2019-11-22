
package com.configuration;

/**
 *
 * @author vital
 */
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
 
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
 
public class HelloWorldInitializer implements WebApplicationInitializer {
 
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        DispatcherServlet dispatcher;

        ctx.register(HelloWorldConfig.class);
        ctx.setServletContext(container);
        dispatcher = new DispatcherServlet(ctx);
        dispatcher.setThrowExceptionIfNoHandlerFound(true);
        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", dispatcher);
        
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
        
    }
 
}