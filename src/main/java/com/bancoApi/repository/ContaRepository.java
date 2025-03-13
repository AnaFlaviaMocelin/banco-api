package com.bancoApi.repository;

import com.bancoApi.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {
  Optional<Conta> findByNumeroConta(String numeroConta);
  boolean existsByNumeroConta(String numeroConta);
}