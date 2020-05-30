package com.miaojiaosan.material.service.dto;

import lombok.Data;

/**
 * 素材发布
 * @author miaojiaosan
 */
@Data
public class ReleaseDTO {

  private String title;

  private String content;

  private Integer categories;

  private Integer isOriginal;

  private Long accountId;

}
