package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "db_item")
public class Item {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private TitleLast title;

    @ManyToOne
    @JoinColumn(name = "desc_id")
    private Desc desc;

    @Column(name = "code")
    private String code;

    @Column(name = "status")
    private String status;

    private int titleId;

    private int descId;

}
