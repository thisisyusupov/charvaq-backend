package com.example.demo.entity;


import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "db_selling")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Selling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "list_numbe")
    private String listNumber;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "payment_type_id")
    private PaymentType paymentType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "unit_type_id")
    private UnitType unitType;

    @Column(name = "cost")
    private double cost;

    @Column(name = "amount")
    private double amount;

    @Column(name = "value")
    private double value;

    @Transient
    private int paymentTypeId;

    @Transient
    private int productTypeId;

    @Transient
    private int unitTypeId;

}
