package com.digdes.simple.task;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class TaskSpecification {
    public static Specification<TaskModel> getFilters (TaskSrchDTO dto) {
        TaskStatus status = TaskStatus.NEW;
        final TaskStatus ts;
        if (dto.getStatus()!=null) {
            switch (dto.getStatus()) {
                case "PROCESSED": {
                    status = TaskStatus.PROCESSED;
                    break;
                }
                case "COMPLETE": {
                    status = TaskStatus.COMPLETE;
                    break;
                }
                case "CLOSED": {
                    status = TaskStatus.CLOSED;
                    break;
                }
            }
            ts = status;
        } else ts = null;
        return ((root, query, criteriaBuilder ) -> {
            List<Predicate> predicates = new ArrayList<>();
            //Order order = criteriaBuilder.desc(root.get("creationdate"));
            if(!ObjectUtils.isEmpty(dto.getName()))
                predicates.add(criteriaBuilder.like(root.get("name"), "%"+dto.getName()+"%"));
            if(!ObjectUtils.isEmpty(dto.getStatus()))
                predicates.add(criteriaBuilder.equal(root.get("status"), ts));
            if(!ObjectUtils.isEmpty(dto.getEmployee()))
                predicates.add(criteriaBuilder.equal(root.get("com/digdes/simple/employee"), dto.getEmployee()));
            if(!ObjectUtils.isEmpty(dto.getAuthor()))
                predicates.add(criteriaBuilder.equal(root.get("author"), dto.getAuthor()));
            if(!ObjectUtils.isEmpty(dto.getExecutionDate()))
                predicates.add(criteriaBuilder.equal(root.get("executiondate"), dto.getExecutionDate()));
            if(!ObjectUtils.isEmpty(dto.getCreationDate()))
                predicates.add(criteriaBuilder.equal(root.get("creationdate"), dto.getCreationDate()));
            if (CollectionUtils.isEmpty(predicates))
                return query
                        //.orderBy(order)
                        .where()
                        .getRestriction();
            else
                return query
                        //.orderBy(order)
                        .where(criteriaBuilder.and(predicates.toArray(Predicate[]::new)))
                        .getRestriction();
        });
    }
}
