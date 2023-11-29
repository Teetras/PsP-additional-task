package com.example.demo1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "necklace_gem")
public class NecklaceGem {
    @EmbeddedId
    private NecklaceGemId id;

    @ManyToOne
    @MapsId("necklaceId")
    @JoinColumn(name = "necklace_id")
    private Necklace necklace;

    @ManyToOne
    @MapsId("gemId")
    @JoinColumn(name = "gem_id")
    private Gem gem;

    @Column(name = "quantity")
    private int quantity;

    // Конструкторы, геттеры и сеттеры
}