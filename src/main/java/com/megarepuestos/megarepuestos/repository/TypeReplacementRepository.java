package com.megarepuestos.megarepuestos.repository;

import com.megarepuestos.megarepuestos.model.TypeReplacement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.lang.reflect.Type;

public interface TypeReplacementRepository extends JpaRepository<TypeReplacement, Long>, JpaSpecificationExecutor<TypeReplacement> {
}
