package com.geostar.georobox.management.module.score.model;

import javax.persistence.*;

@Table(name = "RB_SCORE_TAG")
public class ScoreTagBean {
    @Id
    @Column(name = "TAG")
    private String tag;

    /**
     * @return TAG
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
}