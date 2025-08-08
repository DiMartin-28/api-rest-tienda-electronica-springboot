package com.dimartin.proy_fin_spring_boot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dimartin.proy_fin_spring_boot.model.Cliente;
import com.dimartin.proy_fin_spring_boot.service.IClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> getClientes(){
		
		List<Cliente> lista_clientes = clienteService.getClientes();
		return ResponseEntity.ok(lista_clientes);
	}
	
	@GetMapping("/{id_cliente}")
	public ResponseEntity<Cliente> getCliente(@PathVariable(name = "id_cliente") Long id_cliente){
		
			Optional<Cliente> c = clienteService.getCliente(id_cliente);
			if(c.isPresent()){
				return ResponseEntity.ok(c.get());
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		
	}
	
	@PostMapping
	public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente){
		
		try {
			Cliente c = clienteService.saveCliente(cliente);
			return ResponseEntity.ok(c);
		}
		catch (Exception e) {
		
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCliente(@PathVariable(name="id") Long id){
		
		String mensaje = null;
		
		Cliente c = clienteService.getCliente(id).orElse(null);
		
		if(c != null) {
			clienteService.deleteCliente(id);
			mensaje = "Cliente eliminado correctamente";
			
		}
		else {
			mensaje = "El cliente que desea eliminar no se encuentra en la base de datos";
		}
		
			return ResponseEntity.ok(mensaje);
	}
	
	
	@PutMapping("/{id_cliente}")
	public ResponseEntity<Cliente> editCliente(@PathVariable Long id_cliente, @RequestBody Cliente newCliente){
		
		Optional<Cliente> c = clienteService.getCliente(id_cliente);
		
		if(c.isPresent()){
			Cliente cli = c.get();
			cli = newCliente;
			clienteService.editCliente(newCliente);	
			return ResponseEntity.ok(cli);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
}
