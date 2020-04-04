package com.imagic97.ebook;

import com.auth0.jwt.JWT;
import org.junit.jupiter.api.Test;

/**
 * @author imagic
 */
public class TokenTest {
    @Test
    public void test(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwiZXhwIjoxNTg2Njc1Njk3LCJpYXQiOjE1ODU4MTE2OTd9.IyJJWfdVRfR9-o-9Z1gPxJh3CXjzPDICTdvyrTn4c6U";

        System.out.println(JWT.decode(token).getAudience().get(0));
    }
}
