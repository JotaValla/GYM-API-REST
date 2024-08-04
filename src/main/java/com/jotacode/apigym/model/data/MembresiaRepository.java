package com.jotacode.apigym.model.data;

import com.jotacode.apigym.model.entity.Cliente;
import com.jotacode.apigym.model.entity.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembresiaRepository extends JpaRepository<Membresia, Long> {

}
