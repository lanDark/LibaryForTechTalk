
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
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
 
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

        /**
         *
         * Thằng này dưới này tạo ra là để xử lý hash URL cho validating cache URL
         */
        FilterRegistration.Dynamic fr = container.addFilter("resourceUrlEncodingFilter",
                new ResourceUrlEncodingFilter());
        fr.setInitParameter("encoding", "UTF-8");
        fr.setInitParameter("forceEncoding", "true");
        fr.addMappingForUrlPatterns(null, true, "/*");
    }
 
}