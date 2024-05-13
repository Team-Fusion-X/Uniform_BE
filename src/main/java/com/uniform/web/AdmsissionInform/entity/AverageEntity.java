package com.uniform.web.AdmsissionInform.entity;

import com.uniform.web.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="average")
@Getter
@Setter
public class AverageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "average_id")
    private int averageId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private MemberEntity userId;


    @Column(name = "kemr_percentile")
    private double kemrPercentile;

    @Column(name = "kemr_degree")
    private double kemrDegree;

    @Column(name = "kems_degree")
    private double kemsDegree;

    @Column(name = "kemso_degree")
    private double kemsoDegree;

    @Column(name = "all_subject_degree")
    private double allSubjectDegree;

}
