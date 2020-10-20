package cn.plutonight.library.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Spring Security 配置文件
 * 参考网址：
 * https://blog.csdn.net/XlxfyzsFdblj/article/details/82083443
 * https://blog.csdn.net/lizc_lizc/article/details/84059004
 * https://blog.csdn.net/XlxfyzsFdblj/article/details/82084183
 * https://blog.csdn.net/weixin_36451151/article/details/83868891
 * </p>
 *
 * @author LPH
 * @since 2020-10-16
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    @Autowired
    private UserDetailsService myUserDetailsService;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider())
//                .httpBasic()
//                .authenticationEntryPoint(((httpServletRequest, httpServletResponse, e) -> {
//                    httpServletResponse.setContentType("application/json;charset=utf-8");
//                    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
//                    httpServletResponse.setHeader("Cache-Control","no-cache");
//                    httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                    PrintWriter out = httpServletResponse.getWriter();
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("code", 403);
//                    map.put("msg", "未登录用户");
//                    out.write(objectMapper.writeValueAsString(map));
//                    out.flush();
//                    out.close();
//                }))
//                .and()
                .authorizeRequests()
                .antMatchers("/school/list").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .permitAll()
                .failureHandler(((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                    httpServletResponse.setHeader("Cache-Control","no-cache");
                    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                    PrintWriter out = httpServletResponse.getWriter();
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", 401);
                    if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
                        map.put("msg", "用户名或密码错误");
                    } else if (e instanceof DisabledException) {
                        map.put("msg", "用户已被禁用");
                    } else {
                        map.put("msg", "登录失败");
                    }
                    out.write(objectMapper.writeValueAsString(map));
                    out.flush();
                    out.close();
                }))
                .successHandler(((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                    httpServletResponse.setHeader("Cache-Control","no-cache");
                    Map<String, Object> map = new HashMap<>();
                    PrintWriter out = httpServletResponse.getWriter();
                    map.put("code", 200);
                    map.put("msg", "登录成功");
                    map.put("data", authentication.getPrincipal());
                    out.write(objectMapper.writeValueAsString(map));
                    out.flush();
                    out.close();
                }))
                .and()
                .exceptionHandling()
                .accessDeniedHandler(((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                    httpServletResponse.setHeader("Cache-Control","no-cache");
                    httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    PrintWriter out = httpServletResponse.getWriter();
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", 403);
                    map.put("msg", "权限不足");
                    out.write(objectMapper.writeValueAsString(map));
                    out.flush();
                    out.close();
                }))
                .and()
                .logout()
                .logoutSuccessHandler(((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                    httpServletResponse.setHeader("Cache-Control","no-cache");
                    Map<String, Object> map = new HashMap<>();
                    PrintWriter out = httpServletResponse.getWriter();
                    map.put("code", 200);
                    map.put("msg", "登出成功");
                    map.put("data", authentication);
                    out.write(objectMapper.writeValueAsString(map));
                    out.flush();
                    out.close();
                }))
                .permitAll();
        // 开启跨域访问
        // 开启模拟请求
        http.csrf().disable().cors();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //对默认的UserDetailsService进行覆盖
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(myPasswordEncoder);
        return authenticationProvider;
    }
}
