package com.example.entity;

import com.example.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @ManyToMany
    @JoinTable(
            name = "neck_gem",
            joinColumns = @JoinColumn(name = "necklace_id"),
            inverseJoinColumns = @JoinColumn(name = "gem_id")
    )
    private List<Gem> gems;

    // Геттеры и сеттеры

    // Дополнительные методы и связи
}