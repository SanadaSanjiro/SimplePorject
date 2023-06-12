package com.digdes.simple.dao.project;

import com.digdes.simple.dto.project.ProjectSrchDTO;
import com.digdes.simple.model.project.ProjectModel;
import com.digdes.simple.model.project.ProjectStatus;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class ProjectSpecification {
    public static Specification<ProjectModel> getFilters (ProjectSrchDTO dto) {
        final ProjectStatus ps;
        if (dto.getStatus()!=null) {
            ProjectStatus status = ProjectStatus.DRAFT;
            switch (dto.getStatus()) {
                case "DEVELOPING": {
                    status = ProjectStatus.DEVELOPING;
                    break;
                }
                case "TESTING": {
                    status = ProjectStatus.TESTING;
                    break;
                }
                case "COMPLETE": {
                    status = ProjectStatus.COMPLETE;
                    break;
                }
            }
            ps = status;
        } else ps = null;
        return ((root, query, criteriaBuilder ) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(!ObjectUtils.isEmpty(dto.getName()))
                predicates.add(criteriaBuilder.like(root.get("name"), "%"+dto.getName()+"%"));
            if(!ObjectUtils.isEmpty(dto.getCode()))
                predicates.add(criteriaBuilder.like(root.get("code"), "%"+dto.getCode()+"%"));
            if(!ObjectUtils.isEmpty(dto.getStatus()))
                predicates.add(criteriaBuilder.equal(root.get("status"), ps));
            if (CollectionUtils.isEmpty(predicates))
                return query.where().getRestriction();
            else
                return query.where(criteriaBuilder.and(predicates.toArray(Predicate[]::new))).getRestriction();
        });
    }
}
