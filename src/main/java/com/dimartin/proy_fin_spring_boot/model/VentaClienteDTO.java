package com.dimartin.proy_fin_spring_boot.model;


public class VentaClienteDTO {
	
	private Long cod_venta;
	private Double total;
	private int cant_productos;
	private String nombre_cliente;
	private String apellido_cliente;
	
	
	public VentaClienteDTO(){
	}
	
	public VentaClienteDTO(Long cod_venta, Double total, int cant_productos, String nombre_cliente, String apellido_cliente){
		
		this.cod_venta = cod_venta;
		this.total = total;
		this.cant_productos = cant_productos;
		this.nombre_cliente = nombre_cliente;
		this.apellido_cliente = apellido_cliente;
	}
	
	
	public Long getCodVenta() {
		return cod_venta;
	}

	public void setCod_venta(Long cod_venta) {
		this.cod_venta = cod_venta;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public int getCant_productos() {
		return cant_productos;
	}

	public void setCant_productos(int cant_productos) {
		this.cant_productos = cant_productos;
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public String getApellido_cliente() {
		return apellido_cliente;
	}

	public void setApellido_cliente(String apellido_cliente) {
		this.apellido_cliente = apellido_cliente;
	}
}
