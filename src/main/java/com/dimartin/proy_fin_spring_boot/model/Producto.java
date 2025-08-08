package com.dimartin.proy_fin_spring_boot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo_producto;
	@NotNull
	private String nombre;
	@NotNull
	private String marca;
	@NotNull
	private Double costo;
	@NotNull(message="Es requerida la cantidad")
	private int cantidad_disponible;
	@Transient
	private int cantidad_temporal;
	
	public Producto() {
	}

	public Producto(Long codigo_producto, String nombre, String marca, Double costo, int cantidad_disponible) {
		super();
		this.codigo_producto = codigo_producto;
		this.nombre = nombre;
		this.marca = marca;
		this.costo = costo;
		this.cantidad_disponible = cantidad_disponible;
	}

	public Long getCodigo_producto() {
		return codigo_producto;
	}

	public void setCodigo_producto(Long codigo_producto) {
		this.codigo_producto = codigo_producto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public int getCantidad_disponible() {
		return cantidad_disponible;
	}

	public void setCantidad_disponible(int cantidad_disponible) {
		this.cantidad_disponible = cantidad_disponible;
	}

	public int getCantidad_temporal() {
		return cantidad_temporal;
	}

	public void setCantidad_temporal(int cantidad_temporal) {
		this.cantidad_temporal = cantidad_temporal;
	}
	
	
}
