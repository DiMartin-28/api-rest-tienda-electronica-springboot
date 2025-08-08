package com.dimartin.proy_fin_spring_boot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dimartin.proy_fin_spring_boot.model.Cliente;
import com.dimartin.proy_fin_spring_boot.model.Producto;
import com.dimartin.proy_fin_spring_boot.model.TotalVentaCantDTO;
import com.dimartin.proy_fin_spring_boot.model.Venta;
import com.dimartin.proy_fin_spring_boot.model.VentaClienteDTO;
import com.dimartin.proy_fin_spring_boot.repository.IVentaRepository;

@Service
public class VentaService implements IVentaService{
	
	@Autowired
	private IVentaRepository ventaRepository;
	
	@Autowired  
	private IProductoService productoService;
	
	@Autowired
	private IClienteService clienteService;

	@Override
	public List<Venta> getVenta() {
		List<Venta> lista_ventas = ventaRepository.findAll();
		return lista_ventas;
	}

	@Override
	public Optional<Venta> getVenta(Long codigo_venta) {
		Optional<Venta> venta = ventaRepository.findById(codigo_venta);
		return venta;
	}

	@Override
	public void deleteVenta(Long codigo_venta) {
		
		Venta v = ventaRepository.findById(codigo_venta).get();
		List<Producto> list_prods = v.getLista_productos();
		
		for(Producto pv : list_prods){
			Optional<Producto> prodOpt = productoService.getProducto(pv.getCodigo_producto());
			
			if( prodOpt.isPresent()){
				Producto p = prodOpt.get();
				productoService.agregarStock(p.getCodigo_producto(), 1);
			}
		}
		ventaRepository.deleteById(codigo_venta);
	}

	@Override
	public Venta editVenta(Venta venta) {
		return ventaRepository.save(venta);
	}

	@Override
	public VentaClienteDTO getMayorVentaCliente() {
		
		VentaClienteDTO vcdto = new VentaClienteDTO();
		Venta v = this.getMayorVenta();
		
		vcdto.setCod_venta(v.getCodigo_venta());
		vcdto.setTotal(v.getTotal());
		vcdto.setCant_productos(v.getLista_productos().size());
		vcdto.setNombre_cliente(v.getUn_cliente().getNombre());
		vcdto.setApellido_cliente(v.getUn_cliente().getApellido());
		
		return vcdto;
	}
	
	
	// metodo auxiliar de VentaClienteDTO
	private Venta getMayorVenta(){
	
		Double mayor_costo = 0.0;
	
		List<Venta> lista_ventas = this.getVenta();
		Venta v = new Venta();
	
		for (Venta venta : lista_ventas) {
			if(venta.getTotal() > mayor_costo){
				mayor_costo = venta.getTotal();
				v = venta;
			}
		}
	
		return v;
	}

	@Override  // aca tengo que devolverlo en formato dto
	public TotalVentaCantDTO obtenerTotalyVentasDia(LocalDate fecha) {
		
		TotalVentaCantDTO tvcdto = new TotalVentaCantDTO();
		
		int cantidad_ventas = 0;
		Double monto_total = 0.0;
		String monto_ventas = null;
		
		List<Venta> lista_total_ventas = ventaRepository.findAll();
		List<Venta> lista_ventas_dia = new ArrayList<>();
	
		
		 for (Venta venta : lista_total_ventas){
			if(venta.getFecha_venta().equals(fecha)){ // si uso == en lugar de equals no lo toma
				lista_ventas_dia.add(venta);
			}
		}
		 
		 for (Venta venta : lista_ventas_dia) {
			monto_total += venta.getTotal();
		}
		
		 cantidad_ventas = lista_ventas_dia.size();
		 
		 monto_ventas = "La sumatoria de los montos es: " + monto_total + " y el total de ventas del dia "
		 		+ " selecionado es : " + cantidad_ventas;
		 
		 tvcdto.setFecha(fecha);
		 tvcdto.setTotal_ventas(monto_total);
		 tvcdto.setCant_ventas(cantidad_ventas);
		 
		return tvcdto;
	}
	
	// metodo para registra una venta descontando de a una unidad de stock de producto de la lista
	@Override  
	public Venta saveVentaFlexible(Venta venta){
		
		Venta v = null;
	
	     if(validarCliente(venta)) {
			double total = 0.0;
			List<Producto> lista_productos = venta.getLista_productos();
			
			for(Producto p : lista_productos) {
				Optional<Producto> prodOpt = productoService.getProducto(p.getCodigo_producto());
				
				if(prodOpt.isPresent()) {
					Producto prod = prodOpt.get();
					int cant_disponible = prod.getCantidad_disponible();
					
					if(prod.getCantidad_disponible() > 0) {
						total += p.getCosto();
						cant_disponible = prod.getCantidad_disponible() - 1;
						productoService.actualizarStock(prod.getCodigo_producto(), cant_disponible);
					}
				}
			}
			venta.setTotal(total);
			v = ventaRepository.save(venta);
	     } 
			return v;
	}
	
	// metodo para registar una nueva venta validando el stock total
	@Override
	public Venta saveVentaEstrict(Venta venta) {
		
		Venta ventaResult = null;
		
		boolean esValido = validarCliente(venta) && validarStock(venta);

	    if (esValido) {
	        double totalVenta = calcularTotal(venta);
	        
	        venta.setTotal(totalVenta);
			ventaRepository.save(venta);
			
			ventaResult = venta;
	    }
		return ventaResult;
	}

		
	// funcion para validar un cliente
	private boolean validarCliente(Venta venta){
		
		boolean existCliente = false;
		
		Long codigoClienteVenta = venta.getUn_cliente().getId_cliente();
		List<Cliente> listadoClientes = clienteService.getClientes();
		
		for(Cliente c : listadoClientes){
			if(c.getId_cliente() == codigoClienteVenta ) {
				existCliente = true;
			}
		}
		return existCliente;
	}
	
	// funcion para validar el stock antes de registrar una venta
	private boolean validarStock(Venta venta) {
		
		boolean validado = true;
		List<Producto> listaProductos = venta.getLista_productos();
		
		for(Producto p : listaProductos){
			Optional<Producto> prodOpt = productoService.getProducto(p.getCodigo_producto());
			
			if(prodOpt.isEmpty()){
				validado = false;
				break;
			}
			Producto prod = prodOpt.get();
			int cantidadDisponible = prod.getCantidad_disponible();
			int cantidadVenta = p.getCantidad_temporal();
			
			if(cantidadDisponible < cantidadVenta){
				validado = false;
				break;
			}
			productoService.actualizarStock(prod.getCodigo_producto(), cantidadDisponible - cantidadVenta);
			}	
		return validado;
	}
	
	// funcion para calcular el total de una venta
	private double calcularTotal(Venta venta){
		
		double total = 0.0;
		
		for(Producto p : venta.getLista_productos()){
			total += p.getCosto() * p.getCantidad_temporal();
			System.out.println("El costo total de la venta es : " + total);
		}
		return total;
	}
}

