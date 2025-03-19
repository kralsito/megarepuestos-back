package com.megarepuestos.megarepuestos.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phoneNumber;
}
