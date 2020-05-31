package com.miaojiaosan.material.cmd.qry;

import lombok.Data;

import java.util.Date;

/**
 *
 * @author miaojiaosan
 */
@Data
public class MaterialListQry {

  private String keyword;

  private Integer categories;

  private Long accountId;

  private Integer isOriginal;

  private Integer state;

  private Date releaseDate;

  private Integer page;

  private Integer size;

}
