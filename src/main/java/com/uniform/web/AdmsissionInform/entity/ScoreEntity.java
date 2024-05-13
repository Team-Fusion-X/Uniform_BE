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
    private int scoreId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private MemberEntity userId;

    @ManyToOne
    @JoinColumn(name = "school_year_id")
    private SchoolYearEntity schoolYearId;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectsEntity subjectId;

    @Column(name = "credit")
    private int Credit;

    @Column(name = "raw_score")
    private double rawScore;

    @Column(name = "subject_average")
    private double subjectAverage;

    @Column(name = "standard_deviation")
    private double standardDeviation;

    @Column(name = "headcount")
    private int headcount;

    @Column(name = "ranking")
    private int ranking;


}
