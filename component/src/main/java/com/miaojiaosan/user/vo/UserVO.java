package com.miaojiaosan.user.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author miaojiaosan
 * @date: 2020/4/25
 */
@Data
public class UserVO {

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
