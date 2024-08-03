package com.jotacode.apigym.controller;

import com.jotacode.apigym.model.entity.Cliente;
import com.jotacode.apigym.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    //Obtener todos los clientes
    @GetMapping("/clientes")
    public List<Cliente> getClientes(){
        return clienteService.findAllClientes();
    }

    //Postear un cliente
    @PostMapping("/crearCliente")
    public Cliente saveCliente(@Valid @RequestBody Cliente cliente){
        return clienteService.saveCliente(cliente);
    }

    //Obtener un cliente por id
    @GetMapping("/cliente/{id}")
    public Cliente getClienteById(@PathVariable String id){
        return clienteService.findClienteById(id);
    }

    //Actualizar un cliente por id
    @PostMapping("/actualizarCliente/{id}")
    public Cliente updateCliente(@RequestBody Cliente cliente, @PathVariable String id){
        return clienteService.updateCliente(cliente, id);
    }

    //Eliminar un cliente por id
    @DeleteMapping("/eliminarCliente/{id}")
    public void deleteClienteById(@PathVariable String id){
        clienteService.deleteCliente(id);
    }


}
