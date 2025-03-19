package com.megarepuestos.megarepuestos.repository.specification;



import com.megarepuestos.megarepuestos.model.Form;
import com.megarepuestos.megarepuestos.service.dto.request.FormFilterDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;

public class FormSpec {
    public static Specification<Form> getSpec(FormFilterDTO filter){
        return (root, query, cb) -> {
            final Collection<Predicate> predicates = new ArrayList<>();

            if (filter.getFirstName() != null){
                predicates.add(cb.equal(root.get("firstName"), filter.getFirstName()));
            }

            if (filter.getLastName() != null){
                predicates.add(cb.equal(root.get("lastName"), filter.getLastName()));
            }

            if (filter.getPhoneNumber() != null){
                predicates.add(cb.equal(root.get("phoneNumber"), filter.getPhoneNumber()));
            }

            query.orderBy(cb.desc(root.get("id")));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
