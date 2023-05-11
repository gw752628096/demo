package com.spring.demo.utils;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.io.BaseEncoding;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Map;

public class AuthTokenUtils {
    private static Logger logger = LoggerFactory.getLogger(AuthTokenUtils.class);

    /**
     * token签名算法
     */
    private static final SignatureAlgorithm SIGN_ALGORITHM = SignatureAlgorithm.HS256;

    /**
     * 生成token
     *
     * @param payload    自定义payload
     * @param encodedKey 签名时使用的密钥（base64编码）
     * @return 生成的token
     */
    public static String generateToken(Map<String, Object> payload, String encodedKey) {
        try {
            byte[] decodedKey = BaseEncoding.base64().decode(encodedKey);
            Key originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

            JwtBuilder builder = Jwts.builder();
            builder.setClaims(payload);

            return builder.signWith(SIGN_ALGORITHM, originalKey).compact();
        } catch (Exception e) {
            logger.error("generateToken Exception, err", e);
        }
        return null;
    }

    /**
     * 校验并解析token，得到payload
     *
     * @param compactJws
     * @param encodedKey
     * @return
     */
    public static AuthToken parseToken(String compactJws, String encodedKey) {
        try {
            byte[] decodedKey = BaseEncoding.base64().decode(encodedKey);
            Key originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
            JwtParser parser = Jwts.parser();
            parser.setSigningKey(originalKey);
            Map claimsMap = parser.parseClaimsJws(compactJws).getBody();
            return BeanUtil.copyProperties(claimsMap, AuthToken.class);
        } catch (ExpiredJwtException e) {
            logger.warn("parseToken ExpiredJwtException, jwsToken=" + compactJws, e);
        } catch (Exception e) {
            logger.error("parseToken Exception, jwsToken= " + compactJws, e);
        }
        return null;
    }
}
