package com.project.Game.list.dto;

import com.project.Game.list.entities.Game;
import com.project.Game.list.projections.GameMinProjection;


public class GameMinDTO {

    private Long id;
    private String title;
    private Integer year;
    private String imgUrl;
    private String shortDescription;
    
    public GameMinDTO() {
    }

    public GameMinDTO( Game Entity ) {
        id = Entity.getId();
        title = Entity.getTitle();
        year = Entity.getYear();
        imgUrl = Entity.getImgUrl();
        shortDescription = Entity.getShortDescription();
    }
    public GameMinDTO( GameMinProjection Projection ) {
        id = Projection.getId();
        title = Projection.getTitle();
        year = Projection.getYear();
        imgUrl = Projection.getImgUrl();
        shortDescription = Projection.getShortDescription();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }
}
