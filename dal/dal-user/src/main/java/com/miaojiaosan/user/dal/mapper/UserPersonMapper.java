package com.miaojiaosan.user.dal.mapper;

import com.miaojiaosan.user.dal.dao.UserPersonDAO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户DAO
 * @author miaojiaosan
 * @date 2020/05/05
 */
@Mapper
public interface UserPersonMapper {
    /**
     * 删除
     * @param id 主键
     * @return 行
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增
     * @param record {@link UserPersonDAO}
     * @return 行
     */
    int insert(UserPersonDAO record);

    /**
     * 用户信息
     * @param id 主键
     * @return {@link UserPersonDAO}
     */
    UserPersonDAO selectByPrimaryKey(Long id);

    /**
     * 用户信息列表
     * @return {@link UserPersonDAO}
     */
    List<UserPersonDAO> selectAll();

    /**
     * 更新
     * @param record {@link UserPersonDAO}
     * @return 行
     */
    int updateByPrimaryKey(UserPersonDAO record);
}