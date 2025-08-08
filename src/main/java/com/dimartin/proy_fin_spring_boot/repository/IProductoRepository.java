package com.dimartin.proy_fin_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dimartin.proy_fin_spring_boot.model.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long>{

}
