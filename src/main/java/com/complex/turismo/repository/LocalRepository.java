package com.complex.turismo.repository;

import com.complex.turismo.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocalRepository extends JpaRepository<Local, Long> {
    @Query("SELECT l FROM Local l WHERE LOWER(l.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Local> findByNameContainingIgnoreCase(@Param("nome") String nome);
}