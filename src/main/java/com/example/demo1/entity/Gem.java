    package com.example.demo1.entity;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.util.List;

    @Entity
    @Table(name = "gems")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Gem {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "gem_id")
        private int gemId;

        @Column(name = "name")
        private String name;

        @Column(name = "opacity")
        private double opacity;

        @Column(name = "price")
        private double price;

        @Column(name = "weight")
        private double weight;

        @ManyToMany(mappedBy = "gems")
        private List<Necklace> necklaces;


    }