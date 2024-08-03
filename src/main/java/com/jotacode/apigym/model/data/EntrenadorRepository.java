package com.jotacode.apigym.model.data;

import com.jotacode.apigym.model.entity.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, String> {
}
