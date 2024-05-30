package com.uniform.web.AdmsissionInform.AdmissionInformService.mappingJson;


import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class AnalysisDatas {
    @NonNull
    private String university;
    @NonNull
    private String major;
    @NonNull
    private String possibility;
}

