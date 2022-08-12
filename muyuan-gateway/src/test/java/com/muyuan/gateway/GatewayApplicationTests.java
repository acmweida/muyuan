package com.muyuan.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@SpringBootTest
class GatewayApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(new BCryptPasswordEncoder().encode("4+1NGoDdKIndl"));
    }

    @Test
    public void testFinally() {
        try {
          if (1 == 1) {
              return;
          }
        } finally {
            System.out.println(1);
        }

        try {

        }finally {
            System.out.println(2);
        }
    }

}
