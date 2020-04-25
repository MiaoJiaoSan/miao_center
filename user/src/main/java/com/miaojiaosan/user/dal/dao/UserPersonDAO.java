package com.miaojiaosan.user.dal.dao;

import com.miaojiaosan.common.dal.dao.BaseDAO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserPersonDAO extends BaseDAO {
    private Long id;

    private String name;

    private Integer age;

    private Integer gender;

    private Date birthday;

    private Integer certificatesType;

    private String certificates;

    private String email;

    private String phone;
}