package cn.plutonight.library.utils;

import io.jsonwebtoken.*;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Json Web Token 工具类
 * </p>
 *
 * @author LPH
 * @since 2020-10-18
 */
public class JwtUtil {

    // 过期时间---24 hour
    private static final int EXPIRATION_TIME = 60*60*24;
    // 秘钥
    private static final String SECRET = "023bdc63c3c5a4587*9ee6581508b9d03ad39a74fc0c9a9cce604749876c9646b";
    // 前缀
    public static final String TOKEN_PREFIX = "Bearer ";
    // 表头授权
    public static final String AUTHORIZATION = "Authorization";

    /**
     * 创建Token
     * @Method generateToken
     * @param userName
     * @param studentId
     * @param role
     * @Return String
     * @Author LPH
     * @Version 1.0
     */
    public static String generateToken(String userName, Long studentId, Integer role) {
        Calendar calendar = Calendar.getInstance();
        Date now =  calendar.getTime();
        // 设置签发时间
        calendar.setTime(new Date());
        // 设置过期时间
        // 添加秒钟
        calendar.add(Calendar.SECOND, EXPIRATION_TIME);
        Date time = calendar.getTime();
        Map<String, Object> map = new HashMap<>();
        // 构建Token
        map.put("userName", userName);
        map.put("id", studentId);
        map.put("role", role);
        String jwt = Jwts.builder()
                .setClaims(map)
                //签发时间
                .setIssuedAt(now)
                //过期时间
                .setExpiration(time)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        // 添加Bearer
        return TOKEN_PREFIX + jwt;
    }

    /**
     * 解密Token
     * @Method generateToken
     * @param token
     * @Return String
     * @Author LPH
     * @Version 1.0
     */
    public static Map<String, Object> validateToken(String token) {
        try {
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
//            String userName = body.get("userName").toString();
//            Long id = Long.valueOf(body.get("id").toString());
//            String role = body.get("role").toString();
            return body;
        } catch (Exception e) {
            throw e;
        }
    }
}

