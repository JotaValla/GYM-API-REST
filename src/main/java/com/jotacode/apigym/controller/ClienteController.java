package com.jotacode.apigym.controller;

import com.jotacode.apigym.error.ClienteException;
import com.jotacode.apigym.error.MembresiaException;
import com.jotacode.apigym.model.entity.Cliente;
import com.jotacode.apigym.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    //Obtener todos los clientes
    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getClientes(){
        return ResponseEntity.ok(clienteService.findAllClientes());
    }

    //Postear un cliente
    @PostMapping("/crearCliente")
    public ResponseEntity<Cliente> saveCliente(@Valid @RequestBody Cliente cliente) throws MembresiaException, ClienteException {
        return ResponseEntity.ok(clienteService.saveCliente(cliente));
    }

    //Obtener un cliente por id
    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable String id) throws ClienteException {
        return ResponseEntity.ok(clienteService.findClienteById(id));
    }

    //Actualizar un cliente por id
    @PutMapping("/actualizarCliente/{id}")
    public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente, @PathVariable String id) throws ClienteException {
        return ResponseEntity.ok(clienteService.updateCliente(cliente, id));
    }

    //Eliminar un cliente por id
    @DeleteMapping("/eliminarCliente/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable String id) throws ClienteException {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }


}
