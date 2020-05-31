package com.miaojiaosan.material.cmd.opt;

import lombok.Data;

/**
 * 素材发布
 * @author miaojiaosan
 */
@Data
public class ReleaseOpt {

  private Long id;

  private String title;

  private String content;

  private Integer categories;

  private Integer isOriginal;

  private Long accountId;

  private Integer state;
}
