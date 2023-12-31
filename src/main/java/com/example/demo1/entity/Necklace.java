package com.example.demo1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "necklace")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Necklace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "necklace_id")
    private int necklaceId;

    @Column(name = "name")
    private String name;

    @Column(name = "totalPrice")
    private double totalPrice;

    @Column(name = "weight")
    private double weight;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "necklace",fetch = FetchType.EAGER)
    private List<NecklaceGem> necklaceGems;



    // Геттеры и сеттеры

    // Дополнительные методы и связи
}