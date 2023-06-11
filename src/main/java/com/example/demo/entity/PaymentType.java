package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "db_payment_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
