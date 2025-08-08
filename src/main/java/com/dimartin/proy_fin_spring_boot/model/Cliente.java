package com.dimartin.proy_fin_spring_boot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private Long id_cliente;
	@NotNull
	private String nombre;
	@NotNull
	private String apellido;
	@NotNull
	private String dni;
	
	
	public Cliente() {
	}


	public Cliente(Long id_cliente, String nombre, String apellido, String dni) {
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}


	public Long getId_cliente() {
		return id_cliente;
	}


	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}
}
