package example.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Created by lk on 2019/3/15.
 */
@Configuration
public class SpringSecurityConfig {
    @Bean
    ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource=new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("springsecurity/messages","org/springframework/security/messages");
        return messageSource;
    }

}
