package com.uniform.web.AdmsissionInform.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.swing.plaf.synth.Region;

@Entity
@Table(name = "school", schema = "uniformDB")
@Getter
@Setter
public class SchoolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private int schoolId;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private RegionEntity region_id;

    @Column(name = "school_name", length = 21, nullable = false)
    private String schoolName;

}
