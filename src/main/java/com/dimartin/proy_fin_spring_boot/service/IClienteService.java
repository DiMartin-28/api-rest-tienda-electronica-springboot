package com.dimartin.proy_fin_spring_boot.service;

import java.util.List;
import java.util.Optional;

import com.dimartin.proy_fin_spring_boot.model.Cliente;

public interface IClienteService {

	public List<Cliente> getClientes();
	public Optional<Cliente> getCliente(Long id_cliente);
	public void deleteCliente(Long id_cliente);
	public Cliente saveCliente(Cliente cliente);
	public Cliente editCliente(Cliente cliente);
}
