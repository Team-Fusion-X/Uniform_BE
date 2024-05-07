package com.uniform.web.AdmsissionInform.entity;

import com.uniform.web.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "score")
public class ScoreEntity {
    @Id
    @Column(name = "score_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int score_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private MemberEntity userId;

    @ManyToOne
    @JoinColumn(name = "school_year_id")
    private SchoolYearEntity school_year_id;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectsEntity subject_id;

    @Column(name = "Credit")
    private int Credit;

    @Column(name = "raw_score")
    private double raw_score;

    @Column(name = "subject_average")
    private double subject_average;

    @Column(name = "standard_deviation")
    private double standard_deviation;

    @Column(name = "headcount")
    private int headcount;

    @Column(name = "ranking")
    private int ranking;


}
