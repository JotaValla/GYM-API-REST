package com.jotacode.apigym.service;

import com.jotacode.apigym.model.entity.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> findAllClientes();

    Cliente saveCliente(Cliente cliente);

    Cliente findClienteById(String id);

    Cliente updateCliente(Cliente cliente, String id);

    void deleteCliente(String id);



}
