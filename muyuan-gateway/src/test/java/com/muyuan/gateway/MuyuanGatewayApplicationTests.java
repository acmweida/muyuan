package com.muyuan.gateway;

import org.junit.jupiter.api.Test;

//@SpringBootTest
class MuyuanGatewayApplicationTests {

    @Test
    void contextLoads() {

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
