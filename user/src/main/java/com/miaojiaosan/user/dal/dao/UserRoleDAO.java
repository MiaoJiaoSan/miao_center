package com.miaojiaosan.user.dal.dao;

import lombok.Data;

import java.util.Date;

@Data
public class UserRoleDAO {
    private Long id;

    private String name;

    private String code;

    private Long version;

    private Long modify;

    private Date modifyTime;

}