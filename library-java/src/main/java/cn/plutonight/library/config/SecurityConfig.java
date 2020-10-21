package cn.plutonight.library.config;

import cn.plutonight.library.core.JWTAuthenticationFilter;
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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
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
 * https://blog.csdn.net/xue317378914/article/details/106588435
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

    @Autowired
    private DataSource dataSource;

    /**
     *  URL 白名单
     */
    public static final String[] AUTH_WHITELIST = {
            "/login",
            "/user/login",
            "/user/admin/login",
            "/swagger-ui/**",
            "/school/list"
    };

    @Bean
    public JdbcTokenRepositoryImpl tokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    /**
     * <p>
     *    配置请求拦截
     * </p>
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                // 使用JWT，不需要csrf
                .csrf().disable()
                // 基于Token认证，不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .authenticationProvider(authenticationProvider())
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
                // 允许匿名访问白名单 其它所有请求需要身份认证
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and()
                // JWT认证
                .addFilter(new JWTAuthenticationFilter(authenticationManager()));
//                .formLogin()
//                .permitAll()
//                .failureHandler(((httpServletRequest, httpServletResponse, e) -> {
//                    httpServletResponse.setContentType("application/json;charset=utf-8");
//                    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
//                    httpServletResponse.setHeader("Cache-Control","no-cache");
//                    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
//                    PrintWriter out = httpServletResponse.getWriter();
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("code", 401);
//                    if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
//                        map.put("msg", "用户名或密码错误");
//                    } else if (e instanceof DisabledException) {
//                        map.put("msg", "用户已被禁用");
//                    } else {
//                        map.put("msg", "登录失败");
//                    }
//                    out.write(objectMapper.writeValueAsString(map));
//                    out.flush();
//                    out.close();
//                }))
//                .successHandler(((httpServletRequest, httpServletResponse, authentication) -> {
//                    httpServletResponse.setContentType("application/json;charset=utf-8");
//                    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
//                    httpServletResponse.setHeader("Cache-Control","no-cache");
//                    Map<String, Object> map = new HashMap<>();
//                    PrintWriter out = httpServletResponse.getWriter();
//                    map.put("code", 200);
//                    map.put("msg", "登录成功");
//                    map.put("data", authentication.getPrincipal());
//                    out.write(objectMapper.writeValueAsString(map));
//                    out.flush();
//                    out.close();
//                }))
//                .and()
//                .exceptionHandling()
//                .accessDeniedHandler(((httpServletRequest, httpServletResponse, e) -> {
//                    httpServletResponse.setContentType("application/json;charset=utf-8");
//                    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
//                    httpServletResponse.setHeader("Cache-Control","no-cache");
//                    httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                    PrintWriter out = httpServletResponse.getWriter();
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("code", 403);
//                    map.put("msg", "权限不足");
//                    out.write(objectMapper.writeValueAsString(map));
//                    out.flush();
//                    out.close();
//                }))
//                .and()
//                .logout()
//                .logoutSuccessHandler(((httpServletRequest, httpServletResponse, authentication) -> {
//                    httpServletResponse.setContentType("application/json;charset=utf-8");
//                    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
//                    httpServletResponse.setHeader("Cache-Control","no-cache");
//                    Map<String, Object> map = new HashMap<>();
//                    PrintWriter out = httpServletResponse.getWriter();
//                    map.put("code", 200);
//                    map.put("msg", "登出成功");
//                    map.put("data", authentication);
//                    out.write(objectMapper.writeValueAsString(map));
//                    out.flush();
//                    out.close();
//                }))
//                .permitAll();
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
