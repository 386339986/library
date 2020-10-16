package cn.plutonight.library.config;

import cn.plutonight.library.utils.Utils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 自定义密码加密方法
 * </p>
 *
 * @author LPH
 * @since 2020-10-16
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        // 加密方法
        return Utils.StringToMD5_hex(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // System.out.println("rawPassword: " + rawPassword);
        // System.out.println("encodedPassword: " + encodedPassword);

        return encode(rawPassword).equals(encodedPassword);
    }

}
