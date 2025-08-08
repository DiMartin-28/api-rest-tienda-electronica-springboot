package com.dimartin.proy_fin_spring_boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimartin.proy_fin_spring_boot.model.Producto;
import com.dimartin.proy_fin_spring_boot.model.Venta;
import com.dimartin.proy_fin_spring_boot.repository.IProductoRepository;
import com.dimartin.proy_fin_spring_boot.repository.IVentaRepository;

@Service
public class ProductoService implements IProductoService{
	
	@Autowired
	private IProductoRepository productoRepository;
	
	@Autowired
	private IVentaRepository ventaRepository;
	

	@Override
	public List<Producto> getProductos() {
		List<Producto> lista_productos = productoRepository.findAll();
		return lista_productos;
	}


	@SuppressWarnings("deprecation")
	@Override
	public Optional<Producto> getProducto(Long codigo_producto) {
		
		return productoRepository.findById(codigo_producto);
		
	}

	@Override
	public void deleteProducto(Long codigo_producto) {
		productoRepository.deleteById(codigo_producto);
	}

	@Override
	public Producto saveProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public Producto editProducto(Producto producto) {
		return productoRepository.save(producto);
	}
	
	
	
	// consultar los productos con stock menor a 5 unidaes
	@Override
	public List<Producto> getMenoresAcinco(){
		
		List<Producto> lista_productos = this.getProductos(); 
		List<Producto> lista_menos_cinco = new ArrayList<>();
		
		for(Producto p : lista_productos) {
			if(p.getCantidad_disponible() < 5.0){
				lista_menos_cinco.add(p);
			}
		}
		
		return lista_menos_cinco;
		
	}
	
	// consular la lista de productos de una determinada venta
	@Override
	public List<Producto> getProductosVenta(Long cod_venta){
		
		Venta venta = ventaRepository.findById(cod_venta).orElse(null);
	
		if(venta != null){
	
			List<Producto> lista_productos_venta = venta.getLista_productos();
			return lista_productos_venta;
		}
		
		return null;
	}


	public void actualizarStock(Long cod_prod, int cantidad) {
		Producto p = productoRepository.findById(cod_prod).orElse(null);
		p.setCantidad_disponible(cantidad);
		this.saveProducto(p);
	}


	@Override
	public Producto agregarStock(Long Cod_prod, int cant) {
	
		@SuppressWarnings("deprecation")
		Producto p = productoRepository.findById(Cod_prod).orElse(null);
		
		int stock_actual = p.getCantidad_disponible();
		
		p.setCantidad_disponible(stock_actual + cant);
		productoRepository.save(p);
		return p;
	}
}
