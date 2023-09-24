package com.moto.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moto.service.entity.Moto;

public interface IMotoRepository extends JpaRepository<Moto, Integer> {

	List<Moto> findByUsuarioId(int usuarioId);
}
