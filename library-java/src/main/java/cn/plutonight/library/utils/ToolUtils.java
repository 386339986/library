package cn.plutonight.library.utils;

import cn.plutonight.library.entity.Student;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;

/**
 * <p>
 *  工具类
 * </p>
 *
 * @author LPH
 * @since 2020-10-10
 */
public class ToolUtils {

    public static String timestampToDataString(Long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy 年 MM 月 dd 日 HH 时 mm 分 ss 秒");
        return sdf.format(new Date(timestamp));
    }

    public static Timestamp getTimeStamp() {
        return new Timestamp(new Date().getTime());
    }

    public static long getLongTimeStamp() {
        return new Date().getTime();
    }

    public static String getStringTimeStamp() {
        return getTimeStamp().toString();
    }

    public static String getToken(Student student) {
        String token = Jwts.builder()
                .setId(student.getId().toString())
                .setSubject(student.getName())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "library")
                .compact();
        return token;
    }

    /**
     * @param str 需要加密的字符串
     * @return StringToMD5_digest加密后的字符串
     */
    public static String StringToMD5_digest(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 按固定字符加密
     *
     * @param str 需要加密的字符串
     * @return StringToMD5加密后的字符串
     */
    public static String StringToMD5_hex(String str) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        try {
            byte[] btInput = str.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char s[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                s[k++] = hexDigits[byte0 >>> 4 & 0xf];
                s[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(s);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
