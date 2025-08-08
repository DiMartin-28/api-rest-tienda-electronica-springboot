package com.dimartin.proy_fin_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dimartin.proy_fin_spring_boot.model.Venta;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Long>{

}
