package com.jotacode.apigym.model.data;

import com.jotacode.apigym.model.entity.Entrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Long> {



}
