package com.uniform.web.AdmsissionInform.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "universities_department")
@Getter
@Setter
public class analysisEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Uid")
    @Id
    private int uid;

    @Column(name = "university")
    private String university;

    @Column(name = "department")
    private String department;

    @Column(name = "fields")
    private String fields;

    @Column(name = "keyword")
    private String keyword;


}
