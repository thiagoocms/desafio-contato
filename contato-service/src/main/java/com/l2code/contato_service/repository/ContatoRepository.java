package com.l2code.contato_service.repository;

import com.l2code.contato_service.domain.Contato;
import com.l2code.contato_service.dto.projection.SimpleContatoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    Optional<Contato> findByIdAndSnAtivoIsTrue(Long id);

    @Query("SELECT c FROM Contato c WHERE c.snFavorito = TRUE AND c.snAtivo = TRUE ORDER BY c.nome ASC")
    List<SimpleContatoProjection> findAllBySnFavoritoIsTrueAndSnAtivoIsTrueOrderByNomeAsc();

    @Query("SELECT c FROM Contato c WHERE c.snAtivo = TRUE ORDER BY c.nome ASC")
    List<Contato> findAllBySnAtivoIsTrueOrderByNomeAsc();

    Long countByCelularAndSnAtivoIsTrue(String celular);
}
