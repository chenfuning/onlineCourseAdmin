package com.ning.security;


import com.ning.security.authentication.MyAuthenctiationFailureHandler;
import com.ning.security.authentication.MyAuthenticationSuccessHandler;
import com.ning.security.authentication.MyLogoutSuccessHandler;
import com.ning.security.authentication.RestAuthenticationAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
   @Autowired
   private UserDetailsService userDetailsService;
   @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Autowired
    private RestAuthenticationAccessDeniedHandler restAuthenticationAccessDeniedHandler;
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
   @Bean
   public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       //配置自定义处理用户消息获取逻辑，密码加密逻辑
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭跨站请求
        http.csrf().disable();
        //'X-Frame-Options' to 'deny'
        http.headers().frameOptions().sameOrigin();
        //http所有请求都需要验证
        http.authorizeRequests()
                .antMatchers("/login.html",
                        "/testimages/**",
                        "/my/**",
                        "/xadmin/**",
                        "/treeable-lay/**",
                        "ztree/**",
                        "/static/**")
                .permitAll()
                .anyRequest()
                .authenticated();
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(myAuthenticationSuccessHandler)
                 .failureHandler(myAuthenctiationFailureHandler)
                //使用默认登出功能
                .and()
                //开启注销功能--默认/logout
                .logout()
                .permitAll()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(myLogoutSuccessHandler);

        //异常处理
        http.exceptionHandling().accessDeniedHandler(restAuthenticationAccessDeniedHandler);
    }
}
