package com.miaojiaosan.material.dal.dao;

import lombok.Data;

import java.util.Date;

@Data
public class MaterialDAO {
    private Long id;

    private Long accountId;

    private Integer categories;

    private String title;

    private Integer numberOfPeopleReading;

    private Integer fabulous;

    private Integer isOriginal;

    private Date releaseDate;

    private Long version;

    private Long modify;

    private Date modifyTime;

    private String content;


}