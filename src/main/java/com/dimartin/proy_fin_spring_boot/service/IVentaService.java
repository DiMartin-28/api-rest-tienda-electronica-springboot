package com.dimartin.proy_fin_spring_boot.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.dimartin.proy_fin_spring_boot.model.TotalVentaCantDTO;
import com.dimartin.proy_fin_spring_boot.model.Venta;
import com.dimartin.proy_fin_spring_boot.model.VentaClienteDTO;

public interface IVentaService {
	
	public List<Venta> getVenta();
	public Optional<Venta> getVenta(Long codigo_venta);
	public Venta saveVentaFlexible(Venta venta);
	public Venta saveVentaEstrict(Venta venta);
	public void deleteVenta(Long codigo_venta);
	public Venta editVenta(Venta venta);
	public VentaClienteDTO getMayorVentaCliente();
	public TotalVentaCantDTO obtenerTotalyVentasDia(LocalDate fecha);
	
}
