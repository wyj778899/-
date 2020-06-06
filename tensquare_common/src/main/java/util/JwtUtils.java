package util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

//读取配置文件,加载jwt为前缀的配置
@ConfigurationProperties(prefix = "jwt",ignoreUnknownFields = true)
public class JwtUtils {
    //jwt.ttl
    private long ttl; //过期时间
    //jwt.secret
    private String secret; //密钥

    //生成jwt
    public String generateJwt(String id,String subject,String roles){
        long currentTime = System.currentTimeMillis();//取出当前的毫秒数

        long expTime  = currentTime+ttl*1000*60*60;

        String token = Jwts.builder()//返回jwt构建器
                .signWith(SignatureAlgorithm.HS256, secret)//指定加密方式|密钥(头)
                .setSubject(subject)//预定义载荷 => 存放用户名
                .setId(id) //预定义载荷=> 存放用户id
                .setIssuedAt(new Date()) //预定义载荷 => 存放签发token时间
                .setExpiration(new Date(expTime))//指定token过期时间 => 超过过期时间后,token将无法被解析.
                .claim("roles", roles)//自定义载荷,传入自定义载荷键值对
                .compact();//生成token

        return token;
    }

    //解析jwt
    public Claims parseJwt(String token){
        Claims claims = null;//获得解析的载荷结果
        try {
            claims = Jwts.parser()//返回解析器
                    .setSigningKey(secret)//设置解密密钥
                    .parseClaimsJws(token)//解析token的载荷部分
                    .getBody();
        } catch (Exception e) {
            return null;
        }
        return claims;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
