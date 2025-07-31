package com.gezcarlos.Literatura.repository;

import com.gezcarlos.Literatura.model.Idioma;
import com.gezcarlos.Literatura.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IdiomaRepository extends JpaRepository<Idioma,String> {
    Persona findByNombre(String nombre);

    @Query("SELECT CONCAT(i.codigo, ' - ', i.nombre) FROM Idioma i")
    List<Idioma> findIdiomasConNombre();

}
