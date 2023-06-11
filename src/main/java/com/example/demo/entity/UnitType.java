package com.example.demo.entity;


import javax.persistence.*;
import lombok.*;


@Entity
@Table(name = "db_unit_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
