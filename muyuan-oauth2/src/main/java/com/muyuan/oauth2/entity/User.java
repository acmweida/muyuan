package com.muyuan.oauth2.entity;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "passwd")
    private String passwd;
}
