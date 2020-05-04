package com.miaojiaosan.matereial.domain;

import com.miaojiaosan.matereial.domain.data.Author;
import com.miaojiaosan.matereial.domain.data.Category;
import com.miaojiaosan.matereial.domain.data.Comment;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author miaojiaosan
 * @date 2020/5/4
 */
@Data
public class MaterialDO {

  /**
   * 主键
   */
  private Long id;
  /**
   * 素材类别
   */
  private List<Category> categories;

  /**
   * 素材标题
   */
  private String title;

  /**
   * 阅读的人数
   */
  private Integer numberOfPeopleReading;

  /**
   * 评论
   */
  private List<Comment> comments;

  /**
   * 作者
   */
  private Author author;

  /**
   * 发布时间
   */
  private Date releaseDate;

}
