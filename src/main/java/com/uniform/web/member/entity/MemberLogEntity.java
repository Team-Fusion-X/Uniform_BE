package com.uniform.web.member.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "member_log_table")
public class MemberLogEntity {

    @Id
    @Column(name = "user_id", nullable = false, length = 20)
    private String userId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", unique = true)
    private MemberEntity memberEntity;

    @Column(name = "last_login_date", nullable = false)
    private LocalDateTime lastLoginDate;

    @Column(name = "manager", nullable = false)
    private boolean manager;

    @Column(name = "subscribe", nullable = false)
    private boolean subscribe;

    @Column(name = "currently_active", nullable = false)
    private boolean currentlyActive;




    // Getter, Setter, Constructors 등은 생략하였습니다.
}
