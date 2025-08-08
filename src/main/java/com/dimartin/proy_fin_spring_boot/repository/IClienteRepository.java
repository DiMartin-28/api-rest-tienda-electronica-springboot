package com.dimartin.proy_fin_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dimartin.proy_fin_spring_boot.model.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long>{

}
