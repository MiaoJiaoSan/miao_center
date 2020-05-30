package com.miaojiaosan.material.cmd.opt;

import lombok.Data;

/**
 * 素材发布
 * @author miaojiaosan
 */
@Data
public class ReleaseOpt {


  private String title;

  private String content;

  private Integer categories;

  private Integer isOriginal;

  private Long accountId;
}
