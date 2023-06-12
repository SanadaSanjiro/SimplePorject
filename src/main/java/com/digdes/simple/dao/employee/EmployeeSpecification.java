package com.digdes.simple.dao.employee;

import com.digdes.simple.dto.employee.EmployeeSrchDTO;
import com.digdes.simple.model.employee.EmployeeModel;
import com.digdes.simple.model.employee.EmployeeStatus;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSpecification {

    public static Specification<EmployeeModel> getFilters(EmployeeSrchDTO employeeDTO) {
        return ((root, query, criteriaBuilder ) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(!ObjectUtils.isEmpty(employeeDTO.getFirstname()))
                predicates.add(criteriaBuilder.like(root.get("firstName"), "%"+employeeDTO.getFirstname()+"%"));
            if(!ObjectUtils.isEmpty(employeeDTO.getLastname()))
                predicates.add(criteriaBuilder.like(root.get("lastName"), "%"+employeeDTO.getLastname()+"%"));
            if(!ObjectUtils.isEmpty(employeeDTO.getSurname()))
                predicates.add(criteriaBuilder.like(root.get("surName"), "%"+employeeDTO.getSurname()+"%"));
            if(!ObjectUtils.isEmpty(employeeDTO.getAccount()))
                predicates.add(criteriaBuilder.like(root.get("account"), "%"+employeeDTO.getAccount()+"%"));
            if(!ObjectUtils.isEmpty(employeeDTO.getEmail()))
                predicates.add(criteriaBuilder.like(root.get("eMail"), "%"+employeeDTO.getEmail()+"%"));
            predicates.add(criteriaBuilder.equal(root.get("status"), EmployeeStatus.ACTIVE));
            if (CollectionUtils.isEmpty(predicates))
                return query.where().getRestriction();
            else
                return query.where(criteriaBuilder.and(predicates.toArray(Predicate[]::new))).getRestriction();
        });
    }
}
