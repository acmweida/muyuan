package com.muyuan.user.domain.model.entity;

import com.muyuan.common.core.enums.PlatformType;
import lombok.Data;

@Data
public class Permission {

    @Data
    static public class Identify {

        private Long id;

        private String perms;

        public Identify(String perms) {
            this.perms = perms;
        }

        public Identify(Long id, String perms) {
            this.id = id;
            this.perms = perms;
        }
    }

    private Long id;

    private String business;

    private String module;

    private PlatformType platformType;

    private String resource;

    private String type;

    private String perms;

    private Integer status;
}
