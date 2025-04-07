package com.megarepuestos.megarepuestos.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TypeReplacement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
}
