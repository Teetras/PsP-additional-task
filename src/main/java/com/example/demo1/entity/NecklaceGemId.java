package com.example.demo1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class NecklaceGemId implements Serializable {
    @Column(name = "necklace_id")
    private int necklaceId;

    @Column(name = "gem_id")
    private int gemId;

    public NecklaceGemId(int necklaceId, int gemId) {
        this.gemId=gemId;
        this.necklaceId=necklaceId;
    }

    public NecklaceGemId() {

    }
}