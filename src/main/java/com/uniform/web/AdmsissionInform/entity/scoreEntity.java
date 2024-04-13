package com.uniform.web.AdmsissionInform.entity;

import com.uniform.web.member.entity.MemberEntity;
import com.uniform.web.member.repository.MemberRepository;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "score")
public class scoreEntity {
    @Id
    @Column(name = "score_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int score_id;

//    @OneToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "MemberEntity")
//    @Column(name = "user_id")
//    private String user_id = MemberEntity.



    @Column(name ="school_year_id")
    private int school_id;

    @Column(name ="subject_id")
    private String subject_id;

}
