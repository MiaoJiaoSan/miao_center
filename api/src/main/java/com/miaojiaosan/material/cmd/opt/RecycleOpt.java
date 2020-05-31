package com.miaojiaosan.material.cmd.opt;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author miaojiaosan
 */
@Data
public class RecycleOpt {

  @NotNull
  private Long id;

  private Long accountId;

}
