package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//@Entity
//@Table(name = "db_statistic")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "title")
    private String title;

//    @Column(name = "value")
    private double value;

//    @Column(name = "prefix")
    private String prefix;

//    @Column(name = "suffix")
    private String suffix;

//    @Column(name = "icon")
    private String icon;

}
