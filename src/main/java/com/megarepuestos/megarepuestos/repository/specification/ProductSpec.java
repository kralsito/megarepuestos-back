package com.megarepuestos.megarepuestos.repository.specification;

import com.megarepuestos.megarepuestos.model.Product;
import com.megarepuestos.megarepuestos.service.dto.request.ProductFilterDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;

public class ProductSpec {
    public static Specification<Product> getSpec(ProductFilterDTO filter){
        return (root, query, cb) -> {
            final Collection<Predicate> predicates = new ArrayList<>();

            if (filter.getName() != null){
                predicates.add(cb.equal(root.get("name"), filter.getName()));
            }

            query.orderBy(cb.desc(root.get("id")));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
