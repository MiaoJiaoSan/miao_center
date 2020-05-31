package com.miaojiaosan.material.service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MaterialDTO {

  private Long id;

  private String title;

  private String content;

  private String nickname;

  private Integer categories;

  private Integer numberOfPeopleReading;

  private Integer fabulous;

  private Integer isOriginal;

  private Date releaseDate;
}
