package com.dimartin.proy_fin_spring_boot.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dimartin.proy_fin_spring_boot.model.Cliente;
import com.dimartin.proy_fin_spring_boot.model.Producto;
import com.dimartin.proy_fin_spring_boot.model.TotalVentaCantDTO;
import com.dimartin.proy_fin_spring_boot.model.Venta;
import com.dimartin.proy_fin_spring_boot.model.VentaClienteDTO;
import com.dimartin.proy_fin_spring_boot.service.ClienteService;
import com.dimartin.proy_fin_spring_boot.service.IClienteService;
import com.dimartin.proy_fin_spring_boot.service.IVentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {
	
	@Autowired
	private IVentaService ventaService;
	
	@GetMapping
	public ResponseEntity<List<Venta>> getVentas(){
		
		List<Venta> lista_ventas = ventaService.getVenta();
		return ResponseEntity.ok(lista_ventas);
	}
	
	@GetMapping("/{cod_venta}")
	public ResponseEntity<Venta> getVenta(@PathVariable (name="cod_venta") Long cod_venta){
		
		Venta v = ventaService.getVenta(cod_venta).orElse(null);
		if(v != null){
			return ResponseEntity.ok(v);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(v);
		}
		
	}
	
	// registra la venta de a un producto mientras haya stock disponible
	@PostMapping
	public ResponseEntity<Venta> saveVentaNuevo(@RequestBody Venta venta) {

		Venta nuevaVenta = ventaService.saveVentaFlexible(venta);
		if(nuevaVenta != null) {
			return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	// endpoint para crear la venta con el stock completo
	@PostMapping("/complete")
	public ResponseEntity<Venta> saveVentaEstrict(@RequestBody Venta venta){
		
		Venta nuevaVenta = ventaService.saveVentaEstrict(venta);
		
		if(nuevaVenta != null){
			return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/{cod_venta}")
	public ResponseEntity<String> deleteVenta(@PathVariable(name="cod_venta") Long cod_venta){
		
		Venta v = ventaService.getVenta(cod_venta).orElse(null);
		
		if(v != null){
			ventaService.deleteVenta(cod_venta);
			return ResponseEntity.ok("Venta eliminada correctamente");
		}
			return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{cod_venta}")
	public ResponseEntity<Venta> editVenta(@PathVariable Long cod_venta, @RequestBody Venta newVenta){
		
		if(newVenta == null){
			return ResponseEntity.badRequest().build();
		}
		
		Optional<Venta> optionalVenta = ventaService.getVenta(cod_venta);
		
		if(optionalVenta.isPresent()){
			Venta venta = optionalVenta.get();
			venta.setFecha_venta(newVenta.getFecha_venta());
			venta.setTotal(newVenta.getTotal());
			venta.setLista_productos(newVenta.getLista_productos());
			venta.setUn_cliente(newVenta.getUn_cliente());
			ventaService.saveVentaFlexible(newVenta);
			return ResponseEntity.ok(venta);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	// endpoint para traer la venta con mayor monto de todas dto
	@GetMapping("/mayor-venta")
	public ResponseEntity<VentaClienteDTO> getMayorVentaCliente() {
	    try {
	        VentaClienteDTO vcdto = ventaService.getMayorVentaCliente();
	  
	        return ResponseEntity.ok(vcdto);
	    } catch (Exception e) {

	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

	
	// obtener la sumatoria, el monto, y cantidad de ventas de un dia especifico
	@GetMapping("/fecha")
	public ResponseEntity<TotalVentaCantDTO> obtenerTotalyVentasDia(@RequestParam(name = "fecha") LocalDate fecha) {
	    try {
	        TotalVentaCantDTO totalCantVtaDia = ventaService.obtenerTotalyVentasDia(fecha);
	        return ResponseEntity.ok(totalCantVtaDia);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
	
	
}
