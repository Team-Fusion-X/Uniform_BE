package com.uniform.web.AdmsissionInform.Repository;

import com.uniform.web.AdmsissionInform.entity.analysisEntity;
import org.springframework.data.jpa.domain.Specification;

public class AnalysisSpecification {

    public static Specification<analysisEntity> hasField(String field) {
        return (root, query, cb) -> field == null ? cb.conjunction() : cb.equal(root.get("fields"), field);
    }

    public static Specification<analysisEntity> hasMajor(String major) {
        return (root, query, cb) -> major == null ? cb.conjunction() : cb.equal(root.get("department"), major);
    }

    public static Specification<analysisEntity> hasUniversity(String university) {
        return (root, query, cb) -> university == null ? cb.conjunction() : cb.equal(root.get("university"), university);
    }

    public static Specification<analysisEntity> hasKeyword(String keyword) {
        return (root, query, cb) -> keyword == null ? cb.conjunction() : cb.equal(root.get("keyword"), keyword);
    }
}
