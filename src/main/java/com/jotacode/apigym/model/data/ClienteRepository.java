package com.jotacode.apigym.model.data;

import com.jotacode.apigym.error.dto.ClienteMembresiaDTO;
import com.jotacode.apigym.model.entity.Cliente;
import com.jotacode.apigym.model.entity.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    //Query method para buscar los clientes con su membresia
    @Query("SELECT new com.jotacode.apigym.error.dto.ClienteMembresiaDTO(c.cedula, c.membresia.idMembresia, c.membresia.tipoMembresia) FROM Cliente c JOIN Membresia m ON c.membresia.idMembresia = m.idMembresia")
    List<ClienteMembresiaDTO> findClienteMembresiaDetails();

    Optional<Cliente> findByMembresia(Membresia membresia);

}
