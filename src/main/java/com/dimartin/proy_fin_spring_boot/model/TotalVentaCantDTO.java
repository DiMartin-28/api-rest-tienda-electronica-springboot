package com.dimartin.proy_fin_spring_boot.model;

import java.time.LocalDate;

public class TotalVentaCantDTO {
	
	private LocalDate fecha;
	private Double total_ventas;
	private int cant_ventas;
	
	
	public TotalVentaCantDTO() {
		
	}
	
	public TotalVentaCantDTO(LocalDate fecha, Double total_ventas, int cant_ventas) {
		this.fecha = fecha;
		this.total_ventas = total_ventas;
		this.cant_ventas = cant_ventas;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Double getTotal_ventas() {
		return total_ventas;
	}

	public void setTotal_ventas(Double total_ventas) {
		this.total_ventas = total_ventas;
	}

	public int getCant_ventas() {
		return cant_ventas;
	}

	public void setCant_ventas(int cant_ventas) {
		this.cant_ventas = cant_ventas;
	}
}
