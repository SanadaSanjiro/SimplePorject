package com.digdes.simple.service;

import com.digdes.simple.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PassEncoderTest extends BaseTest {
    @Autowired
    PassEncoder pe;

    @Test
    @DisplayName("Password encoding result exists")
    public void encode() {
        String password = "password";
        String result = pe.encode(password);
        Assertions.assertNotNull(result);
    }
}
