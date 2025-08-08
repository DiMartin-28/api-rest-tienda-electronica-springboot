package com.dimartin.proy_fin_spring_boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimartin.proy_fin_spring_boot.model.Cliente;
import com.dimartin.proy_fin_spring_boot.repository.IClienteRepository;

@Service
public class ClienteService implements IClienteService{
	
	@Autowired
	private IClienteRepository clienteRepository;

	@Override
	public List<Cliente> getClientes() {
		List<Cliente> lista_clientes = clienteRepository.findAll();
		return lista_clientes;
	}

	@Override
	public Optional<Cliente> getCliente(Long id_cliente) {
		Optional<Cliente> cliente = clienteRepository.findById(id_cliente);
		return cliente;
	}

	@Override
	public void deleteCliente(Long id_cliente) {
		clienteRepository.deleteById(id_cliente);
	}

	@Override
	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente editCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
}
