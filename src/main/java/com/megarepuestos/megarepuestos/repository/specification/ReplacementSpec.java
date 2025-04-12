package com.megarepuestos.megarepuestos.repository.specification;

import com.megarepuestos.megarepuestos.model.Replacement;
import com.megarepuestos.megarepuestos.service.dto.request.ReplacementFilterDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;

public class ReplacementSpec {
    public static Specification<Replacement> getSpec(ReplacementFilterDTO filter){
        return (root, query, cb) -> {
            final Collection<Predicate> predicates = new ArrayList<>();

            if (filter.getName() != null && !filter.getName().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
            }

            if (filter.getBrand_id() != null){
                predicates.add(cb.equal(root.get("brand").get("id"), filter.getBrand_id()));
            }

            if (filter.getTypeReplacement_id() != null){
                predicates.add(cb.equal(root.get("typeReplacement").get("id"), filter.getTypeReplacement_id()));
            }

            query.orderBy(cb.desc(root.get("id")));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
