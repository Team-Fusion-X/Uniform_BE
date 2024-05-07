package com.uniform.web.AdmsissionInform.dto;

import com.uniform.web.AdmsissionInform.entity.RegionEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegionDTO {
    private int regionId;
    private String province;
    private String cityCounty;


    //RegionEntity를 DTO로 변경하는 메소드
    public static RegionDTO toRegionDTO(RegionEntity regionEntity){
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setRegionId(regionEntity.getRegionId());
        regionDTO.setProvince(regionEntity.getProvince());
        regionDTO.setCityCounty(regionEntity.getCityCounty());

        return regionDTO;
    }
}
