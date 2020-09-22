package ir.seefa.tutorial.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 09 Sep 2020 T 23:54:03
 */
// 1. Read ir.seefa.tutorial.spring-core-tutorial and ir.seefa.tutorial.spring-jdbc-mvc-tutorial codes before starting this project because primary annotations and mvc logic explained there
@Configuration
@ComponentScan(basePackages = "ir.seefa.tutorial.spring")
@EnableWebMvc
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
