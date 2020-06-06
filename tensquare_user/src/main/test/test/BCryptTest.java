package test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTest {

    @Test
    //加密
    public void fun1() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encode = encoder.encode("123456");

        System.out.println(encode);

        //$2a$10$gfTPvwOwMquAPsf1Lbdj1.3ekBko.4G0fQ6aBlbUeD1Ivb5LkoyeO
        //$2a$10$ax3tgWTtWRFgE5QfgBOoD.PBLrSz/JLTDgyqxfsej0tIiEVoryEvy
        //$2a$10$rD6HMDXH5RPFAVBb.4zU8.fpl.XhblsWw/CzjQCDSTONqj8o6s5wK

    }

    @Test
    //比对
    public void fun2() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


        boolean result = encoder.matches("123456", "$2a$10$ax3tgWTtWRFgE5QfgBOoD.PBLrSz/JLTDgyqxfsej0tIiEVoryEvy");


        System.out.println(result);
        //$2a$10$gfTPvwOwMquAPsf1Lbdj1.3ekBko.4G0fQ6aBlbUeD1Ivb5LkoyeO
        //$2a$10$ax3tgWTtWRFgE5QfgBOoD.PBLrSz/JLTDgyqxfsej0tIiEVoryEvy
        //$2a$10$rD6HMDXH5RPFAVBb.4zU8.fpl.XhblsWw/CzjQCDSTONqj8o6s5wK

    }
}
