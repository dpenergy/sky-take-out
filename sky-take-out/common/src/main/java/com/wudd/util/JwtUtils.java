package com.wudd.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    // 生成-校验-提取 jwt令牌的内容
    private static final String SECRET_STRING = "jekgjeijbeuijriaeruiheiroiw34fhdi343fjlajmd"; // 密钥（实际项目中应从配置文件或环境变量中读取，至少32字节）
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes()); // 生成密钥
    private static final long EXPIRATION_MS = 24 * 3600_000L;// Token 有效期：1小时（毫秒）


    // ==================== 生成 Token ====================
    /**
     * @param id 用户ID
     * @param name 用户名
     * @return return JWT Token 字符串
     */
    public static String generateToken(Integer id, String name) {
        return generateToken(id, name, null);
    }

    /**
     * @param id 用户ID
     * @param name 用户名
     * @param claims 额外的自定义声明
     * @return return JWT Token 字符串
     */
    public static String generateToken(Integer id, String name, Map<String, Object> claims) {
        Date now = new Date(); // 避免使用魔法变量，使变量语义清晰
        Date expiration = new Date(now.getTime() + EXPIRATION_MS);

        // 分发一个令牌必须携带签名，这是防令牌篡改的安全保障
        // 分发一个jwt令牌是建议明确：谁发下来的，发给谁的，什么时间发的，令牌到期时间，备注信息

        // 向构造器中填充信息
        JwtBuilder jwtBuilder = Jwts.builder() // 创建令牌构造器模板，填充数据
                .issuer("sky-take-out-sever") // 发布者
                .subject(String.valueOf(id)) // 发给的人，使用String.valueOf(id)确保将userID转换位字符串
                .issuedAt(now) // 分发时间
                .expiration(expiration) // 到期时间
                .claim("name", name) // 默认备注信息
                .signWith(SECRET_KEY); // 安全签名

        // 添加额外的自定备注信息
        if (claims != null && !claims.isEmpty()) {
            jwtBuilder.claims(claims);
        }

        return jwtBuilder.compact(); // 最后生成令牌
    }

    // ==================== 解析 Token ====================

    /**
     * 解析 Token，返回 Claims
     *
     * @param token JWT Token
     * @return Claims 声明集合
     */
    public static Claims parseToken(String token) {
        return Jwts.parser() // 解析器模板
                .verifyWith(SECRET_KEY) // 填充解析密钥
                .build() // 生成解析器
                .parseSignedClaims(token) // 解析token
                .getPayload(); // 获取有效载荷（获取令牌的备注信息）
    }

    /**
     * 从 Token 中提取用户ID
     */
    public static Long getId(String token) {
        return Long.parseLong(parseToken(token).getSubject());
    }

    /**
     * 从 Token 中提取用户名
     */
    public static String getName(String token) {
        return parseToken(token).get("name", String.class);
    }
}
