package com.uniform.web.AdmsissionInform.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "region", schema = "uniformDB")
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private int regionId;

    @Column(name = "province", length = 7, nullable = false)
    private String province;

    @Column(name = "city_county", length = 7, nullable = false)
    private String cityCounty;

}
