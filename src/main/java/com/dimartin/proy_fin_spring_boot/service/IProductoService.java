package com.dimartin.proy_fin_spring_boot.service;

import java.util.List;
import java.util.Optional;

import com.dimartin.proy_fin_spring_boot.model.Producto;

public interface IProductoService {
	
	public List<Producto> getProductos();
	Optional<Producto> getProducto(Long codigo_producto);
	public void deleteProducto(Long codigo_producto);
	public Producto saveProducto(Producto producto);
	public Producto editProducto(Producto producto);
	public List<Producto> getMenoresAcinco();
	public List<Producto> getProductosVenta(Long cod_venta);
	// metodo para acutlaizar el stock disponible
	public void actualizarStock(Long cod_prod, int cantidad);
	// motodo para agregar stock a un prodcuto
	public Producto agregarStock(Long Cod_prod, int cant);
	
	

}
