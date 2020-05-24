package com.miaojiaosan.user.dal.dao;

import lombok.Data;

import java.util.Date;

/**
 * 用户DAO
 * @author miaojiaosan
 * @date 2020/05/05
 */
@Data
public class UserPersonDAO{
    /**
     * 主键
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 证件类型
     */
    private Integer certificatesType;

    /**
     * 证件号
     */
    private String certificates;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话
     */
    private String phone;
    /**
     * 版本
     */
    private Long version;
    /**
     *  操作人
     */
    private Long modify;
    /**
     * 操作时间
     */
    private Date modifyTime;
}