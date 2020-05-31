package com.miaojiaosan.material.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MaterialVO {

  private Long id;

  private String title;

  private String content;

  private Integer categories;

  private Integer numberOfPeopleReading;

  private Integer fabulous;

  private Integer isOriginal;

  private Date releaseDate;
}
