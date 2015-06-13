package meguster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;


@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Bean(name = "beanNameViewResolver")
	public ViewResolver getBeanNameViewResolver() {
		BeanNameViewResolver resolver = new BeanNameViewResolver();
		resolver.setOrder(1);
		return resolver;
	}

}
