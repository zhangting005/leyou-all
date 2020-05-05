package com.leyou;

import com.leyou.auth.entity.UserInfo;
import com.leyou.auth.utils.JwtUtils;
import com.leyou.auth.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.PrivateKey;
import java.security.PublicKey;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {

    private static final String pubKeyPath = "D:\\GitHub_Code\\leyou-20200502-copy\\rsa\\rsa.pub";

    private static final String priKeyPath = "D:\\GitHub_Code\\leyou-20200502-copy\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU4ODQ4Njk2Nn0.Darkhl159TTlKIonP7AwgNuhXxsuOCTlho6yvhXZayhyIdzgk7hmAGGwZPMUnkyyRKAmG-YxkVXUtjfAe--zBZ1KZv6zwqdUxytmiWcvTQSzFb_TOH-0Hey4DUTCp0qCv-At14Z3GJxc5fINfXcbmSL7UTbYB0xAQEgaYq-rexE";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }

}