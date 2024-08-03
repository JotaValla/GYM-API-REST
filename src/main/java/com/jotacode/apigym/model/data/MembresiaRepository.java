package com.jotacode.apigym.model.data;

import com.jotacode.apigym.model.entity.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembresiaRepository extends JpaRepository<Membresia, Long> {
}
