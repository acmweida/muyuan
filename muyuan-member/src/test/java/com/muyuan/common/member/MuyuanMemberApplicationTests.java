package com.muyuan.common.member;

import com.muyuan.common.member.domain.model.User;
import com.muyuan.common.member.infrastructure.persistence.dao.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootTest
class MuyuanMemberApplicationTests {

    @Autowired
    UserMapper mapper;

    @Test
    void contextLoads() {
        User user = new User();
        mapper.insert(user);
        System.out.println(user.getId());
    }

}
