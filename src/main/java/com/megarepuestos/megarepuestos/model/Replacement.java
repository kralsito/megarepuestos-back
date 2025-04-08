package com.megarepuestos.megarepuestos.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Replacement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "typeReplacement_id")
    private TypeReplacement typeReplacement;

}
