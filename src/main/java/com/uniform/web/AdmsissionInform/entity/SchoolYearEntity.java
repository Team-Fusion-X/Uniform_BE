package com.uniform.web.AdmsissionInform.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;


@Getter
@Setter
@Entity
@Table(name = "school_year")
public class SchoolYearEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_year_id")
    private int schoolYearId;

    @Column(name = "school_year")
    @NotNull
    private int schoolYear;

    @Column(name = "school_term")
    @NotNull
    private int schoolTerm;
}