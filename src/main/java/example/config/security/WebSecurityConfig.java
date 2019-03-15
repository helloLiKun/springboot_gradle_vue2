package example.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Administrator on 2019/1/13 0013.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyAuthenticationProvider myAuthenticationProvider;
    @Autowired
    AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
    @Autowired
    AuthenticationFailureHandlerImpl authenticationFailureHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(myAuthenticationProvider)
//               防止CSRF（Cross-site request forgery跨站请求伪造）的发生，限制了除了get以外的大多数方法。
                .csrf().disable()
                //添加权限信息
                .authorizeRequests()
                .antMatchers("/base/sys/*", "/lib/**", "/sys/**","/login-success","/login-faild").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .defaultSuccessUrl("/login-success")
//                .failureUrl("/login-faild")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .loginPage("/base/sys/login")
                .loginProcessingUrl("/sys/userSubmit")
                .passwordParameter("pwd")
                .usernameParameter("username")
                .permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/logout-success")
                .permitAll();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

}
