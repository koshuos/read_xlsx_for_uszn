package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(	name = "vacations")
public class Vacations {

    public Vacations(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private AreaEducation areaEducation;

    @OneToOne(fetch = FetchType.EAGER)
    private Organizations organizations;

    @Temporal(TemporalType.DATE)
    private Date write_date;

    @Temporal(TemporalType.DATE)
    private Date clouse_date;

    @Temporal(TemporalType.DATE)
    private Date look_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date change_date;

    private Integer who_write;

    @Column(name = "comentar", length = 65535)
    private String comentar;

    private String posada;

}
