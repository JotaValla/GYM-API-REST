package com.jotacode.apigym.service;

import com.jotacode.apigym.error.ClienteException;
import com.jotacode.apigym.error.MembresiaException;
import com.jotacode.apigym.error.dto.ClienteMembresiaDTO;
import com.jotacode.apigym.model.entity.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> findAllClientes();
    Cliente saveCliente(Cliente cliente) throws ClienteException, MembresiaException;
    Cliente findClienteById(String id) throws ClienteException;
    Cliente updateCliente(Cliente cliente, String id) throws ClienteException;
    void deleteCliente(String id) throws ClienteException;
    List<ClienteMembresiaDTO> getClienteMembresiaDetails();

}
