package test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;

public class JwtTest {

    @Test
    //生成JWT
    public void fun1() {
        String token = Jwts.builder()//返回jwt构建器
                .signWith(SignatureAlgorithm.HS256, "itcast")//指定加密方式|密钥(头)
                .setSubject("tom")//预定义载荷 => 存放用户名
                .setId("888") //预定义载荷=> 存放用户id
                .setIssuedAt(new Date()) //预定义载荷 => 存放签发token时间
                .compact();//生成token

        System.out.println(token);
        //eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b20iLCJqdGkiOiI4ODgiLCJpYXQiOjE1NjA5MzU1MTF9.Wped-NMg_jxGxsazK0DehgdQB2WeBVbidK9FzVgL5g8
    }

    @Test
    //解析JWT
    public void fun2() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b20iLCJqdGkiOiI4ODgiLCJpYXQiOjE1NjA5MzU1MTF9.Wped-NMg_jxGxsazK0DehgdQB2WeBVbidK9FzVgL5g8";


        Claims claims = Jwts.parser()//返回解析器
                .setSigningKey("itcast")//设置解密密钥
                .parseClaimsJws(token)//解析token的载荷部分
                .getBody();//获得解析的载荷结果

        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());

    }

    @Test
    //生成JWT =>  添加自定义载荷
    public void fun3() {
        String token = Jwts.builder()//返回jwt构建器
                .signWith(SignatureAlgorithm.HS256, "itcast")//指定加密方式|密钥(头)
                .setSubject("tom")//预定义载荷 => 存放用户名
                .setId("888") //预定义载荷=> 存放用户id
                .setIssuedAt(new Date()) //预定义载荷 => 存放签发token时间
                .claim("roles", "admin")//自定义载荷,传入自定义载荷键值对
                .compact();//生成token

        System.out.println(token);
        //eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b20iLCJqdGkiOiI4ODgiLCJpYXQiOjE1NjA5MzU5MzgsInJvbGVzIjoiYWRtaW4ifQ.xloeXzTNWLKiV2jrqZVS7PN_ALlEa9zKWpjshYFiNeM
    }


    @Test
    //解析JWT
    public void fun4() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b20iLCJqdGkiOiI4ODgiLCJpYXQiOjE1NjA5MzU5MzgsInJvbGVzIjoiYWRtaW4ifQ.xloeXzTNWLKiV2jrqZVS7PN_ALlEa9zKWpjshYFiNeM";


        Claims claims = Jwts.parser()//返回解析器
                .setSigningKey("itcast")//设置解密密钥
                .parseClaimsJws(token)//解析token的载荷部分
                .getBody();//获得解析的载荷结果

        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.get("roles"));

    }

    @Test
    //生成JWT =>  设置token过期时间
    public void fun5() {

        long currentTime = System.currentTimeMillis();//取出当前的毫秒数

        long expTime  = currentTime+1000*30;

        String token = Jwts.builder()//返回jwt构建器
                .signWith(SignatureAlgorithm.HS256, "itcast")//指定加密方式|密钥(头)
                .setSubject("tom")//预定义载荷 => 存放用户名
                .setId("888") //预定义载荷=> 存放用户id
                .setIssuedAt(new Date()) //预定义载荷 => 存放签发token时间
                .setExpiration(new Date(expTime))//指定token过期时间 => 超过过期时间后,token将无法被解析.
                .claim("roles", "admin")//自定义载荷,传入自定义载荷键值对
                .compact();//生成token

        System.out.println(token);
        //eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b20iLCJqdGkiOiI4ODgiLCJpYXQiOjE1NjA5MzYyNDgsImV4cCI6MTU2MDkzNjI3OCwicm9sZXMiOiJhZG1pbiJ9.orD1Dr4bEshL01nYkmY5yuE-NAX6ZKXWPLorBejAO2E
    }

    @Test
    //解析JWT
    public void fun6() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b20iLCJqdGkiOiI4ODgiLCJpYXQiOjE1NjA5MzYzMjAsImV4cCI6MTU2MDkzNjM1MCwicm9sZXMiOiJhZG1pbiJ9.NKtIJEObIYokl1o7VMSz0sZhm5oCHUrB2xeDzqe21LY";


        Claims claims = Jwts.parser()//返回解析器
                .setSigningKey("itcast")//设置解密密钥
                .parseClaimsJws(token)//解析token的载荷部分
                .getBody();//获得解析的载荷结果

        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.get("roles"));

    }
}
