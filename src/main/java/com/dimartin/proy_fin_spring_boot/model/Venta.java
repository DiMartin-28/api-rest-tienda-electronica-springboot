package com.dimartin.proy_fin_spring_boot.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;


@Entity
@Table(name="ventas")
public class Venta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo_venta;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent
	private LocalDate fecha_venta;
	private double total;
	
	@ManyToMany
	@JoinTable(name="ventas_productos", joinColumns = {@JoinColumn(name="codigo_venta")},
	inverseJoinColumns = {@JoinColumn(name="codigo_producto")})
	@NotNull
	private List<Producto> lista_productos;
	
	@ManyToOne
	@JoinColumn(name="un_cliente_id_cliente", referencedColumnName = "id_cliente")
	@NotNull
	private Cliente un_cliente;
	
	
	public Venta() {
	}

	public Venta(Long codigo_venta, LocalDate fecha_venta, double total, List<Producto> lista_productos, Cliente un_cliente) {
		super();
		this.codigo_venta = codigo_venta;
		this.fecha_venta = fecha_venta;
		this.total = total;
		this.lista_productos = lista_productos;
		this.un_cliente = un_cliente;
	}
	

	public Long getCodigo_venta() {
		return codigo_venta;
	}

	public void setCodigo_venta(Long codigo_venta) {
		this.codigo_venta = codigo_venta;
	}

	public LocalDate getFecha_venta() {
		return fecha_venta;
	}

	public void setFecha_venta(LocalDate fecha_venta) {
		this.fecha_venta = fecha_venta;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<Producto> getLista_productos() {
		return lista_productos;
	}

	public void setLista_productos(List<Producto> lista_productos) {
		this.lista_productos = lista_productos;
	}

	public Cliente getUn_cliente() {
		return un_cliente;
	}

	public void setUn_cliente(Cliente un_cliente) {
		this.un_cliente = un_cliente;
	}

	
}
