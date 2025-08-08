package com.dimartin.proy_fin_spring_boot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dimartin.proy_fin_spring_boot.model.Producto;
import com.dimartin.proy_fin_spring_boot.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
	@GetMapping
	public ResponseEntity<List<Producto>> getProductos(){
		List<Producto> lista_productos = productoService.getProductos();
		return ResponseEntity.ok(lista_productos);
	}
	
	
	@GetMapping("/{cod_prod}")
	public ResponseEntity<Producto> getProductoId(@PathVariable(name="cod_prod") Long cod_prod) {
		
		Optional<Producto> p = productoService.getProducto(cod_prod);
		
		if(p.isPresent()){
			return ResponseEntity.ok(p.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@PostMapping
	public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto) {
	    try {
	        Producto p = productoService.saveProducto(producto);
	        return ResponseEntity.ok(p);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}

	
	
	@DeleteMapping("/{cod_prod}")
	public ResponseEntity<String> deleteProducto(@PathVariable(name = "cod_prod") Long cod_prod){
		
		Producto p = productoService.getProducto(cod_prod).orElse(null);
		
		if(p != null){
			productoService.deleteProducto(cod_prod);
			return ResponseEntity.ok("Producto eliminado corecctamente");
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping("/{cod_prod}")
	public ResponseEntity<Producto> editProducto(@PathVariable Long cod_prod, @RequestBody Producto newProd){
		
		Producto p = productoService.getProducto(cod_prod).orElse(null);
		
		if(p != null ){
			p.setNombre(newProd.getNombre());
			p.setMarca(newProd.getMarca());
			p.setCosto(newProd.getCosto());
			p.setCantidad_disponible(newProd.getCantidad_disponible());
			
			productoService.saveProducto(p);
			return ResponseEntity.ok(p);
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	
	
	}
	
	
	@GetMapping("/menos-cinco-unidades")
	public ResponseEntity<List<Producto>> getMenoresCinco(){
		List<Producto> lista_menores_cinco = productoService.getMenoresAcinco();
		return ResponseEntity.ok(lista_menores_cinco);
	}
	
	// falto verificar que exista la venta
	// consultar lista de productos de una venta en particular
	@GetMapping("/productos-venta/{cod_venta}")   // si no uso el(name="cod-venta" postman me tira error)
	public ResponseEntity<List<Producto>>getProductosVenta(@PathVariable(name = "cod_venta") Long cod_venta){
		
		List<Producto> lista_prod_venta = productoService.getProductosVenta(cod_venta);
		
		return ResponseEntity.ok(lista_prod_venta);
		
	}
	
	
	// endpoint para consultar el stock de un producto en particular
	@GetMapping("/stock/{cod_prod}")
	public ResponseEntity<Integer> consultarStock(@PathVariable Long cod_prod){
		
		Producto p = productoService.getProducto(cod_prod).orElse(null);
		int cant_disponible = 0;
		
		if(p != null){
			cant_disponible = p.getCantidad_disponible();
			return ResponseEntity.ok(cant_disponible);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
		}
	}
	
	
	// endpoint para agregar stock a un producto
	@PatchMapping("/agregar/{cod_prod}")  // si no agrago el name en ambos parametros tira IllegarArgumentException 
	public ResponseEntity<Producto> actualizarStock(@PathVariable(name = "cod_prod") Long cod_prod, @RequestParam(name = "cant") int cant){
		
		Producto p = productoService.agregarStock(cod_prod, cant);
		return ResponseEntity.ok(p);
	}
}
