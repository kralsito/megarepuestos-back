package com.megarepuestos.megarepuestos.repository;

import com.megarepuestos.megarepuestos.model.Replacement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReplacementRepository extends JpaRepository<Replacement, Long>, JpaSpecificationExecutor<Replacement> {
}
