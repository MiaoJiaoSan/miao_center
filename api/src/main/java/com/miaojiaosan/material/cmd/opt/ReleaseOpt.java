package com.miaojiaosan.material.cmd.opt;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 素材发布
 * @author miaojiaosan
 */
@Data
public class ReleaseOpt {

  private Long id;

  @Length(min=1, max = 255)
  private String title;

  @NotNull
  private String content;

  @Min(1)
  @Max(128)
  private Integer categories;

  @Min(1)
  @Max(2)
  private Integer isOriginal;

  private Long accountId;
  @Min(1)
  @Max(2)
  private Integer state;
}
