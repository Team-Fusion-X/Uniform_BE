package com.uniform.web.AdmsissionInform.AdmissionInformService.mappingJson;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WrappingSubjectScore {
    List<SubjectScore> scores;
}
