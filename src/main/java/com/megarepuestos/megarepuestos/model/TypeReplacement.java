package com.megarepuestos.megarepuestos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class TypeReplacement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "typeReplacement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Replacement> replacements;
}
