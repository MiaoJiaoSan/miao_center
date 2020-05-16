package com.miaojiaosan.user.domain.data;

import lombok.Data;

/**
 * 角色
 * @author miaojiaosan
 * @date 2020/05/15
 */
@Data
public class Role {
    /**
     * 主键
     */
    private Long id;

    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
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
    private Long modifyTime;

}
