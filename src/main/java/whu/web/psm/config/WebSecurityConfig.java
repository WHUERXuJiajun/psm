/**
 * 
 */
package whu.web.psm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @description: 
 * @author	   : xsy
 * @date	   : 2020年5月26日
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * Http安全配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .rememberMe()
                .tokenValiditySeconds(1800)
                .and()
            .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/html/index/**","/html/common/**","/html/other/**",
                        "/json/**","/res/**","/views/**").permitAll()
                .antMatchers("/html/user/**").access("hasRole('manager') or hasRole('user')")
                .antMatchers("/html/admin/**").access("hasRole('manager')")
                .anyRequest()
                .authenticated();
    }

    /**
     * 密码加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        /**
         * BCryptPasswordEncoder：相同的密码明文每次生成的密文都不同，安全性更高
         */
        return new BCryptPasswordEncoder();
    }

}
