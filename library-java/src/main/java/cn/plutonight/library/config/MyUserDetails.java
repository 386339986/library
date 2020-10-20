package cn.plutonight.library.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * <p>
 * UserDetails接口
 * </p>
 *
 * @author LPH
 * @since 2020-10-16
 */
@Component
public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    // 登录用户名
    private String username;
    // 登录密码
    private String password;
    // 账号状态：1 启用 2 停用 3 黑名单
    private Integer status;
    // 用户ID
    private Long id;
    // 用户所在学校id
    private Long schoolId;
    // 用户权限
    private Integer role;

    private Collection<? extends GrantedAuthority> authorities;

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getRole() {
        return role;
    }

    public void setSchoolId(Long id) {
        this.schoolId = id;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

//    public Integer getStatus() {
//        return this.status;
//    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    //账户是否过期,过期无法验证
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证
    @Override
    public boolean isAccountNonLocked() {
        return status != 2;
    }

    //指示是否已过期的用户的凭据(密码),过期的凭据防止认证
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否被禁用,禁用的用户不能身份验证
    @Override
    public boolean isEnabled() {
        return status != 3;
    }
}
