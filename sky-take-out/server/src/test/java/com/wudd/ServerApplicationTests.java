package com.wudd;

import com.wudd.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerApplicationTests {
    @Test
    void testJwtUtils() {
        String token = JwtUtils.generateToken(1, "wudd");
        System.out.println(token);

        Claims claims = JwtUtils.parseToken(token);
        System.out.println(claims);

        System.out.println("用户id为："+JwtUtils.getId(token));
        System.out.println("用户名为："+JwtUtils.getName(token));
    }


}
